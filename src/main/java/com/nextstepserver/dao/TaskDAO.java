package com.nextstepserver.dao;

import com.nextstepserver.entity.PersonEntity;
import com.nextstepserver.entity.TaskEntity;
import com.nextstepserver.hibernate.HibernateSessionFactory;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.TreeSet;

public class TaskDAO {
    Session session = HibernateSessionFactory.getSessionFactory().openSession();

    /**
     * We must get task, who was not finished
     * @return TreeSet<TaskEntity>
     */
    public TreeSet<TaskEntity> getCurrentTask(PersonEntity personEntity){
        String hql = "select task from TaskEntity task " +
                "where task.targetByTarget.person = :person " +
                "and task.dateEnd is null";

        Query query = session.createQuery(hql);
        query.setParameter("person", personEntity);
        TreeSet<TaskEntity> tasks = new TreeSet<>(query.list());
        return tasks;
    }
}
