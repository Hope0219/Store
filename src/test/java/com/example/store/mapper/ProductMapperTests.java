package com.example.store.mapper;

import com.example.store.entity.Product;
import com.example.store.entity.vo.productvo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductMapperTests {

    @Autowired
    ProductMapper productMapper;

    @Test
    public void hotlist(){
        List<productvo> list = productMapper.gethotlist();
        System.out.println(list);
    }

}
