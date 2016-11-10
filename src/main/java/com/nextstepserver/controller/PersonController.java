package com.nextstepserver.controller;

import com.nextstepserver.hibernate.HibernateSessionFactory;
import com.nextstepserver.entity.PersonEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {
    Session session = HibernateSessionFactory.getSessionFactory().openSession();

    @RequestMapping("/persons")
    public PersonEntity getAllPerson(){
        Query query = session.createQuery("select person from PersonEntity person");
        PersonEntity personEntity = (PersonEntity) query.list().get(0);
        return personEntity;
    }
}
