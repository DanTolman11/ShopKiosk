package com.Model.data;

public class Item {
    String stockName;
    String stockId;
    double stockPrice;
    int stockAmount;
    String item;

    public Item(String name, String id, double price, int amount) {
        this.stockName= name;
        this.stockId = id;
        this.stockPrice = price;
        this.stockAmount = amount;
    }

    public String getName() {
        return stockName;
    }

    public void setStockName(String name) {
        this.stockName = name;
    }

    public String getId() {
        return stockId;
    }

    public void setStockId(String id){
        this.stockId = id;
    }


    public double getPrice() {
        return stockPrice;
    }

    public void setPrice(double price) {
        this.stockPrice = price;
    }

    public int getAmount() {
        return stockAmount;
    }

    public void setAmount(int amount) {
        this.stockAmount = amount;
    }

    public String getItem(){
        return item;
    }

    public void setItem(String item){
        this.item = item;
    }

    //called by the gui to work out what to display
    @Override
    public String toString() {
        return String.format("%s, %.2f", stockName, stockPrice);
    }
}
