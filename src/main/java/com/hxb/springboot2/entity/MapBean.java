package com.hxb.springboot2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MapBean {

    //各省名称
    private String name;
    //确诊人数
    private int value;
}