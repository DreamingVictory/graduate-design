package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "animal")
public class Animal implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String img;
    private String title;
    private Double ciurPic;
    private Double oriPic;
    private String discount;
    private Integer count;
    private Integer categoryId;
    private Date pubDate;
}
