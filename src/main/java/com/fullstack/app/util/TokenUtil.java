package com.fullstack.app.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.crc.commonlib.exception.custom.JWTValidationTokenFailed;
import com.fullstack.app.controller.EmployeeController;
import com.fullstack.app.dto.AuthUser;
import com.fullstack.app.dto.TokenRequestDTO;
import com.fullstack.app.dto.TokenResponse;
import com.fullstack.app.exception.ServiceUnavailbleException;

@Component
public class TokenUtil {

	private Logger logger=LoggerFactory.getLogger(TokenUtil.class);
	@Autowired
	private RestTemplate restTemplate;
	

	public TokenResponse getToken(TokenRequestDTO requestDto) throws ServiceUnavailbleException {

		String url = "http://localhost:8080/auth/realms/master/protocol/openid-connect/token";
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
			map.add("grant_type", requestDto.getGrant_type());
			map.add("client_secret", requestDto.getClient_secret());
			map.add("client_id", requestDto.getClient_id());
			map.add("scope", requestDto.getScope());
			map.add("username", requestDto.getUsername());
			map.add("password", requestDto.getPassword());
			HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

			ResponseEntity<TokenResponse> response = restTemplate.exchange(url, HttpMethod.POST, request,
					TokenResponse.class);

			return response.getBody();

		} catch (Exception re) {
			throw new ServiceUnavailbleException("Unable to generate token");
		}

	}
	
	

}
