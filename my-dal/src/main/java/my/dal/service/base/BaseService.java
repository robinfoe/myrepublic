package my.dal.service.base;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotNull;

import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;
import my.com.common.constant.ConstantStatus;
import my.com.common.scalar.base.BaseEntity;

/**
 * BaseService
 */
public abstract class BaseService<T extends BaseEntity>{

    private Class<T> klass = null;

    @PersistenceContext
    protected EntityManager em;
    @SuppressWarnings("unchecked")
    public BaseService(@CurrentSession EntityManager entityManager){


        this.em = entityManager;
        this.klass = (Class<T>) ((ParameterizedType) this.getClass().getSuperclass().getGenericSuperclass()).getActualTypeArguments()[0];

        // System.err.println("@@@@@ " +   ((ParameterizedType) this.getClass().getSuperclass().getGenericSuperclass()).getActualTypeArguments()[0] );
        // this.klass =  (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }


    

    @Transactional
    public T save(@NotNull T item){
        this.em.persist(item);
        return item;
    }

    @Transactional
    public T delete(@NotNull T item){
        item = this.getByPk((Long)item.getPk());
        item.setStatus(ConstantStatus.DELETED);
        this.em.persist(item);
        return item;
    }

    @Transactional(readOnly = true)
    public T getByPk(Long pk){
        return em.find(this.klass, pk);
    }

    @Transactional(readOnly = true)
    public List<T> getAll(){
       List<T> result=  this.em.createQuery("from "+ this.klass.getName(),this.klass).getResultList();
        return result;
    }

}