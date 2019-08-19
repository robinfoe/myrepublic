package my.com.common.scalar;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import my.com.common.enumeration.ServiceCategoryEnum;
import my.com.common.scalar.base.BaseEntity;

/**
 * SubscribeItem
 */

@Entity
@Table(name = "SUBSCRIBE_ITEM")
public class SubscribeItem extends BaseEntity{

    private static final long serialVersionUID = 1L;

    public SubscribeItem(){}
    public SubscribeItem(Subscribe item){this.subscribe = item;}

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    public Serializable getPk() {return this.getId();}

    @Getter 
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subscribe_id", nullable = false)
    private Subscribe subscribe;
    
    @Getter 
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SERVICE_ID", nullable = false)
    private Service service;
    
    @Getter 
    @Setter
    @Column(name = "VALUE",length = 255)
    private String value;
    
    @Getter 
    @Setter
    @Column(name = "TYPE", nullable = false,length = 10)
    private String type = ServiceCategoryEnum.MOBILE.getCode(); // T - Talk time, D - Data , B - Data Boost

}