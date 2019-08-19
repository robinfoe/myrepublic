package my.dal.service.base;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotNull;

import io.micronaut.spring.tx.annotation.Transactional;
import my.com.common.constant.ConstantStatus;
import my.com.common.scalar.base.BaseEntity;

/**
 * BaseService
 */
public abstract class BaseService<T extends BaseEntity>{

    private Class<T> klass = null;

    @SuppressWarnings("unchecked")
    public BaseService(){


        
        this.klass = (Class<T>) ((ParameterizedType) this.getClass().getSuperclass().getGenericSuperclass()).getActualTypeArguments()[0];

        // System.err.println("@@@@@ " +   ((ParameterizedType) this.getClass().getSuperclass().getGenericSuperclass()).getActualTypeArguments()[0] );
        // this.klass =  (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }


    @PersistenceContext
    protected EntityManager em;

    @Transactional
    public T save(@NotNull T item){
        this.em.persist(item);
        return item;
    }

    @Transactional
    public T delete(@NotNull T item){
        item = this.getByPk((Integer)item.getPk());
        item.setStatus(ConstantStatus.DELETED);
        this.em.persist(item);
        return item;
    }

    public T getByPk(Integer pk){
        return em.find(this.klass, pk);
    }

    public List<T> getAll(){
       List<T> result=  this.em.createQuery("from "+ this.klass.getName(),this.klass).getResultList();
        return result;
    }

}