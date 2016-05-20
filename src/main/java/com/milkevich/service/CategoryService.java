package com.milkevich.service;

import com.milkevich.dao.CategoryDao;
import com.milkevich.model.Category;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by imilkevich on 14/05/16.
 **/
@Transactional
public class CategoryService {

    private CategoryDao categoryDao;

    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public void addCategory(Category category) {
        categoryDao.addCategory(category);
    }

    public List<Category> getRootCategories() {
        return categoryDao.getCategoriesWithoutParent();
    }

    public List<Category> getCategoryListById(Integer id) {
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(categoryDao.findCategoryById(id));
        return categoryList;
    }

    public Category findCategoryById(Integer id) {
        return categoryDao.findCategoryById(id);
    }

}
