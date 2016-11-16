package com.nextstepserver.dao;

import com.nextstepserver.entity.PersonEntity;
import com.nextstepserver.entity.TaskEntity;
import com.nextstepserver.hibernate.HibernateSessionFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class TaskDAO implements CRUD {

    public TaskDAO() {
    }

    Session session = HibernateSessionFactory.getSessionFactory().openSession();

    /**
     * We must get task, who was not finished
     * @return TreeSet<TaskEntity>
     */
    public List<TaskEntity> getCurrentTask(PersonEntity personEntity){
        String hql = "select task from TaskEntity task " +
                "where task.targetByTarget.person.name = :person " +
                "and task.dateEnd is null";
        String name = "Valya";
        Query query = session.createQuery(hql);
        query.setParameter("person", name);

        List<TaskEntity> tasks = query.list();
        return tasks;
    }

    public TaskEntity getTask(){
        String hql = "select task from TaskEntity task ";


        Query query = session.createQuery(hql);
        TaskEntity tasks = (TaskEntity) query.list().get(0);
        return tasks;
    }

    @Override
    public void saveObject(Object o) {
        TaskEntity task = (TaskEntity) o;
        session.beginTransaction();
        session.persist(task);
    }

    public void saveListObject(List<?> o) {
        List<TaskEntity> list = (List<TaskEntity>) o;
        for(TaskEntity task: list){
            session.beginTransaction();
            session.saveOrUpdate(task);
        }
        session.getTransaction().commit();

    }
}
