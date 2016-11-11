package com.nextstepserver.controller;

import com.nextstepserver.entity.PersonEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;

@RestController
public class BalanceController {

    @RequestMapping
    public BigDecimal currentBalance(PersonEntity personEntity, Date date){

        return null;
    }

}
