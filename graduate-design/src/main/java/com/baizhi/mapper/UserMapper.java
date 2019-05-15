package com.baizhi.mapper;

import com.baizhi.dto.Province;
import com.baizhi.dto.SexDto;
import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<User> {
    public Integer staticsCount(@Param("data") String data);

    public List<Province> groupByProvince();

    public List<SexDto> groupBySex(@Param("sex") String sex);
}
