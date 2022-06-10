package com.example.store.controller;

import com.example.store.entity.Address;
import com.example.store.service.IAddressService;
import com.example.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("address")
public class AddressController extends BaseController{
    @Autowired
    IAddressService addressService;

    @RequestMapping("addnewaddress")
    public JsonResult<Void> addnewAddress(HttpSession session, Address address){
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);

        System.out.println(session);
        address.setModified_user(username);
        address.setModified_time(new Date());
        address.setCreated_user(username);
        address.setCreated_time(new Date());

        addressService.addnewAddress(uid,username,address);
        return new JsonResult<Void>(OK);
    }
    @RequestMapping({"/",""})
    public JsonResult<List<Address>> getaddressbyuid(HttpSession session){
        Integer uid = getuidFromSession(session);
        List<Address> address = addressService.getaddressByUid(uid);
        return new JsonResult<List<Address>>(OK,address);
    }

    @RequestMapping("{aid}/setDefault")
    public JsonResult<Void> setDefault(@PathVariable("aid") Integer aid,HttpSession session){

//        addressService.setnodefault();
        Integer uid = getuidFromSession(session);

        addressService.setdefault(aid,uid,getUsernameFromSession(session));
        return new JsonResult<Void>(OK);
    }
    @RequestMapping("{aid}/delete")
    public JsonResult<Void> deleteaddress(@PathVariable("aid") Integer aid){
        addressService.deleteaddress(aid);
        return new JsonResult<Void>(OK);
    }
}
