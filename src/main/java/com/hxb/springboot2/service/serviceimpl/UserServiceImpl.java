package com.hxb.springboot2.service.serviceimpl;

import com.hxb.springboot2.entity.UserBean;
import com.hxb.springboot2.mapper.UserMapper;
import com.hxb.springboot2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
   @Autowired
   UserMapper userMapper;


    @Override
    public UserBean loginIn(String username, String password) {
        return userMapper.getInto(username,password);
    }

    @Override
    public void register(String username, String password) {
         userMapper.registerUser(username,password);
    }

    @Override
    public UserBean getUsername(String username) {
        return this.userMapper.getUsername(username);
    }

    @Override
    public List<UserBean> selectAllUser() {

        return this.userMapper.selectAllUser();
    }

    @Override
    public UserBean getUserById(int id) {
        return this.userMapper.getUserById(id);
    }

    @Override
    public int updateCustomer1(UserBean userBean) {
        return this.userMapper.updateCustomer1(userBean);
    }
    public int deleteUser(int id){
        return this.userMapper.deleteUser(id);
    }
}
