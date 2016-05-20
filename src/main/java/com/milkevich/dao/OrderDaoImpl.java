package com.milkevich.dao;

import com.milkevich.model.OrderUnit;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;

import java.util.List;

/**
 * Created by imilkevich on 19.05.2016.
 */
public class OrderDaoImpl implements OrderDao {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Integer getNewOrderNumber() {
        Integer maxNumber = (Integer) sessionFactory.getCurrentSession().createCriteria(OrderUnit.class)
                .setProjection(Projections.max("number")).uniqueResult();
        return maxNumber != null ? maxNumber + 1 : 1;
    }

    @Override
    public void saveAll(List<OrderUnit> units) {
        for (OrderUnit unit : units) {
            sessionFactory.getCurrentSession().saveOrUpdate(unit);
        }
    }
}
