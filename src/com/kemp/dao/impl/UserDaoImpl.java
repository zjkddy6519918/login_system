package com.kemp.dao.impl;

import com.kemp.dao.UserDao;
import com.kemp.domain.User;
import com.kemp.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDaoImpl implements UserDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());


    @Override
    public List<User> findAll() {
        String sql = "select * from user";
        List<User> queryRs = template.query(sql, new BeanPropertyRowMapper<User>(User.class));
        return queryRs;
    }

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        try{
            String sql = "select * from user where username=? and password = ?";
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username, password);
            return user;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void add(User user) {
        String sql = "insert into user(name,gender,age,address,qq,email) values(?,?,?,?,?,?)";
        template.update(sql, user.getName(),user.getGender(),user.getAge(),user.getAddress(),user.getQq(),user.getEmail());
    }

    @Override
    public void delete(int id) {
        String sql = "delete from user where id=?";
        template.update(sql, id);
    }

    @Override
    public User findUserById(int id) {
        String sql = "select * from user where id=?";
        User user = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
        return user;
    }

    @Override
    public void update(User user) {
        String sql = "update user set name=?,gender=?,age=?,address=?,qq=?,email=? where id=?";
        template.update(sql, user.getName(),user.getGender(),user.getAge()
                ,user.getAddress(),user.getQq(),user.getEmail(),user.getId());
    }
}
