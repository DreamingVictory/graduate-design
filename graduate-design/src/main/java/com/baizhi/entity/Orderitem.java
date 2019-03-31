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
@Table(name = "orderitem")
public class Orderitem implements Serializable {
    @Id
    private Integer orderitemId;
    private Integer animalId;
    private Integer count;
    private Double totalPrice;
    private Integer orderId;
}
