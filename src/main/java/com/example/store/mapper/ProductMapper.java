package com.example.store.mapper;

import com.example.store.entity.Product;
import com.example.store.entity.vo.productvo;


import java.util.List;

public interface ProductMapper {
     List<productvo> gethotlist();

     Product findbyId(Integer id);
}
