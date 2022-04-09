package com.hxb.springboot2.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class UserRepositpryTest {
    @Autowired
           private UserRepositpry userRepositpry;

           @Test
         void findUser(){
               System.out.println(userRepositpry.findAll());
           }


           @Test
    void deleteUser(){
               this.userRepositpry.deleteById(7);
           }
}