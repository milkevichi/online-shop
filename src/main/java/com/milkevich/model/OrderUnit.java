package com.milkevich.model;

import java.math.BigDecimal;

/**
 * Created by imilkevich on 19.05.2016.
 */
public class OrderUnit {

    private Integer orderId;
    private Integer number;
    private User user;
    private Product product;
    private Integer productId;
    private Integer count = 1;
    private BigDecimal amount;

    public OrderUnit() {
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
        if (product != null) {
            setAmount(product.getPrice().multiply(BigDecimal.valueOf(count)));
        }
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
