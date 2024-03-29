package com.redhat.facadeService.utils;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.redhat.facadeService.model.JwtResponse;


@Component
@Order(1)
public class AuthFilter implements Filter{
	Logger log=Logger.getLogger(this.getClass().getName());

	@Autowired
	private AuthService authService;
	
	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
			throws IOException, ServletException {
		log.info(">>>>>>>>>>>> inside filter");
		//get header
		String jwt=(String)(((HttpServletRequest)request).getHeader("Authorization")).substring(7);
		log.info("jwt: \n"+jwt);
		JwtResponse jwtResponse=authService.getInfo(jwt);
		if (jwtResponse.getStatus()==jwtResponse.getStatus().SUCCESS) {
			log.info("Got token with clientId:"+jwtResponse.getJws().getBody().get("clientId"));
			//log.info("scope:"+jwtResponse.getJws().getBody().get("scope"));
			
		} else {
			log.info("issue with token: "+jwtResponse.getExceptionType());
		}
		request.setAttribute("REQ_JWT_RESPONSE", jwtResponse);//probably not a good idea for production system
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	
}
