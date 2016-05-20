package com.milkevich.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by imilkevich on 13.05.2016.
 */
public class Category {
    private Integer categoryId;
    private String name;
    private Category parent;
    private Integer parentId;
    private Set<Category> children = new HashSet<>();
    private int level;

    public Category() {
    }

    public Category(String name, Category parent) {
        this.name = name;
        this.parent = parent;
    }

    public Category(String name, Category parent, Set<Category> children) {
        this.name = name;
        this.parent = parent;
        this.children = children;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public Set<Category> getChildren() {
        return children;
    }

    public void setChildren(Set<Category> children) {
        this.children = children;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
