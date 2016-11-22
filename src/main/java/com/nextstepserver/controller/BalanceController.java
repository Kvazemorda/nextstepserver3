package com.nextstepserver.controller;

import com.nextstepserver.dao.BalanceDAO;
import com.nextstepserver.entity.BalanceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class    BalanceController {

    @Autowired
    BalanceDAO balanceDAO;

    @RequestMapping("/balance")
    public BalanceEntity currentBalance(long login, long date){
        System.out.println(login);
        System.out.printf(" " + date);
        return balanceDAO.getBalance(login, date);
    }

}
