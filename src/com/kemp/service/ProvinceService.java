package com.kemp.service;

import com.kemp.domain.Province;

import java.util.List;

public interface ProvinceService {

    public List<Province> findAllProvinces();

    public String findAllProvincesToJson();

}
