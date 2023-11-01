package com.myapp.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.models.Mail;
import com.myapp.payload.request.MailSendRequest;
import com.myapp.payload.response.MessageResponse;
import com.myapp.repository.MailRepository;
import com.myapp.repository.RoleRepository;
import com.myapp.security.jwt.JwtUtils;
import com.myapp.security.services.UserDetailsImpl;

import jakarta.validation.Valid;

//@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowedHeaders = {"*"}, methods = {RequestMethod.OPTIONS, RequestMethod.POST, RequestMethod.GET})
@RestController
@RequestMapping("/api/mail")
public class MailController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	MailRepository mailRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/send")
	//@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?> sendMail(@Valid @RequestBody MailSendRequest mailSendRequest) {
		mailRepository.save(transformMailRequest(mailSendRequest));
	    UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString()).body(new MessageResponse("Mail sent successfully!"));
	}

	@PostMapping("/attachment/upload")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?> uploadMailAttachment(@Valid @RequestBody MailSendRequest mailSendRequest) {
		mailRepository.save(transformMailRequest(mailSendRequest));
		return ResponseEntity.ok(new MessageResponse("Mail sent successfully!"));
	}

	@PostMapping("/attachment/download")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?> downloadMailAttachment(@Valid @RequestBody MailSendRequest mailSendRequest) {
		mailRepository.save(transformMailRequest(mailSendRequest));
		return ResponseEntity.ok(new MessageResponse("Mail sent successfully!"));
	}

	@GetMapping("/all-user-mails")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?> getAllMailByUsername() {
		return ResponseEntity.ok(mailRepository.findByUploadedBy(getUsername()));
	}

	@GetMapping("/all-mails")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getAllMails() {
		return ResponseEntity.ok(mailRepository.findAll());
	}

	private String getUsername() {
		UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		return userDetails.getUsername();
	}

	private Mail transformMailRequest(MailSendRequest mailSendRequest) {
		Mail mail = new Mail();
		mail.setFromEmailID(mailSendRequest.getFromEmailID());
		mail.setRecipientEmailID(mailSendRequest.getRecipientEmailID());
		mail.setSubject(mailSendRequest.getSubject());
		mail.setBody(mailSendRequest.getBody());
		mail.setAttachmentID(mailSendRequest.getAttachmentID());
		mail.setUploadedBy(getUsername());
		mail.setUploadedDate(new Date());
		return mail;
	}
}