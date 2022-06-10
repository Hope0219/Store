package com.example.store.mapper;

import com.example.store.entity.Cart;
import com.example.store.entity.vo.Cartvo;

import java.util.List;

public interface CartsMapper {
    //商品加入购物车
    Integer addtocart(Cart cart);
    //检查购物车中是否有该商品
    Cart cartByuidandpid(Integer uid,Integer pid);
//    Integer updatecart(Integer num,Integer uid,Integer pid);
    //更新购物车商品数量
    Integer updatecart(Cart cart);

    List<Cartvo> getcartbyuid(Integer uid);

}
