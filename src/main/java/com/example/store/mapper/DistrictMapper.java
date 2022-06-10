package com.example.store.mapper;

import com.example.store.entity.District;

import java.util.List;

public interface DistrictMapper {


   List<District> findByParent(String parent);

   String getByCode(String code);
}
