package com.http.dto;

import com.http.model.Bill;
import com.http.model.BillHasProduct;

import java.util.List;

public class BillDTO {
    Bill bill;

    private Double sumPrice;

    private Integer numbersOfProduct;

    List<BillHasProduct> billHasProducts;

    public BillDTO() {
    }

    public BillDTO(Bill bill, Double sumPrice, Integer numbersOfProduct, List<BillHasProduct> billHasProducts) {
        this.bill = bill;
        this.sumPrice = sumPrice;
        this.numbersOfProduct = numbersOfProduct;
        this.billHasProducts = billHasProducts;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public Double getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(Double sumPrice) {
        this.sumPrice = sumPrice;
    }

    public Integer getNumbersOfProduct() {
        return numbersOfProduct;
    }

    public void setNumbersOfProduct(Integer numbersOfProduct) {
        this.numbersOfProduct = numbersOfProduct;
    }

    public List<BillHasProduct> getBillHasProducts() {
        return billHasProducts;
    }

    public void setBillHasProducts(List<BillHasProduct> billHasProducts) {
        this.billHasProducts = billHasProducts;
    }

    @Override
    public String toString() {
        return "BillDTO{" +
                "bill=" + bill +
                ", sumPrice=" + sumPrice +
                ", numbersOfProduct=" + numbersOfProduct +
                ", billHasProducts=" + billHasProducts +
                '}';
    }
}
