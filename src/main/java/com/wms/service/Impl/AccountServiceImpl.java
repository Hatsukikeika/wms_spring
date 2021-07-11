package com.wms.service.Impl;

import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.wms.DAO.GroupRepository;
import com.wms.DAO.UserRepository;
import com.wms.DAO.FriendPairRepository;
import com.wms.DAO.ValidationRepository;
import com.wms.bean.Group;

import com.wms.bean.User;
import com.wms.bean.enu.GroupType;
import com.wms.bean.enu.UserRole;
import com.wms.bean.relations.mtm.FriendPair;
import com.wms.bean.Validation;
import com.wms.model.AccMailContent;
import com.wms.service.AccountService;
import com.wms.service.Exceptions.BadAuthParamException;
import com.wms.service.Exceptions.DataNotFoundException;
import com.wms.service.Exceptions.FieldMissingException;
import com.wms.service.Exceptions.IllegalActionException;
import com.wms.service.Exceptions.ObjectExpiredException;
import com.wms.service.Exceptions.RegistrationException;
import com.wms.utils.mailing.exceptions.RegInfoNotFoundException;

@Service
public class AccountServiceImpl implements AccountService {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private GroupRepository groupRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ValidationRepository validationRepository;
	
	@Autowired
	private FriendPairRepository friendPairRepository;
	
	private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern
			.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	private static final int MIN_PASSWORD_LENGTH = 8;

	private static final int MAX_PASSWORD_LENGTH = 20;

	private static final int MIN_EMAIL_LENGTH = 6;

	private static final int MAX_EMAIL_LENGTH = 35;

	@Override
	public void createRootAccount(Map<String, Object> signup) {
		
		String email = (String) signup.get("email");
		String password = (String) signup.get("password");
		GroupType grouptype = GroupType.get((Integer) signup.get("grouptype"));
		Long invite_code = (Long) signup.get("invite");
		
		checkEmailAndPassword(email,password);
		
		if(grouptype == GroupType.TYPE_SELLER || grouptype == GroupType.TYPE_STORAGE) {
			// Parameter is save
			// create user group
			Group group = new Group().setType(grouptype)
					.setActivated(false)
					.setCompany((String) signup.get("company"))
					.setEmail(email)
					.setContact((Long) signup.get("contact"))
					.setLocation((String) signup.get("location"))
					.setFirstname((String) signup.get("firstname"))
					.setLastname((String) signup.get("lastname"));
			groupRepository.save(group);
			
			Group linker = null;
			if(invite_code != null) {
				linker = groupRepository.findBySpace(invite_code);
				if(linker == null || linker.getType() == grouptype)
					throw new RegistrationException("invalid invitation code");
				switch(linker.getType()) {
				case TYPE_SELLER:
					FriendPair g1 = new FriendPair(linker,group);
					friendPairRepository.save(g1);
					break;
				case TYPE_STORAGE:
					FriendPair g2 = new FriendPair(group,linker);
					friendPairRepository.save(g2);
					break;
				default:
				}
			}
			
			// create root user using given group
			User user = new User(group).setActivated(false).setEmail(email).setPassword(bCryptPasswordEncoder.encode(password));
			
			switch(grouptype) {
			case TYPE_SELLER:
				user.setRole(UserRole.SELLER);
				break;
			case TYPE_STORAGE:
				user.setRole(UserRole.STORAGE);
				break;
			default:
			}
			
			userRepository.save(user);
			
			// create validation info and send the validation email.
			Validation val = new Validation(user);
			validationRepository.save(val);
			
			sendValidationEmail(email, group.getId(), null, val);
		}else {
			throw new RegistrationException("invalid group type");
		}
	}

	@Override
	public void createSubAccount(Long groupid, String email) {
		// Get group space
		Group group = groupRepository.findOne(groupid);
		// check if the username is duplicated in this group
		User user = userRepository.findByEmailAndGroupId(email, groupid);
		if (user != null)
			throw new RegistrationException(email + " is duplicated under current groupspace");

		// create a sub user into the given group.
		String tempass = UUID.randomUUID().toString();
		tempass = tempass.substring(tempass.lastIndexOf('-'));
		
		user = new User(group)
				.setActivated(false)
				.setEmail(email)
				.setPassword(bCryptPasswordEncoder.encode(tempass));
		
		switch(group.getType()) {
		case TYPE_SELLER:
			user.setRole(UserRole.SELLER_SUB);
			break;
		case TYPE_STORAGE:
			user.setRole(UserRole.STORAGE_SUB);
			break;
		default:
		}
		
		userRepository.save(user);
		
		Validation val = new Validation(user);
		validationRepository.save(val);

		sendValidationEmail(email, groupid, tempass, val);
	}

	@Override
	public void removeSubAccount(Long groupid, Long userid) {
		User user = userRepository.findByIdAndGroupId(userid, groupid);
		if (user == null)
			throw new DataNotFoundException(userid.toString(), "User ID", "Sub user");

		// delete sub user from this group
		if (user.getRole() == UserRole.SELLER_SUB || user.getRole() == UserRole.STORAGE_SUB ) {
			try {
				new AccMailContent().addReceivers(user.getEmail())
									.setSubject("Your account has been removed")
									.setTextBody(String.format("The owner: %s %s removed your sub account <br/> "
											+ "If you have any question, please contact: %s"
											,user.getGroup().getFirstname(), user.getGroup().getLastname(), user.getGroup().getEmail()))
									.send();
				userRepository.delete(user);
			} catch (RegInfoNotFoundException | MessagingException e) {
				throw new RuntimeException("Send Notification Email failed");
			}
		}else {
			throw new RuntimeException("Executed target " + userid + " is not a sub account");
		}
	}

	@Override
	public User getAccountDetail(Long userid) {

		User user = userRepository.findOne(userid);
		entityManager.detach(user);

		return user.setPassword(null);
	}
	
	@Override
	public void validateAccount(Long userid, Long vid, String key) {
		Validation validation = validationRepository.findOne(vid);
		if(validation == null)
			throw new DataNotFoundException(vid.toString(), "vid", "validation attempt");

		User user = validation.getUser();
		
		if(user == null) {
			validationRepository.delete(validation);
			throw new RuntimeException("Mapping error");
		}else if(validation.getExpire() < new Date().getTime()) {
			validationRepository.delete(validation);
			throw new ObjectExpiredException("validation");
		}else if(!validation.getKey().equals(key) || !user.getId().equals(userid)) {
			throw new BadAuthParamException("invalid validation pair");
		}else {
			if(user.getRole() == UserRole.SELLER ||user.getRole() == UserRole.STORAGE)
				groupRepository.save(user.getGroup().setActivated(true));
			userRepository.save(user.setActivated(true));
			validationRepository.delete(validation);
		}
	}
	
	@Override
	public void resendValidation(Long groupid, String email) {
		User user = userRepository.findByEmailAndGroupId(email, groupid);
		if(user.getActivated())
			throw new IllegalActionException("account already activated.");
		Validation val = new Validation(user);
		sendValidationEmail(email, groupid, null, val);
	}
	
	@Override
	public void changePassword(Long groupid, String email, String oldpass, String newpass) {
		User user = userRepository.findByEmailAndGroupId(email, groupid);
		if(bCryptPasswordEncoder.matches(oldpass, user.getPassword()))
			user.setPassword(newpass);
		else
			throw new RegistrationException("old password incorrect");
	}

	@Override
	public void forgotPassword(Long groupid, String email) {
		// TODO Auto-generated method stub
		
	}

	private void checkEmailAndPassword(String email, String password) {
		//check if the email or password is missing
		if (email == null || password == null)
			throw new FieldMissingException("email/password");
		
		// check if the email is duplicated
		if (groupRepository.existsByEmail(email))
			throw new RegistrationException(email + " is already in use.");

		// check if the format is correct
		if (MIN_PASSWORD_LENGTH > password.length() || password.length() > MAX_PASSWORD_LENGTH
				|| !VALID_EMAIL_ADDRESS_REGEX.matcher(email).find() || MIN_EMAIL_LENGTH > email.length()
				|| email.length() > MAX_EMAIL_LENGTH)
			throw new RegistrationException("invalid request format");
	}

	private void sendValidationEmail(String email, Long groupid, String tempass, Validation val) {
		try {
			
			String add = "";
			String subject = "Activate your account";
			
			if(tempass != null) {
				Group group = groupRepository.findOne(groupid);
				add = "<br/> Temporary password is: " + tempass;
				subject = String.format("An invitaion from %s %s", group.getFirstname(), group.getLastname());
			}
				
			String vallink = String.format("http://ec2-3-84-110-44.compute-1.amazonaws.com/api/account/val?userid=%s&vid=%s&key=%s",
					val.getUser().getId(),
					val.getId(),
					val.getKey());
			
			new AccMailContent()
					.addReceivers(email)
					.setSubject(subject)
					.setTextBody("Please keep this group id to login <br/>" + "Group id: " + groupid + "<br/>"
							+ "Activation link: " + "<a href=\" " + vallink + " \"> Click Here </a>" + add)
					.send();
		} catch (RegInfoNotFoundException | MessagingException e) {
			throw new RegistrationException("Failed to send validation email: " + e.getMessage());
		}
	}

}
