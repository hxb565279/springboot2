package com.hxb.springboot2.service;


import com.hxb.springboot2.entity.UserBean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    UserBean loginIn(String username, String password);
      void register(String username,String password);
    UserBean getUsername(String username);

    List<UserBean> selectAllUser();
    UserBean getUserById(int id);
    int updateCustomer1(UserBean userBean);
    int deleteUser(int id);
}
