package com.http.model;

import java.util.Date;

public class Bill {

    private Integer id;

    private Integer idUser;

    private String address;

    private Integer status;

    private Boolean deleted;

    private Date modifyDate;

    private Date createDate;

    private String createBy;

    private String modifyBy;

    public Bill() {
    }

    public Bill(Integer id, Integer idUser, String address, Integer status, Boolean deleted, Date modifyDate,
                Date createDate, String createBy, String modifyBy) {
        this.id = id;
        this.idUser = idUser;
        this.address = address;
        this.status = status;
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

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
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
        return "Bill{" +
                "id=" + id + '\'' +
                "idUser=" + idUser + '\'' +
                "address=" + address + '\'' +
                "status=" + status + '\'' +
                "deleted=" + deleted + '\'' +
                "modifyDate=" + modifyDate + '\'' +
                "createDate=" + createDate + '\'' +
                "createBy=" + createBy + '\'' +
                "modifyBy=" + modifyBy + '\'' +
                '}';
    }
}
