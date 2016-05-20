package com.milkevich.web.controller;

import com.milkevich.model.Category;
import com.milkevich.model.Product;
import com.milkevich.service.CategoryService;
import com.milkevich.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/admin/**")
public class EditProductsController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    public ModelAndView addProductPage(@ModelAttribute("newProduct") Product product,
                                       @RequestParam("file") MultipartFile file) {
        ModelAndView modelAndView = new ModelAndView();
        if (file != null && file.getSize() > 0) {
            try {
                byte[] image = file.getBytes();
                product.setImage(image);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        productService.addProduct(product);

        modelAndView.addObject("message", "Product has been added.");
        modelAndView.setViewName("admin");
        return modelAndView;
    }

    @RequestMapping(value = "/createProduct", method = RequestMethod.GET)
    public ModelAndView createProductPage(@RequestParam(value = "productId", required = false) Integer productId) {
        ModelAndView modelAndView = new ModelAndView();
        Product product = productId != null ? productService.findProductById(productId) : new Product();

        modelAndView.addObject("product", product);
        modelAndView.addObject("title", "Create / edit product");
        modelAndView.addObject("categorylist", getListCategories());
        modelAndView.setViewName("createProduct");
        return modelAndView;
    }

    @RequestMapping(value = "/addCategory", method = RequestMethod.POST)
    public ModelAndView addCategoryPage(@ModelAttribute("newCategory") Category category) {
        ModelAndView modelAndView = new ModelAndView();

        categoryService.addCategory(category);

        modelAndView.addObject("message", "Category has been added.");
        modelAndView.setViewName("admin");
        return modelAndView;
    }

    @RequestMapping(value = "/createCategory", method = RequestMethod.GET)
    public ModelAndView createCategoryPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("newCategory", new Category());
        modelAndView.addObject("parentlist", getListCategories());
        modelAndView.addObject("title","Create new category");
        modelAndView.setViewName("createCategory");
        return modelAndView;
    }

    private Map<Integer, String> getListCategories() {
        Map<Integer, String> categoriesIdName = new LinkedHashMap<>();

        List<Category> categoryList = categoryService.getRootCategories();

        int i = 0;
        for (Category category : categoryList) {
            categoriesIdName.put(category.getCategoryId(), category.getName());
            createTreeOfCategories(categoriesIdName, category);
        }

        return categoriesIdName;

    }


    private void createTreeOfCategories(Map<Integer, String> tree, Category currentCategory) {
        Set<Category> children = currentCategory.getChildren();
        int level = currentCategory.getLevel() + 1;
        if (children.size() != 0) {
            for (Category category : children) {
                category.setLevel(level);
                StringBuilder countOfDash = new StringBuilder();
                for (int j = 0; j < category.getLevel() * 2; j++) {
                    countOfDash.append("-");
                }
                tree.put(category.getCategoryId(), countOfDash.toString() + category.getName());
                createTreeOfCategories(tree, category);
            }
        }
    }
}
