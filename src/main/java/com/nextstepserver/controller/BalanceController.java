package com.nextstepserver.controller;

import com.nextstepserver.dao.BalanceDAO;
import com.nextstepserver.entity.BalanceEntity;
import com.nextstepserver.entity.PersonEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;

@RestController
public class BalanceController {

    @Autowired
    BalanceDAO balanceDAO;

    @RequestMapping("/balance")
    public BalanceEntity currentBalance(PersonEntity personEntity, Date date){
        personEntity =  PersonController.personEntity;
        date = new Date();
        return balanceDAO.getBalance(personEntity, date);
    }

}
