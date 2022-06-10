package com.example.store.service;

import com.example.store.entity.District;

import java.util.List;

public interface IDistrictService {

    List<District> getByParent(String parent);
    String getByCode(String code);
}
