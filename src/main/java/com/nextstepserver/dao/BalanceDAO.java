package com.nextstepserver.dao;

import com.nextstepserver.entity.BalanceEntity;
import com.nextstepserver.entity.PersonEntity;
import com.nextstepserver.hibernate.HibernateSessionFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.Date;

import static java.math.BigDecimal.ZERO;

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
     * @param login
     * @param date
     * @return BalanceEntity
     */
    public BalanceEntity getBalance(long login, long date){
        long person = getIdPerson(login);
        Date currentDate = getDate(date);

        String hql = "select balance from BalanceEntity balance " +
                "where balance.personEntity.id = :person " +
                "and balance.dateBalance < :date";

        Query query = session.createQuery(hql);
        query.setParameter("person", person);
        query.setParameter("date", currentDate);
        if(!query.list().isEmpty() || query.list().size() != 0){
                BalanceEntity previousBalance = (BalanceEntity) query.list().get(0);
                BigDecimal CFBetweenPrevious = getCFBetweenPreviousAndYesterdayBalance(previousBalance.getDateBalance()
                ,currentDate);
            //if cash flow exist between previousBalance and before current day, we add sum cash flow to previous Balance
            if(CFBetweenPrevious != null){
                previousBalance.setBalance(previousBalance.getBalance().add(CFBetweenPrevious));
                saveObject(previousBalance);
            }
            return previousBalance;
        }else {
            return createNewBalance(person, currentDate);
        }
    }

    /**
     * If previous balance not yesterday, need add sum cash flow from previous Balance and before current Balance
     * @param previousDate
     * @param today
     * @return
     */
    private BigDecimal getCFBetweenPreviousAndYesterdayBalance(Date previousDate, Date today){
        String hql = "select sum(cashflow.balance) from CashFlowEntity cashflow " +
                "where cashflow.date > :previousDate " +
                "and cashflow.date < :today";

        Query query = session.createQuery(hql);
        query.setParameter("previousDate", previousDate);
        query.setParameter("today", today);
        BigDecimal cashflow = (BigDecimal) query.list().get(0);
        if ((query.list().size() > 0) && cashflow.intValue() > 0){
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
     * @param login
     * @param date
     * @return
     */
    private BalanceEntity createNewBalance(long login, Date date){

        return createNewBalanceFromCashFlow(login, date);
    }


    private BalanceEntity createNewBalanceFromCashFlow(long login, Date date){
        String hql = "select sum(cashflow.balance) from CashFlowEntity cashflow " +
                "where cashflow.taskByTask.targetByTarget.person.id = :person " +
                "and cashflow.date < :date";

        Query query = session.createQuery(hql);
        query.setParameter("person", login);
        query.setParameter("date", date);

        BigDecimal bigDecimal = (BigDecimal) query.list().get(0);
        if(bigDecimal == null){
            return createZeroBalance(getPerson(login),date);
        }
        BalanceEntity balanceEntity = new BalanceEntity(bigDecimal, date, getPerson(login));

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

    private long getIdPerson(long id){
        return id;
    }
    private Date getDate(long date){
        /*String format = "EEE MMM dd HH:mm:ss z 08:00 yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date currentDate = null;
        Date testDate = new Date();

        try {
            currentDate = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            System.out.println(testDate);
            e.printStackTrace();
        }*/

        Date currentDate = new Date(date);

        return currentDate;
    }
    private PersonEntity getPerson(long id){
        String hql = "select distinct person from PersonEntity person " +
                "where person.id = :id";

        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        PersonEntity personEntity = (PersonEntity) query.list().get(0);
        return personEntity;
    }
}
