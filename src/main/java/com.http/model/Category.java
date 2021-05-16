package com.http.model;

import java.util.Date;

public class Category extends Base {

    private Integer id;

    private String name;

    public Category() {
        super();
    }

    public Category(Integer id, String name, Date createDate) {
        this.id = id;
        this.name = name;
        this.setCreateDate(createDate);
    }

    public Category(Integer id, String name, Boolean deleted, Date modifyDate, Date createDate, String createBy,
                    String modifyBy) {
        super(modifyDate, createDate, createBy, modifyBy, deleted);
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id + '\'' +
                "name=" + name + '\'' +
                "deleted=" + getDeleted() + '\'' +
                "modifyDate=" + getModifyDate() + '\'' +
                "createDate=" + getCreateDate() + '\'' +
                "createBy=" + getCreateBy() + '\'' +
                "modifyBy=" + getModifyBy() + '\'' +
                '}';
    }
}
