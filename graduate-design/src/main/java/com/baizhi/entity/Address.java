package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "address")
public class Address implements Serializable {
    @Id
    private Integer id;
    private String reciveName;
    private String province;
    private String city;
    private String detailAddress;
    private String telphone;
    private Integer userId;
}
