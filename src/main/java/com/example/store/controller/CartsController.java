package com.example.store.controller;

import com.example.store.entity.Cart;
import com.example.store.entity.Product;
import com.example.store.entity.vo.Cartvo;
import com.example.store.service.ICartsService;
import com.example.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("carts")
public class CartsController extends BaseController{

    @Autowired
    private ICartsService iCartsService;

    @RequestMapping({"","/"})
    public JsonResult<List<Cartvo>> getcartbyuid(HttpSession session){
        Integer uid = getuidFromSession(session);
        List<Cartvo> cartvo = iCartsService.getcartbyuid(uid);
        return new  JsonResult<List<Cartvo>>(OK,cartvo);
    }



    @RequestMapping("add_to_cart")
    public JsonResult<Void> addcarts(HttpSession session, Integer pid, Integer amount){
        System.out.println(session);
        System.out.println("pid=" + pid);
        System.out.println("amount=" + amount);
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);

        iCartsService.addtocart(uid,pid,amount,username);

        return new JsonResult<>(OK);
    }

}
