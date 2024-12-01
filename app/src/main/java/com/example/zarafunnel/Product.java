package com.example.zarafunnel;
import java.io.Serializable;

public class Product implements Serializable {
    private String name;
    private String price;
    private int imageResId;
    private String size;

    public Product(String name, String price, int imageResId, String size) {
        this.name = name;
        this.price = price;
        this.imageResId = imageResId;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
