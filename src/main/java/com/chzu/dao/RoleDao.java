package com.chzu.dao;

import com.chzu.entity.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDao {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * 获取用户权限
     * @param roleId
     * @return
     */
    public Role selectByPrimaryKey(Integer roleId){
        Session session = sessionFactory.openSession();
        Role role = (Role) session.get(Role.class, roleId);
        session.close();
        return role;

    }

}
