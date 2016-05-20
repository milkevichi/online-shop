package com.milkevich.service;

import com.milkevich.dao.ProductDao;
import com.milkevich.model.Product;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by imilkevich on 14/05/16.
 **/
@Transactional
public class ProductService {

    private ProductDao productDao;

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    public void addProduct(Product product) {
        product.setCreateDate(new Date());
//        Category category = findCategoryById(product.getCategoryId());
//        if (category != null) {
//            product.setCategory(category);
//        }
        productDao.addProduct(product);
    }


    public List<Product> getProductsList(String searchTerm) {
        return productDao.getProductsList(searchTerm);
    }

    public List<Product> getProductListByCategoryIdAndTerm(Integer id, String searchTerm) {
        return productDao.getProductListByCategoryIdAndTerm(id, searchTerm);
    }
    public Product findProductById(Integer id) {
        return productDao.findProductById(id);
    }

}
