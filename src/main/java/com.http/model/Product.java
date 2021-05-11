package com.http.model;

import java.util.Date;

public class Product {

    private Integer id;

    private String name;

    private Double price;

    private Integer discount;

    private Boolean deleted;

    private String image;

    private String introduction;

    private String CPU;

    private String display;

    private Integer memory;

    private Integer storage;

    private String GPU;

    private Double battery;

    private Double weight;

    private String operatingSystem;

    private Boolean soldOut;

    private Integer guarantee;

    private Integer categoryId;

    private Integer bought;

    private Date modifyDate;

    private Date createDate;

    private String createBy;

    private String modifyBy;

    private String others;

    public Product() {
    }

    public Product(Integer id, String name, Double price, Integer discount, Boolean deleted, String image,
                   String introduction, String CPU, String display, Integer memory, Integer storage, String GPU,
                   Double battery, Double weight, String operatingSystem, Boolean soldOut, Integer guarantee,
                   Integer categoryId, Integer bought, Date modifyDate, Date createDate, String createBy,
                   String modifyBy, String others) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.deleted = deleted;
        this.image = image;
        this.introduction = introduction;
        this.CPU = CPU;
        this.display = display;
        this.memory = memory;
        this.storage = storage;
        this.GPU = GPU;
        this.battery = battery;
        this.weight = weight;
        this.operatingSystem = operatingSystem;
        this.soldOut = soldOut;
        this.guarantee = guarantee;
        this.categoryId = categoryId;
        this.bought = bought;
        this.modifyDate = modifyDate;
        this.createDate = createDate;
        this.createBy = createBy;
        this.modifyBy = modifyBy;
        this.others = others;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setCPU(String CPU) {
        this.CPU = CPU;
    }

    public String getCPU() {
        return CPU;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getDisplay() {
        return display;
    }

    public void setMemory(Integer memory) {
        this.memory = memory;
    }

    public Integer getMemory() {
        return memory;
    }

    public void setStorage(Integer storage) {
        this.storage = storage;
    }

    public Integer getStorage() {
        return storage;
    }

    public void setGPU(String GPU) {
        this.GPU = GPU;
    }

    public String getGPU() {
        return GPU;
    }

    public void setBattery(Double battery) {
        this.battery = battery;
    }

    public Double getBattery() {
        return battery;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getWeight() {
        return weight;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setSoldOut(Boolean soldOut) {
        this.soldOut = soldOut;
    }

    public Boolean isSoldOut() {
        return soldOut;
    }

    public void setGuarantee(Integer guarantee) {
        this.guarantee = guarantee;
    }

    public Integer getGuarantee() {
        return guarantee;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setBought(Integer bought) {
        this.bought = bought;
    }

    public Integer getBought() {
        return bought;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public String getOthers() {
        return others;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id + '\'' +
                "name=" + name + '\'' +
                "price=" + price + '\'' +
                "discount=" + discount + '\'' +
                "deleted=" + deleted + '\'' +
                "image=" + image + '\'' +
                "introduction=" + introduction + '\'' +
                "CPU=" + CPU + '\'' +
                "display=" + display + '\'' +
                "memory=" + memory + '\'' +
                "storage=" + storage + '\'' +
                "GPU=" + GPU + '\'' +
                "battery=" + battery + '\'' +
                "weight=" + weight + '\'' +
                "operatingSystem=" + operatingSystem + '\'' +
                "soldOut=" + soldOut + '\'' +
                "guarantee=" + guarantee + '\'' +
                "categoryId=" + categoryId + '\'' +
                "bought=" + bought + '\'' +
                "modifyDate=" + modifyDate + '\'' +
                "createDate=" + createDate + '\'' +
                "createBy=" + createBy + '\'' +
                "modifyBy=" + modifyBy + '\'' +
                "others=" + others + '\'' +
                '}';
    }
}
