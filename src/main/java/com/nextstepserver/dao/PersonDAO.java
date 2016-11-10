package com.nextstepserver.dao;

import com.nextstepserver.entity.PersonEntity;
import com.nextstepserver.hibernate.HibernateSessionFactory;
import org.hibernate.Session;

public class PersonDAO implements CRUD {

    Session session = HibernateSessionFactory.getSessionFactory().openSession();

    @Override
    public void saveObject(Object o) {
        PersonEntity person = (PersonEntity) o;
        session.beginTransaction();
        session.saveOrUpdate(person);
        session.getTransaction().commit();
    }
}
