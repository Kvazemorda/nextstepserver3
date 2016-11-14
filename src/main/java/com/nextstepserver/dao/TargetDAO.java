package com.nextstepserver.dao;

import com.nextstepserver.entity.TargetEntity;
import com.nextstepserver.hibernate.HibernateSessionFactory;
import org.hibernate.Session;
import org.springframework.context.annotation.Configuration;

@Configuration
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
