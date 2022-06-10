package com.example.store.Service;

import com.example.store.entity.Cart;
import com.example.store.mapper.CartsMapper;
import com.example.store.service.ICartsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CartServiceTests {

    @Autowired
     ICartsService cartsService;

    @Autowired
     CartsMapper cartsMapper;
    @Test
    public void addtocart(){
        cartsService.addtocart(1,10000001,2,"管理");


        Cart cart=new Cart();
        cart.setUid(1);
        cart.setPid(10000001);
        cart.setNum(2);
//        cart.setPrice(123L);

        Integer row = cartsMapper.addtocart(cart);
        System.out.println(row);
    }
}
