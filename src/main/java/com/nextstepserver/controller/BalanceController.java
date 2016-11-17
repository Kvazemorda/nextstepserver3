package com.nextstepserver.controller;

import com.nextstepserver.dao.BalanceDAO;
import com.nextstepserver.entity.BalanceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class    BalanceController {

    @Autowired
    BalanceDAO balanceDAO;

    @RequestMapping("/balance")
    public BalanceEntity currentBalance(String login, Date date){
        return balanceDAO.getBalance(login, date);
    }

}
