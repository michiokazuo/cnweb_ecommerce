package com.http.dto;

import java.util.Date;

public class CategoryDTO {
    private Integer id;

    private String name;

    private Date createDate;

    public CategoryDTO() {
    }

    public CategoryDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryDTO(Integer id, String name, Date createDate) {
        this.id = id;
        this.name = name;
        this.createDate = createDate;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "CategoryDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
