package com.nextstepserver3.controller;

import com.nextstepserver3.hibernate.HibernateSessionFactory;
import com.nextstepserver3.entity.PersonEntity;
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
        System.out.println(query.list().get(0));
        System.out.println("start SPRING Query");
        PersonEntity personEntity = (PersonEntity) query.list().get(0);
        return personEntity;
    }
}
