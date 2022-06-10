package com.example.store.service.impl;

import com.example.store.entity.District;
import com.example.store.mapper.DistrictMapper;
import com.example.store.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class DistrictServiceImpl implements IDistrictService {


    @Autowired
    DistrictMapper districtMapper;

    @Override
    public List<District> getByParent(String parent) {
        List<District> districtList = districtMapper.findByParent(parent);
        for (District d:districtList
             ) {  d.setId(null);
                  d.setParent(null);
        }

        return districtList;
    }

    @Override
    public String getByCode(String code) {
        return districtMapper.getByCode(code);
    }
}
