package com.nextstepserver.dao;

import com.nextstepserver.entity.BalanceEntity;
import com.nextstepserver.entity.CashFlowEntity;
import com.nextstepserver.entity.PersonEntity;
import com.nextstepserver.entity.TaskEntity;
import com.nextstepserver.hibernate.HibernateSessionFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.Date;
import java.util.TreeSet;

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

    public BalanceEntity getCurrentBalance(PersonEntity person, Date date){
        if(balanceIsExist(person, date) != null){
            return balanceIsExist(person, date);
        }
        return createNewBalance(person, date);
    }

    /**
     * check that balance for perosn in current day is exist
     * @param person
     * @param date
     * @return boolean
     */
    private BalanceEntity balanceIsExist(PersonEntity person, Date date){
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
        String hql = "select balance from BalanceEntity balance " +
                "where balance.personEntity = :person " +
                "and balance.dateBalance <= :date " +
                "order by dateBalance desc";

        Query query = session.createQuery(hql);
        query.setParameter("person", person);
        query.setParameter("date", date);

        if(query.list().isEmpty()){
            String sumCashFlow =
                    "select sum(cashflow.balance) from CashFlowEntity cashflow " +
                    "where cashflow.taskByTask.targetByTarget.person = :person " +
                            "and cashflow.date <= :today";

            Query queryCashFlow = session.createQuery(sumCashFlow);
            queryCashFlow.setParameter("person", person);
            queryCashFlow.setParameter("today", date);
            if(queryCashFlow.list().isEmpty()){
                return new BalanceEntity(BigDecimal.ZERO, date, person);
            }else {
                return new BalanceEntity(BigDecimal.valueOf((Long)queryCashFlow.list().get(0)),date, person);
            }
        }else{
            return (BalanceEntity) query.list().get(0);
        }
    }
}
