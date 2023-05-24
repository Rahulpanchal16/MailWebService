package com.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.email.model.EmailRequest;
import com.email.services.EmailService;

@RestController
public class EmailController {

	@Autowired
	private EmailService service;

	@GetMapping(path = "/welcome")
	public String welcome() {
		return "Hello, this is an email api";
	}

	// Handler to send the E-mail
	@PostMapping(path = "/send-email")
	public ResponseEntity<?> sendEmail(@RequestBody EmailRequest request) {

		boolean b = this.service.sendEmail(request.getSubject(), request.getMessage(), request.getTo());
		if (b) {
			return ResponseEntity.status(HttpStatus.OK).body("Email sent successfully");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Email not sent");
		}
	}
}
