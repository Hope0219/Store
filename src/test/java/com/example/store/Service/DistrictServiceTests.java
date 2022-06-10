package com.example.store.Service;

import com.example.store.entity.District;
import com.example.store.service.IDistrictService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DistrictServiceTests {
    @Autowired
    IDistrictService iDistrictService;
    @Test
    public void getbyparent(){
        List<District> byParent = iDistrictService.getByParent("632600");
        System.out.println(byParent);
    }
}
