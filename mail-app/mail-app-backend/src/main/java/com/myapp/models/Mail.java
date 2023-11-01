package com.myapp.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Document(collection = "mails")
public class Mail {
	@Id
	private String id;

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

	@NotBlank
	@Size(max = 20)
	private String uploadedBy;

	private Date uploadedDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getUploadedBy() {
		return uploadedBy;
	}

	public void setUploadedBy(String uploadedBy) {
		this.uploadedBy = uploadedBy;
	}

	public Date getUploadedDate() {
		return uploadedDate;
	}

	public void setUploadedDate(Date uploadedDate) {
		this.uploadedDate = uploadedDate;
	}
}