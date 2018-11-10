package com.dm.ebooks.model;

public class Cart {
    private int userId;
    private int chunkId;

    public int getUserId() {
        return userId;
    }

    public Cart() {
    }
   public Cart(int userId,int chunkId){
        this.userId=userId;
        this.chunkId=chunkId;
   }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getChunkId() {
        return chunkId;
    }

    public void setChunkId(int chunkId) {
        this.chunkId = chunkId;
    }
}
