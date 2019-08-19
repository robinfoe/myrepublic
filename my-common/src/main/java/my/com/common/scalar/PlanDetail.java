package my.com.common.scalar;

import java.io.Serializable;

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
import my.com.common.scalar.base.BaseEntity;

/**
 * PlanDetail
 */

@Entity
@Table(name = "PLAN_DETAIL")
public class PlanDetail extends BaseEntity{

    private static final long serialVersionUID = 1L;

    public PlanDetail(){};
    public PlanDetail(Plan item){
        this.plan = item;
    }

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    public Serializable getPk() {return this.getId();}


    @Getter 
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PLAN_ID", nullable = false)
    private Plan plan;
    
    @Getter 
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SERVICE_ID", nullable = false)
    private Service service;
    
}