package com.example.store.mapper;

import com.example.store.entity.Address;
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
public class AddressMapperTests {

    @Autowired
    AddressMapper addressMapper;

    @Autowired
    ProductMapper productMapper;
    @Test
    public void insert(){
        Address address=new Address();
        address.setUid(18);
        address.setName("admin");
        address.setPhone("17858802974");
        address.setAddress("雁塔区小寨赛格");
        Integer row = addressMapper.insert(address);
        System.out.println(row);
    }

    @Test
    public void gethot(){
        List<productvo> gethotlist = productMapper.gethotlist();
        System.out.println(gethotlist);
    }
}
