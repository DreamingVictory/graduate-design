package com.baizhi.service;

import com.baizhi.dto.PageBeanDto;
import com.baizhi.dto.Province;
import com.baizhi.dto.SexDto;
import com.baizhi.entity.Consumer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ConsumerService {
    public List<Integer> statisCount(String data1, String data2, String data3);

    public List<Province> groupByProvince();

    public List<SexDto> groupBySex(String sex);


    public PageBeanDto<Consumer> queryAllUser(Integer page, Integer rows);

    public void updateUser(Consumer user, String data1, String data2, String data3);

    public void exportUser(HttpServletResponse response, HttpServletRequest request);

    public void userImport();
}
