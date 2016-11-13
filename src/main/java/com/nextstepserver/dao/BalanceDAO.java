package com.nextstepserver.dao;

import com.nextstepserver.entity.BalanceEntity;
import com.nextstepserver.entity.PersonEntity;
import com.nextstepserver.hibernate.HibernateSessionFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.Date;

import static java.math.BigDecimal.*;

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
     * We get balance of complete task previous current date. Balance for today computing in mobile app from list task
     * If balance don't exist then we create new BalanceEntity. Where we have three ways.
     * 1. If we have BalanceEntity before today we return this.
     * 2. If we don't have the before balance, it this time we sum all cash flow and create new Balance in before date
     * 3. If we don't have the balance and don't have cash flow we created balance with ZERO.

     * Get balance for person in before date
     * @param person
     * @param date
     * @return BalanceEntity
     */
    public BalanceEntity getBalance(PersonEntity person, Date date){
        String hql = "select balance from BalanceEntity balance " +
                "where balance.personEntity = :person " +
                "and balance.dateBalance < :date";

        Query query = session.createQuery(hql);
        query.setParameter("person", person);
        query.setParameter("date", date);
        if(!query.list().isEmpty() || query.list().size() != 0){
                BalanceEntity previousBalance = (BalanceEntity) query.list().get(0);
                BigDecimal CFBetweenPrevious = getCFBetweenPreviousAndYestardqyBalance(previousBalance.getDateBalance()
                ,date);
            //if cash flow exist between previousBalance and before current day, we add sum cash flow to previous Balance
            if(CFBetweenPrevious != null){
                previousBalance.setBalance(previousBalance.getBalance().add(CFBetweenPrevious));
                saveObject(previousBalance);
            }
            return previousBalance;
        }else {
            return createNewBalance(person, date);
        }
    }

    /**
     * If previous balance not yesterday, need add sum cash flow from previous Balance and before current Balance
     * @param previousDate
     * @param today
     * @return
     */
    private BigDecimal getCFBetweenPreviousAndYestardqyBalance(Date previousDate, Date today){
        String hql = "select sum(cashflow.balance) from CashFlowEntity cashflow " +
                "where cashflow.date > :previousDate " +
                "and cashflow.date < :today";

        Query query = session.createQuery(hql);
        query.setParameter("previousDate", previousDate);
        query.setParameter("today", today);
        BigDecimal cashflow = (BigDecimal) query.list().get(0);
        if ((query.list().size() > 0) || cashflow.intValue() > 0){
            return cashflow;
        }else {
            return null;
        }
    }

    /**
     * Create new Balance. check on exist balance before today, if list balance is not empty get first balance before
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

        return createNewBalanceFromCashFlow(person, date);
    }


    private BalanceEntity createNewBalanceFromCashFlow(PersonEntity personEntity, Date date){
        String hql = "select sum(cashflow.balance) from CashFlowEntity cashflow " +
                "where cashflow.taskByTask.targetByTarget.person = :person " +
                "and cashflow.date < :date";

        Query query = session.createQuery(hql);
        query.setParameter("person", personEntity);
        query.setParameter("date", date);

        BigDecimal bigDecimal = (BigDecimal) query.list().get(0);
        if(bigDecimal == null){
            return createZeroBalance(personEntity,date);
        }
        BalanceEntity balanceEntity = new BalanceEntity(bigDecimal, date, personEntity);

        return balanceEntity;
    }

    /**
     * Create zero balance for person in current date
     * @param personEntity
     * @param date
     * @return BalanceEntity
     */
    private BalanceEntity createZeroBalance(PersonEntity personEntity, Date date){
        BalanceEntity balanceEntity = new BalanceEntity(ZERO, date, personEntity);

        return balanceEntity;
    }
}
