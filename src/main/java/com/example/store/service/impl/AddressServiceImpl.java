package com.example.store.service.impl;

import com.example.store.entity.Address;
import com.example.store.mapper.AddressMapper;
import com.example.store.service.IAddressService;
import com.example.store.service.IDistrictService;
import com.example.store.service.ex.AddressCountException;
import com.example.store.service.ex.DeleteaddressException;
import com.example.store.service.ex.InsertException;
import com.example.store.service.ex.UpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    AddressMapper addressMapper;

    @Value("${user.address.max-count}")
    private Integer maxcount;
    
    @Autowired
    IDistrictService iDistrictService;

    @Override
    public void addnewAddress(Integer uid, String username, Address address) {

        Integer count = addressMapper.countByUid(uid);


    if(count>=maxcount){
            throw new AddressCountException("收货地址超出上限！！");
        }
        String provincename = iDistrictService.getByCode(address.getProvinceCode());
        String cityname = iDistrictService.getByCode(address.getCityCode());
        String areaname = iDistrictService.getByCode(address.getAreaCode());

        address.setProvinceName(provincename);
        address.setCityName(cityname);
        address.setAreaName(areaname);

        Integer isdefault=count==0?1:0;//1默认 0不是默认
        address.setUid(uid);
        address.setIsDefault(isdefault);
        address.setCreated_user(username);
        address.setCreated_time(new Date());
        address.setModified_user(username);
        address.setModified_time(new Date());
        Integer row = addressMapper.insert(address);
        if(row!=1){
            throw new InsertException("收货地址产生未知异常");
        }

    }

    @Override
    public List<Address> getaddressByUid(Integer uid) {
        List<Address> address = addressMapper.getaddressByUid(uid);

        return address;
    }



    @Override
    public void setdefault(Integer aid,Integer uid,String username) {
        Integer integer = addressMapper.setnoDefault(uid);
        if(integer<1){
            throw new UpdateException("地址更新失败");
        }
        Integer row = addressMapper.setDefault(aid,username,new Date());
        if(row<1){
            throw new UpdateException("默认地址失败");
        }
    }

    @Override
    public void deleteaddress(Integer aid) {
        Integer row = addressMapper.deleteaddress(aid);
        if(row<1){
            throw new DeleteaddressException("删除失败");
        }
    }


}
