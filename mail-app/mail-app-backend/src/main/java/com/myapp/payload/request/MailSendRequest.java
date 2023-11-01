package com.myapp.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class MailSendRequest {

	@NotBlank
	@Size(max = 50)
	@Email
	private String fromEmailID;

	@NotBlank
	@Size(max = 50)
	@Email
	private String recipientEmailID;

	@NotBlank
	private String subject;

	@NotBlank
	private String body;

	private String attachmentID;

	public String getFromEmailID() {
		return fromEmailID;
	}

	public void setFromEmailID(String fromEmailID) {
		this.fromEmailID = fromEmailID;
	}

	public String getRecipientEmailID() {
		return recipientEmailID;
	}

	public void setRecipientEmailID(String recipientEmailID) {
		this.recipientEmailID = recipientEmailID;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getAttachmentID() {
		return attachmentID;
	}

	public void setAttachmentID(String attachmentID) {
		this.attachmentID = attachmentID;
	}
}