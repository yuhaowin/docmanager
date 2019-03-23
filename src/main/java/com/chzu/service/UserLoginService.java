package com.chzu.service;


import com.chzu.dao.RoleDao;
import com.chzu.dao.UserLoginDao;
import com.chzu.entity.Role;
import com.chzu.entity.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class UserLoginService {


    @Autowired
    private UserLoginDao userLoginDao;

    @Autowired
    private RoleDao roleDao;

    /**
     *  获取用户登录信息
     * @param userName
     * @return
     */
    public UserLogin login(String userName){
       return userLoginDao.login(userName);
    }

    /**
     * 获取角色权限信息
     * @param roleId
     * @return
     */
    public Role getRole(Integer roleId){
        return roleDao.getRole(roleId);
    }

    public void save(UserLogin userlogin){

    }

    public void removeByName(String name){

    }

    public UserLogin findByName(String name){
        return null;
    }

    public void updateByName(String name, UserLogin u){

    }
}
