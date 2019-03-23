package com.chzu.service;

import com.chzu.dao.RoleDao;
import com.chzu.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    public Role findByid(Integer id) {
        return roleDao.selectByPrimaryKey(id);
    }
}
