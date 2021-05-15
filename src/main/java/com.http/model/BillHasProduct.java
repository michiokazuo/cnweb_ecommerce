package com.http.model;

import com.http.dto.ProductDTO;

public class BillHasProduct {

    private ProductDTO productDTO;

    private Integer billId;

    private Integer quantity;

    private Double productPrice; // price product after counting with discount

    public BillHasProduct() {
    }

    public BillHasProduct(ProductDTO productDTO, Integer billId, Integer quantity, Double productPrice) {
        this.productDTO = productDTO;
        this.billId = billId;
        this.quantity = quantity;
        this.productPrice = productPrice;
    }

    public ProductDTO getProductDTO() {
        return productDTO;
    }

    public void setProductDTO(ProductDTO productDTO) {
        this.productDTO = productDTO;
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
                "productDTO=" + productDTO + '\'' +
                "billId=" + billId + '\'' +
                "quantity=" + quantity + '\'' +
                "productPrice=" + productPrice + '\'' +
                '}';
    }
}
