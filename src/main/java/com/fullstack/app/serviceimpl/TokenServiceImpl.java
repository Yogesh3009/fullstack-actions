package com.fullstack.app.serviceimpl;

import org.keycloak.TokenVerifier;
import org.keycloak.common.VerificationException;
import org.keycloak.representations.AccessToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.fullstack.app.controller.EmployeeController;
import com.fullstack.app.service.TokenService;

@Service
public class TokenServiceImpl implements TokenService{

	private Logger logger=LoggerFactory.getLogger(TokenServiceImpl.class);
	
	@Override
	public String verifyToken(String token) {
		logger.info("Validating access token ....");
		boolean result=checkToken(token);
		if(result==true) {
			return "Token Verification Successful";
		}
		else {
			return "Invalid token";
		}
	}

	public boolean checkToken(String token)
	{
		boolean isCheck=false;
		try {
			AccessToken accessToken = TokenVerifier.create(token, AccessToken.class).getToken();
			if (!ObjectUtils.isEmpty(accessToken.getResourceAccess())) {			
				return isCheck=true;
			}
		} catch (VerificationException e) {
			logger.error("Problem while validating access token", e);
		}
		return isCheck;
	}
}
