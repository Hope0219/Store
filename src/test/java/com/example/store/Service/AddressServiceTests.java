package com.example.store.Service;

import com.example.store.entity.Address;
import com.example.store.entity.Product;
import com.example.store.entity.vo.productvo;
import com.example.store.service.IAddressService;
import com.example.store.service.IProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressServiceTests {
    @Autowired
    IAddressService iAddressService;

    @Autowired
    IProductService productService;
    @Test
    public void addnewaddress(){
        Address address=new Address();

        address.setPhone("123456789");
        address.setAddress("雁塔区小寨赛格");
        address.setName("男朋友");
        iAddressService.addnewAddress(1,"管理员",address);
    }
    @Test
    public void gethot(){
        List<productvo> list = productService.gethotlist();
//        for (Product item : list) {
            System.out.println(list);
//        }

    }
    @Test
    public  void findbyId(){
        Product product = productService.findbyId(10000034);
        System.out.println(product);
    }
}
