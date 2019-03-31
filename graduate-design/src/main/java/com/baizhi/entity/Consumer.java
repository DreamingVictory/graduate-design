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
@Table(name = "consumer")
public class Consumer implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    @Excel(name = "Id")
    private Integer id;
    @Excel(name = "昵称")
    private String name;
    @Excel(name = "真实姓名")
    private String realName;
    @Excel(name = "密码")
    private String password;
    @Excel(name = "电话")
    private String phone;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    @Excel(name = "注册时间")
    private Date registDate;
    @Excel(name = "用户状态")
    private Integer status;
    @Excel(name = "性别")
    private String sex;
    @Excel(name = "头像", type = 2, width = 20, height = 40)
    private String headPic;
    @Excel(name = "城市")
    private String province;

}
