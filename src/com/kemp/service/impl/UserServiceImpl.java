package com.kemp.service.impl;

import com.kemp.dao.UserDao;
import com.kemp.dao.impl.UserDaoImpl;
import com.kemp.domain.PageBean;
import com.kemp.domain.User;
import com.kemp.service.UserService;

import java.util.List;
import java.util.Map;

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

    @Override
    public boolean userExist(String username) {
        return dao.findUserByUsername(username);
    }

    @Override
    public void register(String username, String password) {
        dao.addByUsernameAndPassword(username, password);
    }

    @Override
    public PageBean<User> findUsersByPage(int currentPage, int rowCount, Map<String, String[]> condition) {
        PageBean<User> pageBean = new PageBean<>();
        pageBean.setCurrentPage(currentPage);
        pageBean.setRowCount(rowCount);
        int totalCount = dao.findTotalCount(condition);
        pageBean.setTotalCount(totalCount);
        pageBean.setList(dao.findUsersByPage((currentPage-1) * rowCount, rowCount,condition));
        pageBean.setTotalPage(totalCount % rowCount == 0
                ?totalCount / rowCount
                :(totalCount / rowCount + 1));
        return pageBean;
    }
}
