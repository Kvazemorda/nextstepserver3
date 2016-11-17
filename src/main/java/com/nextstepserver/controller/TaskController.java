package com.nextstepserver.controller;

import com.nextstepserver.dao.PersonDAO;
import com.nextstepserver.dao.TaskDAO;
import com.nextstepserver.entity.TaskEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class TaskController {

    public static TaskEntity taskEntity = new TaskEntity("title", BigDecimal.TEN);

    @Autowired TaskDAO taskDAO;
    @Autowired PersonDAO personDAO;
    // "http://192.168.0.105:8080/tasks/?PersonEntity=PersonEntity%7Bid=1,%20name='Valya',%20email='ilyavanavara@mail.com',%20targetsById=[]%7D";


    @RequestMapping("/tasks")
    public List<TaskEntity> getTasks(String personEntity){
        System.out.println(personEntity);
            return null; //taskDAO.getCurrentTask(personEntity);
    }

}
