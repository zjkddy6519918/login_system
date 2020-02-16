package com.kemp.test;

import com.kemp.domain.User;
import com.kemp.service.UserService;
import com.kemp.service.impl.UserServiceImpl;
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

    @Test
    public void testCheckUsername() throws SQLException{
        String sql = "select 1 from user where username = ?";
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        boolean flag = true;
        try{
            Integer rs = template.queryForObject(sql, Integer.class,"zhangsan");
            flag = (rs == 1);
        } catch (Exception e){
            flag = false;
        }
        System.out.println(flag);
    }

    @Test
    public void testLogin() throws SQLException{
        UserService service = new UserServiceImpl();
        User user = new User();
        user.setUsername("kemp");
        user.setPassword("123");
        user.setAge(0);
        System.out.println(service.login(user));
    }
}
