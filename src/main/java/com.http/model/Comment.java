package com.http.model;

import java.util.Date;

public class Comment {

    private Integer id;

    private String name;

    private String comment;

    private Double rate;

    private Integer productId;

    private Integer userId;

    private Boolean deleted;

    private Date modifyDate;

    private Date createDate;

    private String createBy;

    private String modifyBy;

    public Comment() {
    }

    public Comment(Integer id, String name, String comment, Double rate, Integer productId, Integer userId,
                   Boolean deleted, Date modifyDate, Date createDate, String createBy, String modifyBy) {
        this.id = id;
        this.name = name;
        this.comment = comment;
        this.rate = rate;
        this.productId = productId;
        this.userId = userId;
        this.deleted = deleted;
        this.modifyDate = modifyDate;
        this.createDate = createDate;
        this.createBy = createBy;
        this.modifyBy = modifyBy;
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

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getRate() {
        return rate;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Boolean isDeleted() {
        return deleted;
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

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id + '\'' +
                "name=" + name + '\'' +
                "comment=" + comment + '\'' +
                "rate=" + rate + '\'' +
                "productId=" + productId + '\'' +
                "userId=" + userId + '\'' +
                "deleted=" + deleted + '\'' +
                "modifyDate=" + modifyDate + '\'' +
                "createDate=" + createDate + '\'' +
                "createBy=" + createBy + '\'' +
                "modifyBy=" + modifyBy + '\'' +
                '}';
    }
}
