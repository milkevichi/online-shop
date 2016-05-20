package com.milkevich.dao;

import com.milkevich.model.OrderUnit;

import java.util.List;

/**
 * Created by imilkevich on 19.05.2016.
 */
public interface OrderDao {
    Integer getNewOrderNumber();

    void saveAll(List<OrderUnit> units);
}
