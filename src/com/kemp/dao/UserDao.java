package com.kemp.dao;

import com.kemp.domain.User;

import java.util.List;

public interface UserDao {
    public List<User> findAll();
    public User findUserByUsernameAndPassword(String username, String password);
    public void add(User user);
    public void delete(int id);
    public User findUserById(int id);
    public void update(User user);
}
