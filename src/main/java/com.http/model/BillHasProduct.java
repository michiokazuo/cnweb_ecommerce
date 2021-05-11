package com.http.model;

public class BillHasProduct {

    private Integer productId;

    private Integer billId;

    private Integer quantity;

    private Double productPrice;

    public BillHasProduct() {
    }

    public BillHasProduct(Integer productId, Integer billId, Integer quantity, Double productPrice) {
        this.productId = productId;
        this.billId = billId;
        this.quantity = quantity;
        this.productPrice = productPrice;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public Integer getBillId() {
        return billId;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    @Override
    public String toString() {
        return "BillHasProduct{" +
                "productId=" + productId + '\'' +
                "billId=" + billId + '\'' +
                "quantity=" + quantity + '\'' +
                "productPrice=" + productPrice + '\'' +
                '}';
    }
}
