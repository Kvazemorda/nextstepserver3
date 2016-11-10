package com;

import com.nextstepserver.controller.TaskController;
import com.nextstepserver.dao.CashFlowDAO;
import com.nextstepserver.dao.PersonDAO;
import com.nextstepserver.dao.TargetDAO;
import com.nextstepserver.dao.TaskDAO;
import com.nextstepserver.datatest.TestData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@EnableAutoConfiguration
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class);
        createDB();
    }

    public static void createDB(){
        TestData testData = null;
        try {
            testData = new TestData();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        PersonDAO personDAO = new PersonDAO();
        TaskDAO taskDAO = new TaskDAO();
        TargetDAO targetDAO = new TargetDAO();
        CashFlowDAO cashFlowDAO = new CashFlowDAO();
        for(int i = 0; i < testData.listPerson.size(); i++){
            personDAO.saveObject(testData.listPerson.get(i));
        }
        for(int i = 0; i < testData.listTargets.size(); i++){
            targetDAO.saveObject(testData.listTargets.get(i));
        }
            taskDAO.saveListObject(testData.listTask);

        for(int i = 0; i < testData.listCashFlow.size(); i++){
            cashFlowDAO.saveObject(testData.listCashFlow.get(i));
        }
        TaskController.taskEntity = testData.listTask.get(0);

    }
}
