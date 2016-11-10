package com.nextstepserver.controller;

import com.nextstepserver.dao.CashFlowDAO;
import com.nextstepserver.entity.CashFlowEntity;
import com.nextstepserver.entity.PersonEntity;
import com.nextstepserver.entity.TaskEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.TreeSet;

@RestController
public class CashFlowController {

    @Autowired
    CashFlowDAO cashFlowDAO;

    @RequestMapping("cashflow")
    public TreeSet<CashFlowEntity> getCashFlowForTask(PersonEntity personEntity, TaskEntity taskEntity){
        personEntity = PersonController.personEntity;
        taskEntity = TaskController.taskEntity;
        return cashFlowDAO.getSetCashFlowForTask(personEntity,taskEntity);
    }
}
