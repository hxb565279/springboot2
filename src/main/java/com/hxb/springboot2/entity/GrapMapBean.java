package com.hxb.springboot2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GrapMapBean {
    //日期
    private String date;
    //现存确诊人数
    private int nowConfirm;
    private int confirm;
    private int dead;
    private int heal;
}
