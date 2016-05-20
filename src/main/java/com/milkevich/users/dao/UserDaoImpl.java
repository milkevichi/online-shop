package com.milkevich.users.dao;

import com.milkevich.model.User;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by imilkevich on 13.05.2016.
 */
public class UserDaoImpl implements UserDao {
    private SessionFactory sessionFactory;

    @Override
    public User findByUsername(String username) {
        List<User> users = new ArrayList<>();

        users = getSessionFactory().getCurrentSession().createQuery("from User where username like ?").
                setParameter(0, username).list();

        if (users.size() > 0) {
            return users.get(0);
        } else {
             return null;
        }
    }


    @Override
    public void addUser(User user) {
        getSessionFactory().getCurrentSession().save(user);
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
