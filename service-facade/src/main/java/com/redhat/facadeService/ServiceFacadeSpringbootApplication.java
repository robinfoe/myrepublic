package com.redhat.facadeService;

import javax.servlet.http.HttpServlet;

import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServiceFacadeSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceFacadeSpringbootApplication.class, args);
	}
	
	
    @Bean
    ServletRegistrationBean servletRegistrationBean() {
        ServletRegistrationBean servlet = new ServletRegistrationBean(new CamelHttpTransportServlet(),"/*");
        servlet.setName("CamelServlet");
        return servlet;
    }
}
