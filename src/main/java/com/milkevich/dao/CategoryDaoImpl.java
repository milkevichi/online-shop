package com.milkevich.dao;

import com.milkevich.model.Category;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by imilkevich on 19.05.2016.
 */
public class CategoryDaoImpl implements CategoryDao {
    
    private SessionFactory sessionFactory;

    @Override
    public void addCategory(Category category) {
        sessionFactory.getCurrentSession().save(category);
    }

    @Override
    public List<Category> getCategoriesWithoutParent() {
        List<Category> categoryList = new ArrayList<>();
        categoryList = sessionFactory.getCurrentSession().createQuery("from Category where parent_id = null ").list();
        return categoryList;
    }

    @Override
    public Category findCategoryById(Integer id) {
        return (Category) sessionFactory.getCurrentSession().createQuery("from Category where category_id = ?").
                setParameter(0, id).uniqueResult();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
