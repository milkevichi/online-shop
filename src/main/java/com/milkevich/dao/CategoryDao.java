package com.milkevich.dao;

import com.milkevich.model.Category;

import java.util.List;

/**
 * Created by imilkevich on 19.05.2016.
 */
public interface CategoryDao {
    void addCategory(Category category);

    List<Category> getCategoriesWithoutParent();

    Category findCategoryById(Integer id);
}
