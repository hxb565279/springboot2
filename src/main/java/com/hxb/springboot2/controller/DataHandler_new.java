package com.hxb.springboot2.controller;


import com.hxb.springboot2.entity.DataBean;
import com.hxb.springboot2.repository.UserRepositpry;
import com.hxb.springboot2.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/data")
public class DataHandler_new {
    @Autowired
  private DataService dataService;


    @GetMapping("/findAllData")
    public List<DataBean> findAllData(){
        List<DataBean> dataBeans =        dataService.list();
        return dataBeans;
    }

    @GetMapping("/findall")
    public List<DataBean> findAllDataFor(){
        System.out.println(11);
        return dataService.list2();
    }
    @GetMapping("/findallMap")
    public List<DataBean> findAllDataMap(){
        System.out.println(11);
        return dataService.list2();
    }




}
