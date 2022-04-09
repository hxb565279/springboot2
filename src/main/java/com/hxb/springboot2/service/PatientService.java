package com.hxb.springboot2.service;





import com.hxb.springboot2.entity.Patient;

import java.util.List;

public interface PatientService {
    //查找所有病患
   public List<Patient> findPatient();

    public List<Patient> findAll();

    //添加病患
    public void add(Patient patient);
    //更新病患信息
    public int update(Patient patient) ;


    //死亡
    public int delete(int id);

    public Patient updatePaintById(int baseId);

    public List<Patient> findAllToMain();
}
