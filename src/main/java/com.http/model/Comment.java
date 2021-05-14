package com.http.model;

import java.util.Date;

public class Comment extends Base{

    private Integer id;

    private String comment;

    private Double rate;

    private Integer productId;

    private Integer userId;

    public Comment() {
        super();
    }

    public Comment(Integer id, String comment, Double rate, Integer productId, Integer userId,
                   Boolean deleted, Date modifyDate, Date createDate, String createBy, String modifyBy) {
        super(modifyDate, createDate, createBy, modifyBy, deleted);
        this.id = id;
        this.comment = comment;
        this.rate = rate;
        this.productId = productId;
        this.userId = userId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
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

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id + '\'' +
                "comment=" + comment + '\'' +
                "rate=" + rate + '\'' +
                "productId=" + productId + '\'' +
                "userId=" + userId + '\'' +
                "deleted=" + getDeleted() + '\'' +
                "modifyDate=" + getModifyDate() + '\'' +
                "createDate=" + getCreateDate() + '\'' +
                "createBy=" + getCreateBy() + '\'' +
                "modifyBy=" + getModifyBy() + '\'' +
                '}';
    }
}
