package com.hxb.springboot2.mapper;


import com.hxb.springboot2.entity.Patient;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PatientDao {

    //查找所有现存确诊病人
    @Select("select * from patient")
    public List<Patient> findAll();

    //添加病人
    @Insert("insert into patient(username,id,sex,elephone,address,onsetDate,hospital,symptoms)values(#{username},#{id},#{sex},#{elephone},#{address},#{onsetDate},#{hospital}, #{symptoms})")
    public void add(Patient patient);
    //更新病人信息
    @Update("update patient set  username=#{username},id=#{id},sex=#{sex},elephone=#{elephone},address=#{address} ,hospital=#{hospital}, symptoms=#{symptoms} where baseId=#{baseId}")
    public int update(Patient patient);
   @Delete(value = "delete from patient where baseId= #{baseId}")
    public int delete(int id);

   @Select("select * from patient where baseId= #{baseId}")
   public Patient updatePatientById(int baseId);

   @Select("select * from patient")
    public List<Patient> findPat();
}
