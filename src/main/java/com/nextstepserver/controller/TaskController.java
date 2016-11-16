package com.nextstepserver.controller;

import com.nextstepserver.dao.PersonDAO;
import com.nextstepserver.dao.TaskDAO;
import com.nextstepserver.entity.PersonEntity;
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


    @RequestMapping("tasks")
    public List<TaskEntity> getTasks(PersonEntity personEntity){
        PersonEntity personEntity1 = personEntity;
        personDAO.saveObject(personEntity1);
        if(personDAO.personIsExist(personEntity1)){
            return taskDAO.getCurrentTask(personEntity1);
        }else {
            return null;
        }
    }

}
