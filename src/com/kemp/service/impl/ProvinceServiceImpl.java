package com.kemp.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kemp.dao.ProvinceDao;
import com.kemp.dao.impl.ProvinceDaoImpl;
import com.kemp.domain.Province;
import com.kemp.service.ProvinceService;
import com.kemp.util.JedisUtils;
import redis.clients.jedis.Jedis;

import java.util.List;

public class ProvinceServiceImpl implements ProvinceService {

    private ProvinceDao dao = new ProvinceDaoImpl();

    @Override
    public List<Province> findAllProvinces() {
        return dao.findAllProvinces();
    }

    @Override
    public String findAllProvincesToJson(){
        String province = null;
        boolean redisReady = true;
        Jedis jedis = null;
        try{
            jedis = JedisUtils.getJedis();
            //访问Redis
            province = jedis.get("province");
        } catch (Exception e){
            System.out.println("Redis未部署，直接访问数据库");
            redisReady = false;
        }

        if (province == null || province.length() == 0){
            System.out.println("访问数据库");
            //Redis中不存在省份信息，则访问数据库
            List<Province> provinceList = dao.findAllProvinces();
            //转换省份信息为json
            ObjectMapper mapper = new ObjectMapper();
            try {
                province = mapper.writeValueAsString(provinceList);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            if (redisReady){
                jedis.set("province", province);
            }
        } else {
            System.out.println("访问Redis");
        }
        System.out.println("province:"+province);
        return province;
    }
}
