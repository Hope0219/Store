package com.example.store.mapper;

import com.example.store.entity.Address;

import java.util.Date;
import java.util.List;

public interface AddressMapper {


    /**
     *  插入数据的收货地址数据
     * @param address
     * @return  受影响的行数
     */
     Integer insert(Address address);

    /**
     * 统计用户收货地址总数
     * @param uid
     * @return 当前收货地址总数
     */
     Integer countByUid(Integer uid);

     List<Address> getaddressByUid(Integer uid);

     Integer setnoDefault(Integer uid);

     Integer setDefault(Integer aid, String modifieduser, Date modifiedtime);

     Integer deleteaddress(Integer aid);
}
