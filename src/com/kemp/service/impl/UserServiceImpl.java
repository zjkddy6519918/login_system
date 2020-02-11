package com.kemp.service.impl;

import com.kemp.dao.UserDao;
import com.kemp.dao.impl.UserDaoImpl;
import com.kemp.domain.User;
import com.kemp.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao dao = new UserDaoImpl();

    @Override
    public List<User> findAll() {
        return dao.findAll();
    }

    @Override
    public User login(User loginUser) {
        return dao.findUserByUsernameAndPassword(loginUser.getUsername(), loginUser.getPassword());
    }

    @Override
    public void addUser(User user) {
        dao.add(user);
    }

    @Override
    public void delUser(int id) {
        dao.delete(id);
    }

    @Override
    public User findUser(String id) {
        return dao.findUserById(Integer.parseInt(id));
    }

    @Override
    public void updateUser(User user) {
        dao.update(user);
    }

    @Override
    public void delSelectedUsers(String[] ids) {
        if (ids != null){
            for (String id : ids) {
                dao.delete(Integer.parseInt(id));
            }
        }
    }
}
