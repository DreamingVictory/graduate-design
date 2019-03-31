package com.baizhi.entity;

import lombok.*;

import javax.persistence.Table;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
//订单项
public class CartItem implements Serializable {
    private Animal animal;
    private Integer count=1;//数量
    private Double totalPrice;//小计
}
