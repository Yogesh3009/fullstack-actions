package com.fullstack.app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.fullstack.app.dto.TokenRequestDTO;
import com.fullstack.app.dto.TokenResponse;
import com.fullstack.app.exception.ServiceUnavailbleException;
import com.fullstack.app.service.TokenService;
import com.fullstack.app.util.TokenUtil;

@RestController
public class TokenController {

	@Autowired
	private TokenUtil utils;
	
	@Autowired
	private TokenService service;
	
	@PostMapping("/token/generate")
	public ResponseEntity<TokenResponse> generateToken(TokenRequestDTO requestDto) throws ServiceUnavailbleException {
		return new ResponseEntity<>(utils.getToken(requestDto), HttpStatus.OK);
	}
	
	 @PostMapping("/token/verify")
	 public ResponseEntity<String> verifyToken(@RequestHeader String token) {
	        return new ResponseEntity<>(service.verifyToken(token),HttpStatus.OK);
		}
}
