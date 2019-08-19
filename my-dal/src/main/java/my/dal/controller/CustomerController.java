package my.dal.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import my.com.common.scalar.Customer;
import my.dal.controller.base.BaseController;
import my.dal.service.CustomerService;
import my.dal.service.base.MyService;

/**
 * CustomerController
 */

@Controller("/api/dal/customer")
public class CustomerController extends BaseController<Customer>{

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    @Inject
    private CustomerService service;

    protected MyService<Customer> getService(){
        log.info("@@@ getService....");
        return this.service;
    }

    
    // @Get(consumes = MediaType.APPLICATION_JSON)
    // @Produces(MediaType.APPLICATION_JSON)
    // public Customer getCustomer(@Body Customer customer){
    //     //Customer customer = new Customer();
    //     //customer.setAddress("hello world ??? ");

    //     log.info(customer.getName());

    //     return customer;
    // }    

    // @Post
    // @Produces(MediaType.APPLICATION_JSON)
    // public Customer saveCustomer(@Body Customer customer){
    //     //Customer customer = new Customer();
    //     //customer.setAddress("hello world ??? ");
    //     log.info(customer.getName());
    //     this.service.save(customer);

    //     return customer;
    // }    
}