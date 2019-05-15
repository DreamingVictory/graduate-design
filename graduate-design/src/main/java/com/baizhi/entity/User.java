package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    @Excel(name = "Id")
    private Integer id;
    @Excel(name = "电话")
    private String phone;
    @Excel(name = "用户名")
    private String username;
    private String password;
    private String salt;
    @Excel(name = "真实姓名")
    private String realName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    @Excel(name = "注册日期")
    private Date registDate;
    @Excel(name = "用户状态")
    private Integer status;
    @Excel(name = "性别")
    private String sex;
    @Excel(name = "头像", type = 2, width = 20, height = 40)
    private String headPic;
    @Excel(name = "省份")
    private String province;
    @Excel(name = "签名")
    private String sign;

}
