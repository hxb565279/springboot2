package com.hxb.springboot2.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hxb.springboot2.entity.DataBean;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DataMapper extends BaseMapper<DataBean> {
}
