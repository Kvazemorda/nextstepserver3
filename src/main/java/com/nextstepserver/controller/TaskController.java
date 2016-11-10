package com.nextstepserver.controller;

import com.nextstepserver.dao.TaskDAO;
import com.nextstepserver.entity.PersonEntity;
import com.nextstepserver.entity.TaskEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.TreeSet;

@RestController
public class TaskController {
    public static TaskEntity taskEntity;

    @Autowired
    TaskDAO taskDAO;

    @RequestMapping("tasks")
    public TreeSet<TaskEntity> getTasks(PersonEntity personEntity){
        personEntity = PersonController.personEntity;
        return taskDAO.getCurrentTask(personEntity);
    }

}
