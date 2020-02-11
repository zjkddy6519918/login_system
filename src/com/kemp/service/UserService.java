package com.kemp.service;

import com.kemp.domain.User;

import java.util.List;

public interface UserService {
    public List<User> findAll();
    public User login(User loginUser);
    public void addUser(User user);
    public void delUser(int id);
    public User findUser(String id);
    public void updateUser(User user);
}
