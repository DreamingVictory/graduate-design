package com.baizhi.serviceImpl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.alibaba.fastjson.JSONObject;
import com.baizhi.dto.PageBeanDto;
import com.baizhi.dto.Province;
import com.baizhi.dto.SexDto;
import com.baizhi.entity.Consumer;
import com.baizhi.mapper.ConsumerMapper;
import com.baizhi.service.ConsumerService;
import com.github.pagehelper.PageHelper;
import io.goeasy.GoEasy;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ConsumerServiceImpl implements ConsumerService {
    @Autowired
    private ConsumerMapper consumerMapper;

    @Override
    public List<Integer> statisCount(String data1, String data2, String data3) {
        List<Integer> list = new ArrayList<Integer>();

        Integer integer1 = consumerMapper.staticsCount(data1);
        Integer integer2 = consumerMapper.staticsCount(data2);
        Integer integer3 = consumerMapper.staticsCount(data3);
        list.add(integer1);
        list.add(integer2);
        list.add(integer3);

        return list;
    }

    @Override
    public List<Province> groupByProvince() {
        List<Province> provinces = consumerMapper.groupByProvince();
        return provinces;
    }

    @Override
    public List<SexDto> groupBySex(String sex) {
        List<SexDto> list = null;
        if ("男".equals(sex)) {
            list = consumerMapper.groupBySex("男");
        } else {
            list = consumerMapper.groupBySex("女");
        }
        return list;
    }

    @Override
    public PageBeanDto<Consumer> queryAllUser(Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        List<Consumer> users = consumerMapper.selectAll();
        if (users.isEmpty()) {
            throw new RuntimeException("用户列表不存在...");
        }
        PageBeanDto pb = new PageBeanDto();
        pb.setRows(users);
        pb.setTotal(consumerMapper.selectCount(null));
        return pb;
    }

    @Override
    public void updateUser(Consumer user, String data1, String data2, String data3) {
        //用户状态发生修改  冻结/未冻结
        consumerMapper.updateByPrimaryKeySelective(user);
        //实现数据表格的实时更新
        List<Integer> list = new ArrayList<Integer>();
        Integer integer1 = consumerMapper.staticsCount(data1);
        Integer integer2 = consumerMapper.staticsCount(data2);
        Integer integer3 = consumerMapper.staticsCount(data3);
        list.add(integer1);
        list.add(integer2);
        list.add(integer3);

        String s = JSONObject.toJSONString(list);

        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-866c45c541e54fb6a416e17428daedcd");
        goEasy.publish("cmfz", s);

        //用户分布图
        Map<String, List<SexDto>> map = new HashMap<String, List<SexDto>>();

        List<SexDto> listMale = consumerMapper.groupBySex("男");
        List<SexDto> listFemale = consumerMapper.groupBySex("女");
        map.put("male", listMale);
        map.put("female", listFemale);
        String s1 = JSONObject.toJSONString(map);

        goEasy.publish("userProfile", s1);
    }

    @Override
    public void exportUser(HttpServletResponse response, HttpServletRequest request) {
        List<Consumer> users = consumerMapper.selectAll();
        for (int i = 0; i < users.size(); i++) {
            users.get(i).setHeadPic(request.getSession().getServletContext().getRealPath("/upload/") + users.get(i).getHeadPic());
        }

        Workbook workbook = ExcelExportUtil.exportBigExcel(new ExportParams("用户一览表", "用户表"), Consumer.class, users);

        try {
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("用户表.xls", "UTF-8"));
            response.setContentType("application/vnd.ms-excel");
            workbook.write(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void userImport() {
        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        params.setHeadRows(1);
        List<Consumer> list = ExcelImportUtil.importExcel(new File("E:/文档/用户表.xls"), Consumer.class, params);
        for (int i = 0; i < list.size(); i++) {

            System.out.println(list.get(i));
        }

    }
}
