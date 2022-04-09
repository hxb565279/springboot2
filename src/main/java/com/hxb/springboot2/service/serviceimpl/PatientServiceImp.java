package com.hxb.springboot2.service.serviceimpl;

import com.hxb.springboot2.entity.Patient;
import com.hxb.springboot2.mapper.PatientDao;
import com.hxb.springboot2.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImp implements PatientService {
    @Autowired
    PatientDao dao;


    @Override
    public List<Patient> findPatient() {
        return null;
    }

    @Override
    public List<Patient> findAll() {
        return dao.findAll();
    }


    @Override
    public void add(Patient patient) {
        dao.add(patient);
    }

    @Override
    public int update(Patient patient) {
     return    dao.update(patient);
    }

    @Override
    public int delete(int id) {
       return   dao.delete(id);
    }

    @Override
    public Patient updatePaintById(int baseId) {
     return    dao.updatePatientById(baseId);
    }

    @Override
    public List<Patient> findAllToMain() {
        return this.findAllToMain();
    }


}
