package com.hxb.springboot2.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class DataBean implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
private int id;
    private String area;
    private int nowConfirm;
    private int confirm;
    private int heal;
    private int dead;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getNowConfirm() {
        return nowConfirm;
    }

    public void setNowConfirm(int nowConfirm) {
        this.nowConfirm = nowConfirm;
    }

    public int getConfirm() {
        return confirm;
    }

    public void setConfirm(int confirm) {
        this.confirm = confirm;
    }

    public int getHeal() {
        return heal;
    }

    public void setHeal(int heal) {
        this.heal = heal;
    }

    public int getDead() {
        return dead;
    }

    public void setDead(int dead) {
        this.dead = dead;
    }



    @Override
    public String toString() {
        return "DataBean{" +

                ", area='" + area + '\'' +
                ", nowConfirm=" + nowConfirm +
                ", confirm=" + confirm +
                ", heal=" + heal +
                ", dead=" + dead +

                '}';
    }
}
