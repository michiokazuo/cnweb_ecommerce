package com.http.model;

import java.util.Date;

public abstract class Base {
    private Date modifyDate;

    private Date createDate;

    private String createBy;

    private String modifyBy;

    private Boolean deleted;

    public Base() {
    }

    public Base(Date modifyDate, Date createDate, String createBy, String modifyBy, Boolean deleted) {
        this.modifyDate = modifyDate;
        this.createDate = createDate;
        this.createBy = createBy;
        this.modifyBy = modifyBy;
        this.deleted = deleted;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Base{" +
                "modifyDate=" + modifyDate +
                ", createDate=" + createDate +
                ", createBy='" + createBy + '\'' +
                ", modifyBy='" + modifyBy + '\'' +
                ", deleted=" + deleted +
                '}';
    }
}
