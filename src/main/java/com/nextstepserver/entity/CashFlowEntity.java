package com.nextstepserver.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "CASHFLOW")
public class CashFlowEntity implements Comparable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false) private long id;
    @Basic @Column private BigDecimal balance;
    @Basic @Column private String title;
    @Temporal(TemporalType.DATE) @Column(nullable = false) private Date date;
    @ManyToOne private TaskEntity taskByTask;

    public CashFlowEntity() {
    }

    public CashFlowEntity(BigDecimal balance, String title, Date date, TaskEntity taskByTask) {
        this.balance = balance;
        this.title = title;
        this.date = date;
        this.taskByTask = taskByTask;
    }

    public CashFlowEntity(BigDecimal balance, String title, Date date) {
        this.balance = balance;
        this.title = title;
        this.date = date;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CashFlowEntity that = (CashFlowEntity) o;

        if (balance != that.balance) return false;
        if (id != that.id) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) id;
        temp = Long.parseLong(String.valueOf(balance));
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }

    public TaskEntity getTaskByTask() {
        return taskByTask;
    }
    public void setTaskByTask(TaskEntity taskByTask) {
        this.taskByTask = taskByTask;
    }

    @Override
    public int compareTo(Object o) {
        CashFlowEntity cashFlowEntity = (CashFlowEntity) o;
        if(this.date.before(cashFlowEntity.date)){
            return -1;
        }
        return 1;
    }
}
