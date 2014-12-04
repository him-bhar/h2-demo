package com.himanshu.poc.h2.springboot;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class TokenAuthenticationProcessingFilter extends
		AbstractAuthenticationProcessingFilter {

	public TokenAuthenticationProcessingFilter(
			RequestMatcher requiresAuthenticationRequestMatcher) {
		super(requiresAuthenticationRequestMatcher);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException,
			IOException, ServletException {
		Authentication auth = new TokenAuthentication("-1");
		try {
			Map<String, String[]> params = request.getParameterMap();
			if (!params.isEmpty() && params.containsKey("auth_token")) {
				String token = params.get("auth_token")[0];
				if (token != null) {
					auth = new TokenAuthentication(token);
				}
			}
			return this.getAuthenticationManager().authenticate(auth);
		} catch (AuthenticationException ae) {
			unsuccessfulAuthentication(request, response, ae);
		}
		return auth;
	}
}