package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu implements Serializable {
    @Id
    private Integer id;
    private String title;
    private String iconcls;
    private String url;
    private Integer parentId;
    @Transient
    private List<Menu> list;

}
