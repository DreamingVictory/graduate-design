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
    private Integer id;
    private String phone;
    private String username;
    private String password;
    private String salt;
    private String realName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date registDate;
    private Integer status;
    private String sex;
    private String headPic;
    private String province;
    private String sign;

}
