package com.kemp.test;

import com.kemp.domain.User;
import com.kemp.util.JDBCUtils;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.List;

public class DruidTest {
    @Test
    public void testConnection() throws SQLException {
        System.out.println(JDBCUtils.getConnection());
    }

    @Test
    public void testQuery() throws SQLException{
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "select * from user";
        List<User> query = template.query(sql, new BeanPropertyRowMapper<User>(User.class));
        System.out.println(query);
    }
}
