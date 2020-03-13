/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


public class Product {

    private String productId;
    private String productName;
    private double costPrice;
    private double sellPrice;
    private int quantity;
    
    
//    create table product (productId varchar(100),productName varchar(100),costPrice double,sellPrice double,quantity int)
    public String getProductId() {
        return productId;
    }
    public String getProductName() {
        return productName;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    

}
