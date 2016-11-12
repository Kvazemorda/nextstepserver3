package com.nextstepserver.dao;

import com.nextstepserver.entity.BalanceEntity;
import com.nextstepserver.entity.PersonEntity;
import com.nextstepserver.hibernate.HibernateSessionFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.Date;

@Configuration
public class BalanceDAO implements CRUD {

    Session session = HibernateSessionFactory.getSessionFactory().openSession();

    @Override
    public void saveObject(Object o) {
        BalanceEntity balance = (BalanceEntity) o;
        session.beginTransaction();
        session.saveOrUpdate(balance);
        session.getTransaction().commit();
    }

    /**
     * What is BalanceEntity - it has two variable
     * 1. If Task don't complete then Balance equals sum(plan cash flow) or sum(current cash flow) - it depends from who
     * is biggest.
     * 2. If Task complete then BalanceEntity equals sum(cash flow for this Task)
     * Get balance on current date for current person
     * If balance don't exist then we create new BalanceEntity. Where we have two ways.
     * 1. If we have BalanceEntity before date when new balance equals balance before date plus
     * cash flow from before date to current date
     * 2. If we don't have the balance, it this time we sum all cash flow and create new Balance in this date
     * 3. If we don't have the balance and don't have cash flow we created balance with ZERO.
     * @param person
     * @param date
     * @return
     */
    public BalanceEntity getCurrentBalance(PersonEntity person, Date date){
        BalanceEntity balanceEntity = getBalance(person, date);
        if(balanceEntity != null){
            return balanceEntity;
        }
        return createNewBalance(person, date);
    }

    /**
     * Get balance for person in current date
     * @param person
     * @param date
     * @return BalanceEntity
     */
    private BalanceEntity getBalance(PersonEntity person, Date date){
        String hql = "select balance from BalanceEntity balance " +
                "where balance.personEntity = :person " +
                "and balance.dateBalance = :date";

        Query query = session.createQuery(hql);
        query.setParameter("person", person);
        query.setParameter("date", date);
        if(query.list().isEmpty()){
            return (BalanceEntity) query.list().get(0);
        }else {
            return null;
        }
    }

    /**
     * create new Balance. check on exist balance befor today, if list balance is not empty get first balance before
     * today,
     * if before balance is not exist create new BalanceEntity:
     * check cashflow befor today,
     * if sum cashflow is not empty create new Balance from (sum(Cashflow), person and today)
     * else create new Balance from(ZERO, person and today)
     * @param person
     * @param date
     * @return
     */
    private BalanceEntity createNewBalance(PersonEntity person, Date date){

        return null;
    }

    private BalanceEntity creteNewBalanceFromBalanceBefor(PersonEntity personEntity, Date date){

        return null;
    }

    private BalanceEntity createNewBalanceFromCashflow(PersonEntity personEntity, Date date){
        String hql = "select sum(cashflow.balance) from CashFlowEntity cashflow " +
                "where cashflow.taskByTask.targetByTarget.person = :person " +
                "and cashflow.date <= :date";

        Query query = session.createQuery(hql);
        query.setParameter("person", personEntity);
        query.setParameter("date", date);

        BigDecimal bigDecimal = (BigDecimal) query.list().get(0);
        BalanceEntity balanceEntity = new BalanceEntity(bigDecimal, date, personEntity);

        return balanceEntity;
    }

    /**
     * Create zero balance for person in current date
     * @param personEntity
     * @param date
     * @return BalanceEntity
     */
    private BalanceEntity craateZeroBalance(PersonEntity personEntity, Date date){
        BalanceEntity balanceEntity = new BalanceEntity(BigDecimal.ZERO, date, personEntity);

        return balanceEntity;
    }

    /**
     * Get a plan cash flow from task whose is not complete
     * @param personEntity
     * @param date
     * @return BigDecimal
     */
    private BigDecimal getPlanCashFlowForNotCompleteTask(PersonEntity personEntity, Date date){
        String hql = "select sum(task.planCashFlow) from TaskEntity task " +
                "where task.targetByTarget.person = :person " +
                "and task.dateStart = :date " +
                "and task.dateEnd is null";

        Query query = session.createQuery(hql);
        query.setParameter("person", personEntity);
        query.setParameter("date", date);

        BigDecimal bigDecimal = (BigDecimal) query.list().get(0);

        return bigDecimal;
    }
}
