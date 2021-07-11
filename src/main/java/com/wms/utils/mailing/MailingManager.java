package com.wms.utils.mailing;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.wms.utils.mailing.exceptions.RegInfoNotFoundException;
import com.wms.utils.mailing.exceptions.RegOverwriteNotAllowedException;

public class MailingManager {

	private static Map<String, InternetAddress> REG_MAP = new HashMap<>();
	private static Boolean CANOVERWRITE = false;
	private static Session SESSION = null;
	
	private final static String SMTP_USERNAME = "AKIA3PQHGGIDTQ74OHNI";

	private final static String SMTP_PASSWORD = "BD/VZQZ75gJ98Wj6QBZu3rLKby67FPkwToW5VOCZy3xA";
	
	private final static String HOST = "email-smtp.us-east-1.amazonaws.com";
	
	static {
		Properties props = System.getProperties();
    	props.put("mail.transport.protocol", "smtp");
    	props.put("mail.smtp.port", 587); 
    	props.put("mail.smtp.starttls.enable", "true");
    	props.put("mail.smtp.auth", "true");
    	SESSION = Session.getDefaultInstance(props);
	}

	public static boolean reg(String catagory, String email, String showname)
			throws RegOverwriteNotAllowedException, UnsupportedEncodingException {
		
		if (REG_MAP.containsKey(catagory)) {
			if(!CANOVERWRITE) {
				throw new RegOverwriteNotAllowedException(catagory);
			}
		}
		
		REG_MAP.put(catagory, new InternetAddress(email,showname));

		return true;
	}

	public static boolean unreg(String catagory) {
		if (REG_MAP.containsKey(catagory)) {
			REG_MAP.remove(catagory);
			return true;
		} else {
			return false;
		}
	}

	public static void regProtection(boolean b) {
		CANOVERWRITE = !b;
	}

	public static boolean sendMail(MailContent mailcontent) throws RegInfoNotFoundException, MessagingException {
		String catagory = mailcontent.getCatagory();

		if (!REG_MAP.containsKey(catagory)) {
			throw new RegInfoNotFoundException(catagory);
		}

		Message Msg = new MimeMessage(SESSION);
		Msg.setFrom(REG_MAP.get(catagory));
		Msg.addRecipients(Message.RecipientType.TO, InternetAddress.parse(mailcontent.getReceivers()));
		Msg.setSubject(mailcontent.getSubject());
		Msg.setContent(mailcontent.getMail_body(), mailcontent.getContent_type());
		Msg.setSentDate(new Date());

		Transport transport = SESSION.getTransport();

		System.out.println(String.join(",",HOST, SMTP_USERNAME, SMTP_PASSWORD));
		
		transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
		
		transport.sendMessage(Msg, Msg.getAllRecipients());
		
		return true;
	}	
}
