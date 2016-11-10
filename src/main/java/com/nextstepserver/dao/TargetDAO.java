package com.nextstepserver.dao;

import com.nextstepserver.entity.TargetEntity;
import com.nextstepserver.hibernate.HibernateSessionFactory;
import org.hibernate.Session;

public class TargetDAO implements CRUD {

    public TargetDAO() {
    }

    Session session = HibernateSessionFactory.getSessionFactory().openSession();

    @Override
    public void saveObject(Object o) {
        TargetEntity target = (TargetEntity) o;
        session.beginTransaction();
        session.saveOrUpdate(target);
        session.getTransaction().commit();
    }
}
