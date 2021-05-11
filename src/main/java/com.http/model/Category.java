package com.http.model;


import java.util.Date;

public class Category {

    private Integer id;

    private String name;

    private Boolean deleted;

    private Date modifyDate;

    private Date createDate;

    private String createBy;

    private String modifyBy;

    public Category() {
    }

    public Category(Integer id, String name, Boolean deleted, Date modifyDate, Date createDate, String createBy, String modifyBy) {
        this.id = id;
        this.name = name;
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
        return "Category{" +
                "id=" + id + '\'' +
                "name=" + name + '\'' +
                "deleted=" + deleted + '\'' +
                "modifyDate=" + modifyDate + '\'' +
                "createDate=" + createDate + '\'' +
                "createBy=" + createBy + '\'' +
                "modifyBy=" + modifyBy + '\'' +
                '}';
    }
}
