package com.example.store.controller;

import com.example.store.entity.District;
import com.example.store.service.IDistrictService;
import com.example.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("district")
public class DistrictController extends BaseController{
    @Autowired
    IDistrictService iDistrictService;

    @RequestMapping({"/",""})
    public JsonResult<List<District>>getByParent(String parent){
        List<District> list = iDistrictService.getByParent(parent);

        return new JsonResult<>(OK,list);
    }
}
