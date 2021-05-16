package com.http.dto;

public class ProductDTO {
    private Integer id;

    private String name;

    private Double price;

    private Integer discount;

    private String image;

    private Boolean deleted;

    private Integer numberOfRate;

    private Double rate;

    private Integer numberOfCMT;

    public ProductDTO() {
    }

    public ProductDTO(Integer numberOfRate, Double rate) {
        this.numberOfRate = numberOfRate;
        this.rate = rate;
    }

    public ProductDTO(Integer id, String name, String image, Boolean deleted) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.deleted = deleted;
    }

    public ProductDTO(Integer id, String name, Double price, Integer discount, String image, Boolean deleted) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.image = image;
        this.deleted = deleted;
    }

    public ProductDTO(Integer id, String name, Double price, Integer discount, String image, Integer numberOfRate,
                      Double rate, Integer numberOfCMT, Boolean deleted) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.image = image;
        this.numberOfRate = numberOfRate;
        this.rate = rate;
        this.numberOfCMT = numberOfCMT;
        this.deleted = deleted;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getNumberOfRate() {
        return numberOfRate;
    }

    public void setNumberOfRate(Integer numberOfRate) {
        this.numberOfRate = numberOfRate;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Integer getNumberOfCMT() {
        return numberOfCMT;
    }

    public void setNumberOfCMT(Integer numberOfCMT) {
        this.numberOfCMT = numberOfCMT;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", image='" + image + '\'' +
                ", deleted=" + deleted +
                ", numberOfRate=" + numberOfRate +
                ", rate=" + rate +
                ", numberOfCMT=" + numberOfCMT +
                '}';
    }
}
