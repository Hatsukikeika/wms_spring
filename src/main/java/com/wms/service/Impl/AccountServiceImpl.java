package com.wms.service.Impl;

import java.util.Date;
import java.util.Map;
import java.util.regex.Pattern;

import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.wms.DAO.CompMemberRepository;
import com.wms.DAO.CompanyRepository;
import com.wms.DAO.FriendPairRepository;
import com.wms.DAO.UserRepository;
import com.wms.DAO.ValidationRepository;
import com.wms.bean.Company;
import com.wms.bean.Inventory;
import com.wms.bean.User;
import com.wms.bean.Validation;
import com.wms.bean.enu.GroupType;
import com.wms.bean.enu.UserRole;
import com.wms.bean.relations.mtm.CompanyMember;
import com.wms.bean.relations.mtm.FriendPair;
import com.wms.model.AccMailContent;
import com.wms.service.AccountService;
import com.wms.service.Exceptions.BadAuthParamException;
import com.wms.service.Exceptions.DataNotFoundException;
import com.wms.service.Exceptions.FieldMissingException;
import com.wms.service.Exceptions.ObjectExpiredException;
import com.wms.service.Exceptions.RegistrationException;
import com.wms.utils.mailing.exceptions.RegInfoNotFoundException;

@Service
public class AccountServiceImpl implements AccountService {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private CompMemberRepository compMemberRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ValidationRepository validationRepository;

	@Autowired
	private FriendPairRepository friendPairRepository;
	
	@Autowired
	private InventoryRepository inventoryRepository;

	private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern
			.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	// Password length must between 7-35 character.
	private static final int MIN_PASSWORD_LENGTH = 7;

	private static final int MAX_PASSWORD_LENGTH = 35;

	// Email length must between 6-35 character.
	private static final int MIN_EMAIL_LENGTH = 6;

	private static final int MAX_EMAIL_LENGTH = 35;

	@Override
	public void createRootAccount(Map<String, Object> signup) {
		String email = (String) signup.get("email");
		String password = (String) signup.get("password");

		GroupType gtype = GroupType.get((Integer) signup.get("grouptype"));
		String invcode = (String) signup.get("invite");

		checkEmailAndPassword(email, password);

		if (gtype == GroupType.TYPE_SELLER || gtype == GroupType.TYPE_STORAGE) {
			// Parameter is save
			// create user group
			Company company = new Company().setType(gtype).setActivated(true).setName((String) signup.get("cname"));
			Inventory inventory = new Inventory(company);
			companyRepository.save(company);
			inventoryRepository.save(inventory);

			Company linker = null;
			if (invcode != null) {
				linker = companyRepository.findByInvcode(invcode);
				if (linker == null || linker.getType() == gtype)
					throw new RegistrationException("invalid invitation code");
				switch (linker.getType()) {
				case TYPE_SELLER:
					FriendPair g1 = new FriendPair(linker, company);
					friendPairRepository.save(g1);
					break;
				case TYPE_STORAGE:
					FriendPair g2 = new FriendPair(company, linker);
					friendPairRepository.save(g2);
					break;
				default:
				}
			}

			// create root user using given group
			User user = new User(company)
					.setFirstname((String) signup.get("firstname"))
					.setLastname((String) signup.get("lastname"))
					.setActivated(false).setEmail(email)
					.setPassword(bCryptPasswordEncoder.encode(password));

			CompanyMember cm = new CompanyMember();
			cm.setCompany(company).setMember(user);
			
			switch (gtype) {
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

			compMemberRepository.save(cm);
			
			//sendValidationEmail(email, company.getId(), null, val);

		} else {
			throw new RegistrationException("invalid group type");
		}

	}

	@Override
	public void createSubAccount(Long groupid, String email) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeSubAccount(Long groupid, Long userid) {
		// TODO Auto-generated method stub

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
				companyRepository.save(user.getOwnedCompany().setActivated(true));
			userRepository.save(user.setActivated(true));
			validationRepository.delete(validation);
		}

	}

	@Override
	public void resendValidation(Long groupid, String email) {
		// TODO Auto-generated method stub

	}

	@Override
	public void changePassword(Long groupid, String email, String oldpass, String newpass) {
		// TODO Auto-generated method stub

	}

	@Override
	public void forgotPassword(Long groupid, String email) {
		// TODO Auto-generated method stub

	}

	@Override
	public User getAccountDetail(Long userid) {
		
		User user = userRepository.findOne(userid);
		entityManager.detach(user);

		return user.setPassword(null);
	}

	private void checkEmailAndPassword(String email, String password) {
		// check if the email or password is missing
		if (email == null || password == null)
			throw new FieldMissingException("email/password");

		// check if the email is duplicated
		if (userRepository.existsByEmail(email))
			throw new RegistrationException(email + " is already in use.");

		// check if the format is correct
		if (MIN_PASSWORD_LENGTH > password.length() || password.length() > MAX_PASSWORD_LENGTH
				|| !VALID_EMAIL_ADDRESS_REGEX.matcher(email).find() || MIN_EMAIL_LENGTH > email.length()
				|| email.length() > MAX_EMAIL_LENGTH)
			throw new RegistrationException("invalid request format");
	}

	private void sendValidationEmail(String email, Long compid, String tempass, Validation val) {
		try {

			String add = "";
			String subject = "Activate your account";

			Company company = companyRepository.findOne(compid);

			if (tempass != null) {
				add = "<br/> Temporary password is: " + tempass;
				subject = String.format("An invitaion from %s %s", company.getOwner().getFirstname(),
						company.getOwner().getLastname());
			}

			String vallink = String.format(
					"http://ec2-3-84-110-44.compute-1.amazonaws.com/api/account/val?userid=%s&vid=%s&key=%s",
					val.getUser().getId(), val.getId(), val.getKey());

			new AccMailContent().addReceivers(email).setSubject(subject)
					.setTextBody("Please remember your company name to login <br/>" + "Company Name: "
							+ company.getName() + "<br/>" + "Activation link: " + "<a href=\" " + vallink
							+ " \"> Click Here </a>" + add)
					.send();
		} catch (RegInfoNotFoundException | MessagingException e) {
			throw new RegistrationException("Failed to send validation email: " + e.getMessage());
		}
	}

}
