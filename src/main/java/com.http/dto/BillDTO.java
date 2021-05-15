package com.http.dto;

import com.http.model.Bill;

public class BillDTO {
    Bill bill;

    private Double sumPrice;

    private Integer numbersOfProduct;

    public BillDTO() {
    }

    public BillDTO(Bill bill, Double sumPrice, Integer numbersOfProduct) {
        this.bill = bill;
        this.sumPrice = sumPrice;
        this.numbersOfProduct = numbersOfProduct;
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

    @Override
    public String toString() {
        return "BillDTO{" +
                "bill=" + bill +
                ", sumPrice=" + sumPrice +
                ", numbersOfProduct=" + numbersOfProduct +
                '}';
    }
}
