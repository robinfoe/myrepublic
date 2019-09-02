package com.redhat.facadeService.routes;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MediaType;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

import com.redhat.facadeService.model.JwtResponse;
import com.redhat.facadeService.model.UserObject;

@Component
public class MainRoute extends RouteBuilder{
	
	Logger log=Logger.getLogger(this.getClass().getName());

	@Override
	public void configure() throws Exception {
        rest("/customers")
        .get("/{id}").to("direct:getCustomer")
        //.post("/newCustomer").consumes("application/json").to("direct:newCustomer")
        .produces(MediaType.APPLICATION_JSON)
        .consumes(MediaType.APPLICATION_JSON)            
        .type(UserObject.class)
        .bindingMode(RestBindingMode.auto);
        
        
        from("direct:getCustomer")
        .convertBodyTo(String.class, "UTF-8")
        .process(new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {
            	log.info("Inside processor");
                HttpServletRequest request = exchange.getIn().getBody(HttpServletRequest.class);
                JwtResponse jwtResponse=(JwtResponse)request.getAttribute("REQ_JWT_RESPONSE");
        		String jwt=(String)request.getHeader("Authorization");//.substring(7);
        		exchange.getOut().setHeader("Authorization", jwt);
        		log.info("access token:"+jwt);
        		log.info("scope:"+jwtResponse.getJws().getBody().get("scope"));
                
            }
        })    
            .setHeader(Exchange.HTTP_METHOD).constant(HttpMethod.GET)
            .to("http://y?host=yahoo.com")
            .log("Response : ${body}");

	}
}
