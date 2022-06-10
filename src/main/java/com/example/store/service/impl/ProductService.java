package com.example.store.service.impl;

import com.example.store.entity.Product;
import com.example.store.entity.vo.productvo;
import com.example.store.mapper.ProductMapper;
import com.example.store.service.IProductService;
import com.example.store.service.ex.NoproductException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductService implements IProductService {
    @Autowired
    ProductMapper productMapper;


    @Override
    public List<productvo> gethotlist() {
        List<productvo> product = productMapper.gethotlist();
//        for (Product list : product) {
//            list.setPriority(null);
//            list.setCreated_user(null);
//            list.setCreated_time(null);
//            list.setModified_user(null);
//            list.setModified_time(null);
//        }
        return product;
    }
    @Override
    public  Product findbyId(Integer id){
        Product product = productMapper.findbyId(id);
        if(product==null){
            throw new NoproductException();
        }
        return product;
    }
}
