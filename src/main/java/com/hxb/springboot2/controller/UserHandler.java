package com.hxb.springboot2.controller;


import com.hxb.springboot2.entity.User;
import com.hxb.springboot2.repository.UserRepositpry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserHandler {
    @Autowired
    private UserRepositpry userRepositpry;

    @GetMapping("/findall")
    public  List<User> findAll(){
        return userRepositpry.findAll();
    }
    @PostMapping("/save")
    public String save(@RequestBody User user){
        User result = userRepositpry.save(user);
        if(result != null){
            return "success";
        }else{
            return "error";
        }
    }
    @Transactional
    @GetMapping("/findById/{id}")
    public User findById(@PathVariable("id") Integer id){
        return userRepositpry.findById(id).get();
    }


    @Transactional
    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") Integer id){
        System.out.println(id);
        userRepositpry.deleteById(id);
    }
    @PutMapping("/update")
    public String update(@RequestBody User user){
        User result = userRepositpry.save(user);
        if(result != null){
            return "success";
        }else{
            return "error";
        }
    }

}
