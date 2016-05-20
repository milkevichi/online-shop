package com.milkevich.dao;

import com.milkevich.model.Category;
import com.milkevich.model.Product;
import liquibase.util.StringUtils;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by imilkevich on 14/05/16.
 **/
public class ProductDaoImpl implements ProductDao {
    private SessionFactory sessionFactory;

    private CategoryDao categoryDao;

    @Override
    public void addProduct(Product product) {
        Product mergedProduct = (Product) sessionFactory.getCurrentSession().merge(product);
        sessionFactory.getCurrentSession().saveOrUpdate(mergedProduct);

    }

    @Override
    public List<Product> getProductsList(String searchTerm) {
        String nameSearch = StringUtils.isNotEmpty(searchTerm) ?
                " where UPPER(p.name) like UPPER(:searchKeyword) OR UPPER(p.category.name) like UPPER(:searchKeyword)" : "";

        Query query = sessionFactory.getCurrentSession()
                .createQuery("from Product p" + nameSearch);

        if (StringUtils.isNotEmpty(searchTerm)) {
            query.setParameter("searchKeyword", "%" + searchTerm + "%");
        }

        return query.list();
    }

    @Override
    public Product findProductById(Integer id) {
        return (Product) sessionFactory.getCurrentSession().createQuery("from Product p where p.id = ?").
                setParameter(0, id).uniqueResult();
    }

    @Override
    public List<Product> getProductListByCategoryIdAndTerm(Integer id, String searchTerm) {
        List<Product> productList = new ArrayList<>();

        List<Integer> categoryIdList = new ArrayList<>();
        categoryIdList.add(id);
        createTreeOfCategories(categoryIdList, categoryDao.findCategoryById(id));

        if (!categoryIdList.isEmpty()) {
            String nameSearch = StringUtils.isNotEmpty(searchTerm)
                    ? " and (UPPER(p.name) like UPPER(:searchKeyword) OR UPPER(p.category.name) like UPPER(:searchKeyword))" : "";

            Query query = sessionFactory.getCurrentSession()
                    .createQuery("from Product p where p.categoryId in (:categoryList)" + nameSearch)
                    .setParameterList("categoryList", categoryIdList);

            if (StringUtils.isNotEmpty(searchTerm)) {
                query.setParameter("searchKeyword", "%" + searchTerm + "%");
            }

            productList = query.list();
        }
        return productList;
    }

    private void createTreeOfCategories(List<Integer> categoryIdList, Category currentCategory) {
        Set<Category> children = currentCategory.getChildren();
        if (children.size() != 0) {
            for (Category category : children) {
                categoryIdList.add(category.getCategoryId());
                createTreeOfCategories(categoryIdList, category);
            }
        }
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }
}
