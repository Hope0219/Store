package com.example.store.service;

import com.example.store.entity.Address;

import java.util.List;

public interface IAddressService {

    void addnewAddress(Integer uid,String username,Address address);

    List<Address> getaddressByUid(Integer uid);

    void setdefault(Integer aid,Integer uid,String username);
    void deleteaddress(Integer aid);
}
