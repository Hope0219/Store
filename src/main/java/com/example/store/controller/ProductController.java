package com.example.store.controller;

import com.example.store.entity.Product;
import com.example.store.entity.vo.productvo;
import com.example.store.service.IProductService;
import com.example.store.service.ex.NoproductException;
import com.example.store.service.impl.ProductService;
import com.example.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController extends BaseController{

    @Autowired
    IProductService productService;

    @RequestMapping("hotlist")
    public JsonResult<List<productvo>> gethotlist(){
        List<productvo> list = productService.gethotlist();
//        System.out.println(list.toString());
        return new JsonResult<>(OK,list);
    }

    @RequestMapping("{id}/details")
    public JsonResult<Product> findbyId(@PathVariable("id") Integer id){
        Product product = productService.findbyId(id);
        if(product==null){
            throw new NoproductException("产品不存在");
        }
        return new JsonResult<>(OK,product);
    }
}
