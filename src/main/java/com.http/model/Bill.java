package com.http.model;

import java.util.Date;

public class Bill extends Base {

    private Integer id;

    private Integer idUser;

    private String address;

    private Integer status;

    public Bill() {
        super();
    }

    public Bill(Integer id, Integer idUser, String address, Integer status, Boolean deleted, Date modifyDate,
                Date createDate, String createBy, String modifyBy) {
        super(modifyDate, createDate, createBy, modifyBy, deleted);
        this.id = id;
        this.idUser = idUser;
        this.address = address;
        this.status = status;
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

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id + '\'' +
                "idUser=" + idUser + '\'' +
                "address=" + address + '\'' +
                "status=" + status + '\'' +
                "deleted=" + getDeleted() + '\'' +
                "modifyDate=" + getModifyDate() + '\'' +
                "createDate=" + getCreateDate() + '\'' +
                "createBy=" + getCreateBy() + '\'' +
                "modifyBy=" + getModifyBy() + '\'' +
                '}';
    }
}
