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

    public BigDecimal getCurrentBalance(PersonEntity person, Date date){
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
    private BigDecimal balanceIsExist(PersonEntity person, Date date){
        String hql = "select balance from BalanceEntity balance " +
                "where balance.personEntity = :person " +
                "and balance.dateBalance = :date";

        Query query = session.createQuery(hql);
        query.setParameter("person", person);
        query.setParameter("date", date);
        if(query.list().isEmpty()){
            return (BigDecimal) query.list().get(0);
        }else {
            return null;
        }
    }

    private BigDecimal createNewBalance(PersonEntity person, Date date){

        return null;
    }
}
