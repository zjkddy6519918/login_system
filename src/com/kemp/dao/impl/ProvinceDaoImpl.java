package com.kemp.dao.impl;

import com.kemp.dao.ProvinceDao;
import com.kemp.domain.Province;
import com.kemp.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ProvinceDaoImpl implements ProvinceDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Province> findAllProvinces() {
        String sql = "select * from province";
        List<Province> rs = template.query(sql, new BeanPropertyRowMapper<Province>(Province.class));
        return rs;
    }
}
