package com.redhat.facadeService.utils;

import org.apache.camel.Body;
import org.apache.camel.Exchange;
import org.apache.camel.Header;

import com.redhat.facadeService.model.UserObject;

public class CustomerBean {

	public void test(@Header("user") String user, @Body String body, Exchange exchange) {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>"+exchange.getIn().getBody());
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>"+body+" "+user);
		exchange.getOut().setBody(user);
		//exchange.getOut().setBody("123");
	}
	public void test1(Exchange exchange) {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>"+((UserObject)exchange.getIn().getBody()).getName());
		exchange.getOut().setBody("123");
	}	
}
