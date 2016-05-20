package com.milkevich.web.controller;

import com.milkevich.model.OrderUnit;
import com.milkevich.service.OrderService;
import com.milkevich.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by imilkevich on 19.05.2016.
 */
@Controller
@RequestMapping("/order/**")
public class OrderController {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/buyNow", method = RequestMethod.POST)
    public ModelAndView createProductPage(@ModelAttribute(Constants.ORDER_UNIT_PARAM) OrderUnit orderUnit,
                                          HttpSession session) {
        orderUnit.setProduct(productService.findProductById(orderUnit.getProductId()));

        Integer orderNum = orderService.processCheckout(Collections.singletonList(orderUnit));

        ModelAndView modelAndView = new ModelAndView();
        session.setAttribute(Constants.BUYNOW_COMPLETE_PARAM, orderNum);

        modelAndView.setViewName("redirect:/welcome");
        return modelAndView;
    }

    @RequestMapping(value = "/addOrderUnit", method = RequestMethod.POST)
    public ModelAndView createProductPage(@ModelAttribute(Constants.ORDER_UNIT_PARAM) OrderUnit orderUnit,
                                          @RequestParam(value = "from", required = false) String from,
                                          HttpSession session) {

        Object cartObj = session.getAttribute(Constants.USER_CART_PARAM);
        List<OrderUnit> units = cartObj == null ? new ArrayList<OrderUnit>() : (List<OrderUnit>) cartObj;
        orderUnit.setProduct(productService.findProductById(orderUnit.getProductId()));

        boolean alreadyAdded = false;

        Iterator<OrderUnit> iterator = units.iterator();

        while (iterator.hasNext()) {
            OrderUnit unit = iterator.next();
            if (unit.getProductId().equals(orderUnit.getProductId())) {
                alreadyAdded = true;
                unit.setCount(unit.getCount() + orderUnit.getCount());
                if (unit.getCount() < 1) {
                    iterator.remove();
                } else {
                    orderUnit = unit;
                }
            }
        }

        if (!alreadyAdded) {
            units.add(orderUnit);
        }

        session.setAttribute(Constants.USER_CART_PARAM, units);

        if ("welcome".equals(from)) {
            session.setAttribute(Constants.ADDED_ORDER_PARAM, orderUnit);
        }

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("redirect:/" + (StringUtils.isEmpty(from) ? "welcome" : from));
        return modelAndView;
    }

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public ModelAndView createProductPage(HttpSession session) {
        Object cartObj = session.getAttribute(Constants.USER_CART_PARAM);
        List<OrderUnit> units = cartObj == null ? new ArrayList<OrderUnit>() : (List<OrderUnit>) cartObj;

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(Constants.USER_CART_PARAM, units);
        modelAndView.addObject(Constants.ORDER_UNIT_PARAM, new OrderUnit());
        modelAndView.setViewName("cart");
        return modelAndView;
    }


    @RequestMapping(value = "/checkout", method = RequestMethod.POST)
    public ModelAndView checkout(HttpSession session) {
        Object cartObj = session.getAttribute(Constants.USER_CART_PARAM);
        List<OrderUnit> units = cartObj == null ? null : (List<OrderUnit>) cartObj;

        Integer orderNum = null;

        if (!CollectionUtils.isEmpty(units)) {
            orderNum = orderService.processCheckout(units);
        }

        session.removeAttribute(Constants.USER_CART_PARAM);

        ModelAndView modelAndView = new ModelAndView();
        session.setAttribute(Constants.CHECKOUT_COMPLETE_PARAM, orderNum);

        modelAndView.setViewName("redirect:/welcome");
        return modelAndView;
    }
}
