package com.milkevich.model;

import org.springframework.security.crypto.codec.Base64;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by imilkevich on 13.05.2016.
 */
public class Product {
    private Integer id;
    private String name;
    private Category category;
    private Integer categoryId;
    private BigDecimal price;
    private Date createDate;
    private byte[] image;

    public Product() {
    }

    public Product(String name, Category category) {
        this.name = name;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageBase64() {
        String imageBase64;
        try {
            if (image != null) {
            byte[] encodeBase64 = Base64.encode(image);
            imageBase64 = new String(encodeBase64, "UTF-8");
            } else {
                imageBase64 = "";
            }
        }catch (UnsupportedEncodingException ex){
            ex.printStackTrace();
            imageBase64 = "";
        }

        return imageBase64;
    }

}
