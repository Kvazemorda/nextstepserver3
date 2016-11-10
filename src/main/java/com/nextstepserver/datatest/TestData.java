package com.nextstepserver.datatest;

import com.nextstepserver.entity.CashFlowEntity;
import com.nextstepserver.entity.PersonEntity;
import com.nextstepserver.entity.TargetEntity;
import com.nextstepserver.entity.TaskEntity;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class TestData {
    public ArrayList<PersonEntity> listPerson;
    public ArrayList<TargetEntity> listTargets;
    public ArrayList<TaskEntity> listTask;
    public ArrayList<CashFlowEntity> listCashFlow;

    public TestData() throws InterruptedException {
        PersonEntity ilya = new PersonEntity("Ilya", "ilyavanavara@mail.com");
        PersonEntity luba = new PersonEntity("Luba", "luba@mail.com");

        listTargets = new ArrayList<>();
        listTask = new ArrayList<>();
        listPerson = new ArrayList<>();
        listCashFlow = new ArrayList<>();

        listPerson.add(ilya);
        listPerson.add(luba);

        TargetEntity targetIlya1 = new TargetEntity("target1", ilya);
        TargetEntity targetIlya2 = new TargetEntity("target2", ilya);
        TargetEntity targetIlya3 = new TargetEntity("target3", ilya);
        TargetEntity targetIlya4 = new TargetEntity("target4", ilya);
        TargetEntity targetluba1 = new TargetEntity("target1", luba);
        TargetEntity targetluba2 = new TargetEntity("target2", luba);
        TargetEntity targetluba3 = new TargetEntity("target3", luba);
        TargetEntity targetluba4 = new TargetEntity("target4", luba);

        listTargets.add(targetIlya1);
        listTargets.add(targetIlya2);
        listTargets.add(targetIlya3);
        listTargets.add(targetIlya4);
        listTargets.add(targetluba1);
        listTargets.add(targetluba2);
        listTargets.add(targetluba3);
        listTargets.add(targetluba4);

        TaskEntity task1TargetIlya1 = new TaskEntity("task1Target1", targetIlya1);
        TaskEntity task2TargetIlya1 = new TaskEntity("task2Target1", targetIlya1, new Date());
        TaskEntity task3TargetIlya1 = new TaskEntity("task3Target1", targetIlya1, new BigDecimal(20.00));
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(new Date());
        calendar1.add(Calendar.HOUR, 1);
        Date date1 = calendar1.getTime();
        TaskEntity task4TargetIlya1 = new TaskEntity("task4Target1", targetIlya1);
        TaskEntity task5TargetIlya1 = new TaskEntity("task5Target1", targetIlya1, date1);
        TaskEntity task6TargetIlya1 = new TaskEntity("task6Target1", targetIlya1, new BigDecimal(30.20));
        calendar1.add(Calendar.HOUR, 2);
        Date date2 = calendar1.getTime();
        TaskEntity task7TargetIlya1 = new TaskEntity("task7Target1", targetIlya1);
        TaskEntity task8TargetIlya1 = new TaskEntity("task8Target1", targetIlya1, date2);
        TaskEntity task9TargetIlya1 = new TaskEntity("task9Target1", targetIlya1, new BigDecimal(300.22));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR, 9);
        Date date = calendar.getTime();
        TaskEntity task10TargetIlya1 = new TaskEntity("task10Target1", targetIlya1);
        TaskEntity task11TargetIlya1 = new TaskEntity("task11Target1", targetIlya1, date);
        TaskEntity task12TargetIlya1 = new TaskEntity("task12Target1", targetIlya1, new BigDecimal(1300.00));

        TaskEntity task13TargetIlya1 = new TaskEntity("task13Target1", targetIlya1);
        TaskEntity task14TargetIlya1 = new TaskEntity("task14Target1", targetIlya1, new Date());
        TaskEntity task15TargetIlya1 = new TaskEntity("task15Target1", targetIlya1, new BigDecimal(5300.00));

        TaskEntity task1TargetIlya2 = new TaskEntity("task1Target2", targetIlya2);
        TaskEntity task2TargetIlya2 = new TaskEntity("task2Target2", targetIlya2, new Date());
        TaskEntity task3TargetIlya2 = new TaskEntity("task3Target2", targetIlya2, new BigDecimal(155.00));

        TaskEntity task1TargetIlya3 = new TaskEntity("task1Target3", targetIlya3);
        TaskEntity task2TargetIlya3 = new TaskEntity("task2Target3", targetIlya3, new Date());
        TaskEntity task3TargetIlya3 = new TaskEntity("task3Target3", targetIlya3, new BigDecimal(266.00));



        //Р”РѕР±Р°РІР»СЏРµРј Рє Р·Р°РґР°С‡Р°Рј РїРµСЂРІРѕР№ С†РµР»Рё РґРѕС‡РµРє
        //Р—Р°РґР°С‡Р° 1 РїРµСЂРІРѕР№ С†РµР»Рё РёРјРµРµС‚ РґРѕС‡РµСЂРЅСЋСЋ Р·Р°РґР°С‡Сѓ 2
        //Р—Р°РґР°С‡Р° 2 РїРµСЂРІРѕР№ С†РµР»Рё РёРјРµРµС‚ РґРѕС‡РµСЂРЅСЋСЋ Р·Р°РґР°С‡Сѓ 3
        //Р—Р°РґР°С‡Р° 3 РїРµСЂРІРѕР№ С†РµР»Рё РёРјРµРµС‚ РґРІРµ РґРѕС‡РµСЂРЅРёРё Р·Р°РґР°С‡Рё 4 Рё 5
        //Р—Р°РґР°С‡Р° 4 РїРµСЂРІРѕР№ С†РµР»Рё РёРјРµРµС‚ РґРІРµ РґРѕС‡РµРЅРёРё  Р·Р°РґР°С‡Рё 6 Рё 7
        //Р—Р°РґР°С‡Р° 5 РїРµСЂРІРѕР№ С†РµР»Рё РёРјРµРµС‚ РґРІРµ РґРѕС‡РµРЅРёРё  Р·Р°РґР°С‡Рё 8 Рё 9
        //Р—Р°РґР°С‡Р° 6 РїРµСЂРІРѕР№ С†РµР»Рё РёРјРµРµС‚ С‚СЂРё РґРѕС‡РµРЅРёРё  Р·Р°РґР°С‡Рё 10 Рё 11 Рё 12
        //Р—Р°РґР°С‡Р° 10 РїРµСЂРІРѕР№ С†РµР»Рё РёРјРµРµС‚ С‚СЂРё РґРѕС‡РµРЅРёРё  Р·Р°РґР°С‡Рё 13 Рё 14 Рё 15

        task1TargetIlya1.getTaskChild().add(task2TargetIlya1);
        task2TargetIlya1.getTaskChild().add(task3TargetIlya1);
        task3TargetIlya1.getTaskChild().add(task4TargetIlya1);
        task3TargetIlya1.getTaskChild().add(task5TargetIlya1);
        task4TargetIlya1.getTaskChild().add(task6TargetIlya1);
        task4TargetIlya1.getTaskChild().add(task7TargetIlya1);
        task5TargetIlya1.getTaskChild().add(task8TargetIlya1);
        task5TargetIlya1.getTaskChild().add(task9TargetIlya1);
        task6TargetIlya1.getTaskChild().add(task10TargetIlya1);
        task6TargetIlya1.getTaskChild().add(task11TargetIlya1);
        task6TargetIlya1.getTaskChild().add(task12TargetIlya1);
        task10TargetIlya1.getTaskChild().add(task13TargetIlya1);
        task10TargetIlya1.getTaskChild().add(task14TargetIlya1);
        task10TargetIlya1.getTaskChild().add(task15TargetIlya1);

        //Р”РѕР±Р°РІР»СЏРµРј Рє Р·Р°РґР°С‡Р°Рј РІС‚РѕСЂРѕР№ С†РµР»Рё РґРѕС‡РµРє
        //Р—Р°РґР°С‡Р° 1 РІС‚РѕСЂРѕР№ С†РµР»Рё РёРјРµРµС‚ РґРѕС‡РµСЂРЅСЋСЋ Р·Р°РґР°С‡Сѓ 2
        //Р—Р°РґР°С‡Р° 2 РІС‚РѕСЂРѕР№ С†РµР»Рё РёРјРµРµС‚ РґРѕС‡РµСЂРЅСЋСЋ Р·Р°РґР°С‡Сѓ 3
        task1TargetIlya2.getTaskChild().add(task2TargetIlya2);
        task2TargetIlya2.getTaskChild().add(task3TargetIlya2);

        //Р”РѕР±Р°РІР»СЏРµРј Рє Р·Р°РґР°С‡Р°Рј С‚СЂРµС‚РµР№ С†РµР»Рё РґРѕС‡РµРє
        //Р—Р°РґР°С‡Р° 1 С‚СЂРµС‚РµР№ С†РµР»Рё РёРјРµРµС‚ РґРѕС‡РµСЂРЅСЋСЋ Р·Р°РґР°С‡Сѓ 2
        //Р—Р°РґР°С‡Р° 2 С‚СЂРµС‚РµР№ С†РµР»Рё РёРјРµРµС‚ РґРѕС‡РµСЂРЅСЋСЋ Р·Р°РґР°С‡Сѓ 3
        task1TargetIlya3.getTaskChild().add(task2TargetIlya3);
        task2TargetIlya3.getTaskChild().add(task3TargetIlya3);

        listTask.add(task1TargetIlya1);
        listTask.add(task2TargetIlya1);
        listTask.add(task3TargetIlya1);
        listTask.add(task4TargetIlya1);
        listTask.add(task5TargetIlya1);
        listTask.add(task6TargetIlya1);
        listTask.add(task7TargetIlya1);
        listTask.add(task8TargetIlya1);
        listTask.add(task9TargetIlya1);
        listTask.add(task10TargetIlya1);
        listTask.add(task11TargetIlya1);
        listTask.add(task12TargetIlya1);
        listTask.add(task13TargetIlya1);
        listTask.add(task14TargetIlya1);
        listTask.add(task15TargetIlya1);
        listTask.add(task1TargetIlya2);
        listTask.add(task2TargetIlya2);
        listTask.add(task3TargetIlya2);
        listTask.add(task1TargetIlya3);
        listTask.add(task2TargetIlya3);
        listTask.add(task3TargetIlya3);

        CashFlowEntity cashFlowEntity = new CashFlowEntity(BigDecimal.valueOf(102.50),"Products", new Date());
        CashFlowEntity cashFlowEntity1 = new CashFlowEntity(BigDecimal.valueOf(502),"Cafe", new Date());
        CashFlowEntity cashFlowEntity2 = new CashFlowEntity(BigDecimal.valueOf(100),"forTask1", new Date(), task1TargetIlya1);
        CashFlowEntity cashFlowEntity3 = new CashFlowEntity(BigDecimal.valueOf(200), "forTask2Target1", new Date(), task1TargetIlya1);
        CashFlowEntity cashFlowEntity4 = new CashFlowEntity(BigDecimal.valueOf(300), "forTask2Target1", new Date(), task1TargetIlya1);
        CashFlowEntity cashFlowEntity5 = new CashFlowEntity(BigDecimal.valueOf(400), "forTask2Target1", new Date(),  task1TargetIlya1);

        listCashFlow.add(cashFlowEntity);
        listCashFlow.add(cashFlowEntity1);
        listCashFlow.add(cashFlowEntity2);
        listCashFlow.add(cashFlowEntity3);
        listCashFlow.add(cashFlowEntity4);
        listCashFlow.add(cashFlowEntity5);

    }

}
