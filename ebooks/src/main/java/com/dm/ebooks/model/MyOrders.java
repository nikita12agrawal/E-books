package com.dm.ebooks.model;

public class MyOrders {
    float price;
    int userId;
    String path;
    String booktitle;

    public  MyOrders()
    {}


    public MyOrders(float price, int userId, String path, String booktitle) {
        this.price = price;
        this.userId = userId;
        this.path = path;
        this.booktitle = booktitle;
    }

    public int getUserId() {
        return userId;
    }


    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getBooktitle() {
        return booktitle;
    }

    public void setBooktitle(String booktitle) {
        this.booktitle = booktitle;
    }


    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
