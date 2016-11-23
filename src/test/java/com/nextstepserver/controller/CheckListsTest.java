package com.nextstepserver.controller;

import com.nextstepserver.dao.*;
import com.nextstepserver.datatest.TestData;
import com.nextstepserver.entity.CashFlowEntity;
import com.nextstepserver.entity.PersonEntity;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

public class CheckListsTest {
    public TestData testData;
    public PersonDAO personDAO = new PersonDAO();

    public CheckListsTest() throws InterruptedException {
        testData = new TestData();
        createDB();
    }

    public void createDB(){
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
        BalanceDAO balanceDAO = new BalanceDAO();
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

        for(int i = 0; i < testData.listBalance.size(); i++){
            balanceDAO.saveObject(testData.listBalance.get(i));
        }
    }


    @Test
    public void testPerson(){
        Assert.assertTrue(personDAO.personIsExist(1));
        Assert.assertEquals("Валя то не Валя", personDAO.getPersonEntity(1), testData.listPerson.get(0));
    }

    @Test
    public void testSumCashFlow(){
        PersonEntity personEntity = personDAO.getPersonEntity(1);
        CashFlowDAO cashFlowDAO = new CashFlowDAO();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("DD.MM.yyyy");
        Date today = null;
        try {
            today = simpleDateFormat.parse("24.11.2016");
            System.out.println(today);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Set<CashFlowEntity> cashFlowSet = cashFlowDAO.getSetCashFlowForPerson(personEntity, today);
        System.out.println(cashFlowSet.size());
        BigDecimal bigDecimal = new BigDecimal(0);
        int count = 0;
        for(CashFlowEntity cashFlowEntity: cashFlowSet){
            bigDecimal = bigDecimal.add(cashFlowEntity.getBalance());
            System.out.println(bigDecimal);
        }
        Assert.assertEquals("Сумма расходов не бьется", bigDecimal, 1604.5);
    }
}
