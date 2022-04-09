package com.hxb.springboot2.controller;


import com.hxb.springboot2.entity.Patient;
import com.hxb.springboot2.mapper.PatientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientHandler {

    @Autowired
    private PatientDao patientDao;
    @GetMapping("/patientfindall")
    public List<Patient> findAllPat(){
  return       this.patientDao.findAll();
    }


}
