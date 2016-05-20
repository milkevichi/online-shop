package com.milkevich.service;

import com.milkevich.dao.OrderDao;
import com.milkevich.model.OrderUnit;
import com.milkevich.Utils.ContextUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by imilkevich on 19.05.2016.
 */
@Transactional
public class OrderService {

    private static final Logger logger = Logger.getLogger(OrderService.class.getName());

    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public Integer processCheckout(List<OrderUnit> units) {
        Integer newOrderNumber = orderDao.getNewOrderNumber();

        List<String> names = new ArrayList<>();
        for (OrderUnit unit : units) {
            unit.setNumber(newOrderNumber);
            unit.setUser(userService.findByName(ContextUtils.currentUserDetails().getUsername()));
            names.add(String.format("Product: %s (count %s); amount: %s",
                    unit.getProduct().getName(), unit.getCount(), unit.getAmount()));
        }

        orderDao.saveAll(units);

        logger.log(Level.INFO, names.toString());

        return newOrderNumber;
    }

    private OrderDao orderDao;

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

}
