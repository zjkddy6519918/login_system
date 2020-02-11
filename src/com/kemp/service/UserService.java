package com.kemp.service;

import com.kemp.domain.PageBean;
import com.kemp.domain.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    public List<User> findAll();
    public User login(User loginUser);
    public void addUser(User user);
    public void delUser(int id);
    public User findUser(String id);
    public void updateUser(User user);
    public void delSelectedUsers(String[] ids);

    /**
     * 分页条件查询
     * @param currentPage
     * @param rowCount
     * @param condition：条件
     * @return
     */
    public PageBean<User> findUsersByPage(int currentPage, int rowCount, Map<String, String[]> condition);
}
