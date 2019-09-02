package my.dal.service.base;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
public class BeanFactory {



    @PersistenceContext
    private EntityManager entityManager;




}