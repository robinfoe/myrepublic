package my.com.common.scalar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import my.com.common.scalar.base.BaseEntity;

/**
 * Plan
 */
@Entity
@Table(name = "PLAN", uniqueConstraints={@UniqueConstraint(columnNames = "CODE")} )
public class Plan extends BaseEntity{


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
    @Column(name = "DESCRIPTION", nullable = true,length = 255)
    private String description;

    @Getter 
    @Setter
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "plan")
    private List<PlanDetail> details = new ArrayList<PlanDetail>();
    public PlanDetail addDetail(){
        PlanDetail item = new PlanDetail(this);
        this.details.add(item);
        return item;
    }

}