package com.milkevich.users.dao;

import com.milkevich.model.User;

/**
 * Created by imilkevich on 13.05.2016.
 */
public interface UserDao {
    User findByUsername(String username);

    void addUser(User user);
}
