package com.dm.ebooks.model;

public class Chunk {
     private int chunkId;

    public int getChunkId() {
        return chunkId;
    }
    public Chunk(){}

    public Chunk(int chunkId, String topic, int spage, int epage, int bookid, int price, String desc) {
        this.chunkId = chunkId;
        this.topic = topic;
        this.spage = spage;
        this.epage = epage;
        this.bookid = bookid;
        this.price = price;
        this.desc = desc;
    }

    public void setChunkId(int chunkId) {
        this.chunkId = chunkId;

    }

    public int getSpage() {
        return spage;
    }

    public void setSpage(int spage) {
        this.spage = spage;
    }

    public int getEpage() {
        return epage;
    }

    public void setEpage(int epage) {
        this.epage = epage;
    }

    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    private String topic;
    private int spage;
    private int epage;
    private int bookid;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    private int price;
    private String desc;
    private String path;
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getPrice() {
        return price;
    }


    public void setPrice(int price) {

        this.price = price;
    }

    public String getTopic() {
        return topic;
    }
    public int getStartPage() {
        return spage;
    }
    public int getEndPage() {
        return epage;
    }
    public int getBookId() {
        return bookid;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }


    public void setStartPage(int spage) {
        this.spage = spage;
    }
    public void setEndPage(int epage) {
        this.epage = epage;
    }
    public void setBookId(int bookid) {
        this.bookid = bookid;
    }

    @Override
    public String toString() {
        return "Login{" +
                "topic='" + topic + '\'' +
                ", spage='" + spage + '\'' +
                ", epage=" + epage+
                "bookid='" + bookid + '\''+
                ", desc='" + desc + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}

