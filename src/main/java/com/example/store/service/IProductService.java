package com.example.store.service;

import com.example.store.entity.Product;
import com.example.store.entity.vo.productvo;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IProductService {
    List<productvo> gethotlist();
    Product findbyId(Integer id);
}
