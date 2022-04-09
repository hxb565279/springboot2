package com.hxb.springboot2.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@Data
@Accessors(chain=true)

public class Patient implements Serializable {
    private int baseId;
    private String username;



    private String id;
    private String sex;
    private String elephone;
    private String address;

    private Date onsetDate;
    private String hospital;
    private String symptoms;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }





    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getElephone() {
        return elephone;
    }

    public void setElephone(String elephone) {
        this.elephone = elephone;
    }

    public int getBaseId() {
        return baseId;
    }

    public void setBaseId(int baseId) {
        this.baseId = baseId;
    }



    public Date getOnsetDate() {
        return onsetDate;
    }

    public void setOnsetDate(Date onsetDate) {
        this.onsetDate = onsetDate;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }



    @Override
    public String toString() {
        return "Patient{" +
                "baseId=" + baseId +
                ", id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", onsetDate=" + onsetDate +
                ", symptoms='" + symptoms + '\'' +
                ", hospital='" + hospital + '\'' +
                ", address='" + address + '\'' +
                ", sex='" + sex + '\'' +
                ", elephone='" + elephone + '\'' +

                '}';
    }
}
