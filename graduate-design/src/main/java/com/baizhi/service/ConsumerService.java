package com.baizhi.service;

import com.baizhi.dto.PageBeanDto;
import com.baizhi.dto.Province;
import com.baizhi.dto.SexDto;
import com.baizhi.entity.Consumer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ConsumerService {
     List<Integer> statisCount(String data1, String data2, String data3);

     List<Province> groupByProvince();

     List<SexDto> groupBySex(String sex);

     PageBeanDto<Consumer> queryAllUser(Integer page, Integer rows);

     void updateUser(Consumer user, String data1, String data2, String data3);

     void exportUser(HttpServletResponse response, HttpServletRequest request);

     void userImport();
}
