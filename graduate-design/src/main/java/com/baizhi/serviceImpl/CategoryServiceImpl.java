package com.baizhi.serviceImpl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.dto.PageBeanDto;
import com.baizhi.entity.Category;
import com.baizhi.entity.Consumer;
import com.baizhi.mapper.CategoryMapper;
import com.baizhi.service.CategoryService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public List<Category> queryAllCategory() {
        List<Category> categories = categoryMapper.queryAllCategory();
            if(categories.isEmpty()){
            throw new RuntimeException("类别还没有添加，请添加后查询");
        }
        return categories;
    }

    @Override
    public PageBeanDto<Category> queryAllCategory(Integer page, Integer rows) {
        List<Category> categories = categoryMapper.queryAllCategory();
        int count = categoryMapper.selectCount(null);
        PageBeanDto<Category> pageBeanDto=new PageBeanDto<>();
        pageBeanDto.setTotal(count);
        pageBeanDto.setRows(categories);
        return pageBeanDto;
    }

    @Override
    public void insertCategory(Category category) {
        categoryMapper.insert(category);
    }

    @Override
    public void exportCategory(HttpServletResponse response, HttpServletRequest request) {
        List<Category> categories = categoryMapper.selectAll();

        Workbook workbook = ExcelExportUtil.exportBigExcel(new ExportParams("类别一览表", "类别表"), Category.class, categories);

        try {
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("类别表.xls", "UTF-8"));
            response.setContentType("application/vnd.ms-excel");
            workbook.write(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
