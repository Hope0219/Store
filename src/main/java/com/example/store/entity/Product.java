package com.example.store.entity;

import lombok.Data;

@Data
public class Product extends BaseEntity{
    private Integer id;
    private Integer categoryid;
    private String itemtype;
    private String title;
    private String sellpoint;
    private Long price;
    private Integer num;
    private String image;
    private Integer status;
    private Integer priority;
}
