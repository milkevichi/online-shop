package com.milkevich.service;

import com.milkevich.users.dao.UserDao;
import com.milkevich.model.User;
import com.milkevich.model.UserRole;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by imilkevich on 14/05/16.
 **/
@Transactional
public class UserService {
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void addUser(User user) {
        user.getUserRoles().add(new UserRole(user,"ROLE_USER"));
        userDao.addUser(user);
    }

    public User findByName(String username) {
        return userDao.findByUsername(username);
    }
}
