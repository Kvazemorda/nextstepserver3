package com.nextstepserver.dao;

import com.nextstepserver.entity.CashFlowEntity;
import com.nextstepserver.entity.PersonEntity;
import com.nextstepserver.entity.TaskEntity;
import com.nextstepserver.hibernate.HibernateSessionFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.TreeSet;

@Configuration
public class CashFlowDAO implements CRUD {

    Session session = HibernateSessionFactory.getSessionFactory().openSession();

    /**
     * Get TreeSet cash flow for Person who spend money on this task
     * @param personEntity
     * @param taskEntity
     * @return TreeSet<CashFlow>
     */
    public TreeSet<CashFlowEntity> getSetCashFlowForTask(PersonEntity personEntity, TaskEntity taskEntity){
        String hql = "select cashflow from CashFlowEntity cashflow " +
                "where cashflow.taskByTask.targetByTarget.person = :person " +
                "and cashflow.taskByTask = :task";

        Query query = session.createQuery(hql);
        query.setParameter("person", personEntity);
        query.setParameter("task", taskEntity);
        TreeSet<CashFlowEntity> cashFlowEntities = new TreeSet<>(query.list());
        return cashFlowEntities;
    }

    /**
     * Get TreeSet cash flow all income and expence person who was made
     * @param personEntity
     * @param date cash flow
     * @return TreeSet<CashFlowEntity>
     */
    public TreeSet<CashFlowEntity> getSetCashFlowForPerson(PersonEntity personEntity, Date date){
        String hql = "select cashflow from CashFlowEntity cashflow " +
                "where cashflow.taskByTask.targetByTarget.person = :person ";
              //  "and cashflow.date = :currentDate";

        Query query = session.createQuery(hql);
        query.setParameter("person", personEntity);
        //query.setParameter("currentDate", date);
        TreeSet<CashFlowEntity> cashFlowEntities = new TreeSet<>(query.list());
        return cashFlowEntities;
    }

    @Override
    public void saveObject(Object o) {
        CashFlowEntity cashFlow = (CashFlowEntity) o;
        session.beginTransaction();
        session.saveOrUpdate(cashFlow);
        session.getTransaction().commit();
    }
}
