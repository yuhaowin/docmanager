package com.chzu.dao;

import com.chzu.entity.Student;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Student getStudentByName(String userName){
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Student where user_name = ?");
        query.setParameter(0, userName);
        return null;
    }

}
