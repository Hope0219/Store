package com.example.store.service.impl;

import com.example.store.entity.Cart;
import com.example.store.entity.Product;
import com.example.store.entity.vo.Cartvo;
import com.example.store.entity.vo.productvo;
import com.example.store.mapper.CartsMapper;
import com.example.store.mapper.ProductMapper;
import com.example.store.service.ICartsService;
import com.example.store.service.ex.InsertException;
import com.example.store.service.ex.UpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CartsServiceImpl implements ICartsService {
    @Autowired
    private CartsMapper cartsMapper;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public void addtocart(Integer uid,Integer pid,Integer num,String username) {
        Cart cart1 = cartsMapper.cartByuidandpid(uid, pid);
        //查询原购物车中是否存在该商品
        if(cart1 == null)
        {Product product = productMapper.findbyId(pid);
        Long price = product.getPrice();
        Cart cart=new Cart();
        cart.setUid(uid);
        cart.setPid(pid);
        cart.setPrice(price);
        cart.setNum(num);
        cart.setCreated_user(username);
        cart.setCreated_time(new Date());
        cart.setModified_user(username);
        cart.setModified_time(new Date());
            Integer row = cartsMapper.addtocart(cart);

            if(row!=1) {
                throw new InsertException("插入商品时出现错误");
            }
        }else{
            //更新原购物车中商品的数量
            Integer num1 = cart1.getNum();
            Integer num2=num1+num;
            cart1.setNum(num2);
            Integer row = cartsMapper.updatecart(cart1);
            if(row!=1){
                throw new UpdateException("更新数量失败");
            }

        }

    }

    @Override
    public List<Cartvo> getcartbyuid(Integer uid) {
        List<Cartvo> cartvo = cartsMapper.getcartbyuid(uid);
        return cartvo;
    }
}
