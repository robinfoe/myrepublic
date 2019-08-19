package my.com.common.scalar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;
import my.com.common.enumeration.SubscriberType;
import my.com.common.scalar.base.BaseEntity;

/**
 * Subscribe
 */
@Entity
@Table(name = "SUBSCRIBE")
public class Subscribe extends BaseEntity{

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    public Serializable getPk() {return this.getId();}


    @Getter 
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMER_ID", nullable = false)
    private Customer customer;

    @Getter 
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PLAN_ID", nullable = false)
    private Plan plan;

    @Getter 
    @Setter
    @Column(name = "TYPE", nullable = false,length = 10)
    private String type = SubscriberType.CONTRACT.getCode(); // C - Contract , N - Non contract
    
    @Getter 
    @Setter
    @Basic(optional = false)
    @Column(name = "START_DATE", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date start;
    
    @Getter 
    @Setter
    @Basic(optional = false)
    @Column(name = "END_DATE", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date end;


    @Getter 
    @Setter
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "subscribe")
    private List<SubscribeItem> details = new ArrayList<SubscribeItem>();
    public SubscribeItem addDetail(){
        SubscribeItem item = new SubscribeItem(this);
        this.details.add(item);
        return item;
    }

}