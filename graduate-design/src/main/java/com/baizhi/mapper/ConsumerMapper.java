package com.baizhi.mapper;

import com.baizhi.dto.Province;
import com.baizhi.dto.SexDto;
import com.baizhi.entity.Consumer;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ConsumerMapper extends Mapper<Consumer> {
    public Integer staticsCount(@Param("data") String data);

    public List<Province> groupByProvince();

    public List<SexDto> groupBySex(@Param("sex") String sex);
}
