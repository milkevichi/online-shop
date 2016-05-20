package com.milkevich.web.controller;

import com.milkevich.model.OrderUnit;
import com.milkevich.service.CategoryService;
import com.milkevich.service.ProductService;
import com.milkevich.model.User;
import com.milkevich.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by imilkevich on 12.05.2016.
 */
@Controller
public class PublicController {

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = {"/", "/welcome**"}, method = RequestMethod.GET)
    public ModelAndView defaultPage(@RequestParam(value = "categoryId", required = false) Integer categoryId,
                                    @RequestParam(value = "searchTerm", required = false) String searchTerm,
                                    HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title", "Online Shop");
        modelAndView.addObject("message", "This is initial page!");
        if (categoryId != null) {
            modelAndView.addObject("rootCategories", categoryService.getCategoryListById(categoryId));
            modelAndView.addObject("productsList", productService.getProductListByCategoryIdAndTerm(categoryId, searchTerm));
            modelAndView.addObject("currentCategoryId", categoryId);
        } else {
            modelAndView.addObject("rootCategories", categoryService.getRootCategories());
            modelAndView.addObject("productsList", productService.getProductsList(searchTerm));
        }

        modelAndView.addObject(Constants.ORDER_UNIT_PARAM, new OrderUnit());
        Object addedOrder = session.getAttribute(Constants.ADDED_ORDER_PARAM);
        if (addedOrder != null) {
            OrderUnit orderUnit = (OrderUnit) addedOrder;
            modelAndView.addObject("modalMessage", String.format("You have added product to cart: %s (%s)",
                    orderUnit.getProduct().getName(), orderUnit.getCount()));
            session.removeAttribute(Constants.ADDED_ORDER_PARAM);
        }

        Object checkout = session.getAttribute(Constants.CHECKOUT_COMPLETE_PARAM);
        if (checkout != null) {
            modelAndView.addObject("modalMessage", String.format(
                    "Your order #%s is being processed. Thank you for buying in our store!", checkout));
            session.removeAttribute(Constants.CHECKOUT_COMPLETE_PARAM);
        }

        Object buyNow = session.getAttribute(Constants.BUYNOW_COMPLETE_PARAM);
        if (buyNow != null) {
            modelAndView.addObject("modalMessage", String.format(
                    "Your fast order #%s is being processed. Thank you for buying in our store! " +
                            "Please continue shopping", buyNow));
            session.removeAttribute(Constants.BUYNOW_COMPLETE_PARAM);
        }

        modelAndView.setViewName("welcome");

        return modelAndView;
    }

    @RequestMapping(value = "/admin/main/**", method = RequestMethod.GET)
    public ModelAndView adminPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title", "Spring Security Login Form - Database Authentication");
        modelAndView.addObject("message", "This page is for Admin only!");
        modelAndView.setViewName("admin");
        return modelAndView;
    }

    @RequestMapping(value = "/login")
    public ModelAndView loginPage(@RequestParam(value = "error", required = false) String error,
                                  @RequestParam(value = "logout", required = false) String logout) {
        ModelAndView modelAndView = new ModelAndView();
        if (error != null) {
            modelAndView.addObject("error", "Invalid username and password!");
        }

        if (logout != null) {
            modelAndView.addObject("msg", "You've been logged out successfully.");
        }

        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ModelAndView addUserPage(@ModelAttribute("newUser") User user) {
        ModelAndView modelAndView = new ModelAndView();

        userService.addUser(user);

        modelAndView.addObject("msg", "You have been successfully signed on. Please sign in.");
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = "/signOn", method = RequestMethod.GET)
    public ModelAndView signOnPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("newUser", new User());
        modelAndView.setViewName("signOn");
        return modelAndView;
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView errorPage() {
        ModelAndView modelAndView = new ModelAndView();

        //check if user is login
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            System.out.println(userDetail);

            modelAndView.addObject("username", userDetail.getUsername());

        }

        modelAndView.setViewName("403");
        return modelAndView;
    }
}
