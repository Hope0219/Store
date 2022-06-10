package com.example.store.service;

import com.example.store.entity.Cart;
import com.example.store.entity.Product;
import com.example.store.entity.vo.Cartvo;
import com.example.store.entity.vo.productvo;

import java.util.List;

public interface ICartsService {
     void addtocart(Integer uid, Integer id, Integer num, String username);
     List<Cartvo> getcartbyuid(Integer uid);
}
