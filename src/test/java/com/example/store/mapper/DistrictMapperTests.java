package com.example.store.mapper;

import com.example.store.entity.Address;
import com.example.store.entity.District;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DistrictMapperTests {

    @Autowired
    DistrictMapper districtMapper;

    @Test
    public void insert(){
        List<District> byParent = districtMapper.findByParent("632500");
        for (District d:byParent
             ) {
            System.out.println(d);

        }
    }
}
