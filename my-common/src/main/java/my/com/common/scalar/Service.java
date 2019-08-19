package my.com.common.scalar;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import my.com.common.enumeration.ServiceCategoryEnum;
import my.com.common.scalar.base.BaseEntity;

/**
 * Service
 */

@Entity
@Table(name = "SERVICE", uniqueConstraints={@UniqueConstraint(columnNames = "CODE")} )
public class Service extends BaseEntity{

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    public Serializable getPk() {return this.getId();}


    @Getter 
    @Setter
    @NotNull
    @Column(name = "CODE", nullable = false,length = 20)
    private String code;
    
    @Getter 
    @Setter
    @Column(name = "DESCRIPTION", length = 255)
    private String description;

    @Getter 
    @Setter
    @NotNull
    @Column(name = "CATEGORY", nullable = false,length = 20)
    private String category = ServiceCategoryEnum.MOBILE.getCode(); // M - Mobile, D - data , B data boost

    @Getter 
    @Setter
    @Column(name = "VALUE", length = 255)
    private String value;

}