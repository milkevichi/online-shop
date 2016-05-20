package com.milkevich.dao;

import com.milkevich.model.Product;

import java.util.List;

/**
 * Created by imilkevich on 14/05/16.
 **/
public interface ProductDao {
    void addProduct(Product product);

    Product findProductById(Integer id);

    List<Product> getProductsList(String searchTerm);

    List<Product> getProductListByCategoryIdAndTerm(Integer id, String searchTerm);
}
