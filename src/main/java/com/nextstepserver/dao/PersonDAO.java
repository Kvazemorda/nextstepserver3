package com.nextstepserver.dao;

import com.nextstepserver.entity.PersonEntity;
import com.nextstepserver.hibernate.HibernateSessionFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class PersonDAO implements CRUD {

    Session session = HibernateSessionFactory.getSessionFactory().openSession();

    @Override
    public void saveObject(Object o) {
        PersonEntity person = (PersonEntity) o;
        session.beginTransaction();
        session.saveOrUpdate(person);
        session.getTransaction().commit();
    }

    public List<PersonEntity> getAllPerson(){
        Query query = session.createQuery("select person from PersonEntity person");
        return query.list();
    }
}
