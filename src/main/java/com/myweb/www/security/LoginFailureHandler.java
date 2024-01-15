package com.myweb.www.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter @Setter
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

	private String authEmail;
	private String errorMsg;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		setAuthEmail(request.getParameter("email"));
		
		//exception 발생시 메시지 저장
		if(exception instanceof BadCredentialsException ||
				exception instanceof InternalAuthenticationServiceException) {
			setErrorMsg(exception.getMessage());
		}
		
		log.info("errorMsg >>>{}", errorMsg);
		
		request.setAttribute("email", getAuthEmail());
		request.setAttribute("errorMsg", getErrorMsg());
		request.getRequestDispatcher("/member/login?error").forward(request, response);

	}

}
