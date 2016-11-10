package com.nextstepserver.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TASK")
public class TaskEntity implements Comparable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false) private long id;
    @Basic @Column private String title;
    @Basic @Column private String description;
    @Basic @Column private int potato;
    @Temporal(TemporalType.DATE) @Column private Date dateStart;
    @Temporal(TemporalType.DATE) @Column private Date dateEnd;
    @ManyToOne private TargetEntity targetByTarget;
    @Basic @Column private BigDecimal planCashFlow;
    @OneToMany private Set<CashFlowEntity> cashFlowsById = new HashSet<>();
    @OneToMany private Set<TaskEntity> taskChild = new HashSet<>();

    public TaskEntity() {
    }

    public TaskEntity(String title, TargetEntity targetByTarget, Date dateStart, Set<TaskEntity> taskChild) {
        this.title = title;
        this.dateStart = dateStart;
        this.taskChild = taskChild;
        this.targetByTarget = targetByTarget;
    }

    public TaskEntity(String title, TargetEntity targetByTarget, Date dateStart) {
        this.title = title;
        this.dateStart = dateStart;
        this.targetByTarget = targetByTarget;
    }

    public TaskEntity(String title, TargetEntity targetByTarget) {
        this.title = title;
        this.targetByTarget = targetByTarget;
    }

    public TaskEntity(String title, TargetEntity targetByTarget, BigDecimal planCashFlow) {
        this.title = title;
        this.targetByTarget = targetByTarget;
        this.planCashFlow = planCashFlow;
    }


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }


    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }


    public int getPotato() {
        return potato;
    }
    public void setPotato(int potato) {
        this.potato = potato;
    }


    public Date getDateStart() {
        return dateStart;
    }
    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }
    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public BigDecimal getPlanCashFlow() {
        return planCashFlow;
    }
    public void setPlanCashFlow(BigDecimal planCashFlow) {
        this.planCashFlow = planCashFlow;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskEntity that = (TaskEntity) o;

        if (id != that.id) return false;
        if (potato != that.potato) return false;
        if (dateEnd != null ? !dateEnd.equals(that.dateEnd) : that.dateEnd != null) return false;
        if (dateStart != null ? !dateStart.equals(that.dateStart) : that.dateStart != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int)id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + potato;
        result = 31 * result + (dateStart != null ? dateStart.hashCode() : 0);
        result = 31 * result + (dateEnd != null ? dateEnd.hashCode() : 0);
        return result;
    }

    public Set<CashFlowEntity> getCashFlowsById() {
        return cashFlowsById;
    }
    public void setCashFlowsById(Set<CashFlowEntity> cashFlowsById) {
        this.cashFlowsById = cashFlowsById;
    }

    public TargetEntity getTargetByTarget() {
        return targetByTarget;
    }
    public void setTargetByTarget(TargetEntity targetByTarget) {
        this.targetByTarget = targetByTarget;
    }

    public Set<TaskEntity> getTaskChild() {
        return taskChild;
    }
    public void setTaskChild(Set<TaskEntity> taskChild) {
        this.taskChild = taskChild;
    }

    @Override
    public String toString() {
        return "title='" + title + '\'' +
                ", dateStart=" + dateStart +
                ", cashFlowsById=" + cashFlowsById;
    }

    @Override
    public int compareTo(Object o) {
        TaskEntity taskEntity = (TaskEntity) o;
        if(this.getTaskChild().contains(taskEntity)){
            return 1;
        }else
        return -1;
    }
}
