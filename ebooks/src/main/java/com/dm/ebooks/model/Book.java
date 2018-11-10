package com.dm.ebooks.model;

public class Book {
    private int bookId;
    private String bookName;
    private int price;
    private int totalPages;
    int publisher;
    private String description;
    public Book(){}

    public Book( String bookName, float price, int totalPages, int publisher, String description) {

        this.bookName = bookName;
        this.price = (int)price;
        this.totalPages = totalPages;
        this.publisher = publisher;
        this.description = description;
    }


    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    /*public Login getPublisher() {
        return publisher;
    }*/

    /*public void setPublisher(Login publisher) {
        this.publisher = publisher;
    }*/

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
