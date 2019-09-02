package my.dal.service.impl;

import javax.inject.Singleton;
import javax.persistence.EntityManager;

import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import my.com.common.scalar.Customer;
// import my.dal.sevice.CustomerService;
import my.dal.service.CustomerService;
import my.dal.service.base.BaseService;

/**
 * CustomerServiceImpl
 */
@Singleton
public class CustomerServiceImpl extends BaseService<Customer> implements CustomerService{

    public CustomerServiceImpl(@CurrentSession EntityManager entityManager){super(entityManager);}

    
}