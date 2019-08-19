package my.com.common.scalar.base;

import java.io.Serializable;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import my.com.common.constant.ConstantStatus;

/**
 * BaseEntity
 */
public abstract class BaseEntity implements Serializable{

    private static final long serialVersionUID = 2932183180997443414L;
    

    @NotNull
    @Column(name = "STATUS", nullable = false,length = 20)
    private String Status = ConstantStatus.ACTIVE;
    public String getStatus() {return Status;}
    public void setStatus(String status) {this.Status = status;}    

    /**** HELPER METHODS....  */
    abstract public Serializable getPk();
    
}