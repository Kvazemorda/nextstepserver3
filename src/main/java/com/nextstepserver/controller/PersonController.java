package com.nextstepserver.controller;

import com.nextstepserver.dao.PersonDAO;
import com.nextstepserver.entity.PersonEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    PersonDAO personDAO;
    public static PersonEntity personEntity;

    @RequestMapping("/persons")
    public List<PersonEntity> getAllPerson(){
        List<PersonEntity> listPersons = personDAO.getAllPerson();

        return listPersons;
    }
}
