package com.wms.utils.mailing;

import javax.mail.MessagingException;

import com.wms.utils.mailing.exceptions.RegInfoNotFoundException;

public class MailContent {
	private String content_type = "text/html;charset=UTF-8";
	
	private String catagory = "DEFAULT";
	
	private String mail_body;
	
	private String subject;
	
	private String receivers;

	public MailContent() {
		super();
	}

	public String getContent_type() {
		return content_type;
	}

	public MailContent setContent_type(String _content_type) {
		this.content_type = _content_type;
		return this;
	}

	public String getCatagory() {
		return catagory;
	}

	public MailContent setCatagory(String _catagory) {
		this.catagory = _catagory;
		return this;
	}

	public String getMail_body() {
		return mail_body;
	}
	
	public MailContent setTextBody(String _text) {
		this.mail_body = _text;
		return this;
	}

	public String getReceivers() {
		return receivers;
	}

	public MailContent addReceivers(String _receivers) {
		this.receivers = _receivers;
		return this;
	}
	
	public String getSubject() {
		return subject;
	}

	public MailContent setSubject(String _subject) {
		this.subject = _subject;
		return this;
	}

	public void send() throws RegInfoNotFoundException, MessagingException {
		MailingManager.sendMail(this);
	}
}
