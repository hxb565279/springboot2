package com.hxb.springboot2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hxb.springboot2.entity.DataBean;

import java.util.List;

public interface DataService extends IService<DataBean> {

    List<DataBean> list();
    List<DataBean> list2();

}
