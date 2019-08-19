package my.dal.controller.base;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import my.com.common.scalar.base.BaseEntity;
import my.dal.service.base.MyService;

/**
 * BaseController
 */
public abstract class BaseController<T extends BaseEntity> {

    private static final Logger log = LoggerFactory.getLogger(BaseController.class);


    @Get(uri = "/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public T getByid(@PathVariable Integer id){
        T item = this.getService().getByPk(id);
        return item;
    }    

    @Get(uri = "/list/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<T> listAll(){
       return this.getService().getAll();
    }

    @Post
    @Produces(MediaType.APPLICATION_JSON)
    public T save(@Body T item){
        log.info("@@@ Save....");
        this.getService().save(item);
        return item;
    }

    @Delete
    @Produces(MediaType.APPLICATION_JSON)
    public T delete(@Body T item){
        this.getService().delete(item);
        return item;
    }

    
    // @Post
    // @Produces(MediaType.APPLICATION_JSON)
    // public T saveCustomer(@Body T item){
    //     this.getService().save(item);
    //     return item;
    // }



    /** ABSTRACTIONS */
    abstract protected MyService<T> getService();
    
}