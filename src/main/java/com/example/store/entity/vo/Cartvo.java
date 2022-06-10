package com.example.store.entity.vo;

import lombok.Data;

@Data
public class Cartvo {

    private Integer cid;
    private Integer uid;
    private Integer pid;
    private Long price;
    private String title;
    private Integer amount;
    private String image;
    private Long realprice;

}
