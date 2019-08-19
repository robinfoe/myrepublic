package my.com.common.scalar;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import my.com.common.scalar.base.BaseEntity;

/**
 * Customer
 */

@Entity
@Table(name = "CUSTOMER")
public class Customer extends BaseEntity{

    private static final long serialVersionUID = 1L;

    @Getter 
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Override
    public Serializable getPk() {return this.getId();}


    @Getter
    @Setter
    @NotNull
    @Column(name = "ID_NUMBER", nullable = false,length = 20)
    private String idnumber;
    
    @Getter 
    @Setter
    @NotNull
    @Column(name = "NAME", nullable = false,length = 255)
    private String name;
    
    @Getter 
    @Setter
    @NotNull
    @Column(name = "ADDRESS", nullable = true,length = 255)
    private String address;
    
    @Getter 
    @Setter
    @NotNull
    @Column(name = "EMAIL", nullable = true,length = 255)
    private String email;
    
}