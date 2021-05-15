package com.http.model;

import com.http.dto.UserDTO;

import java.util.Date;

public class Bill extends Base {

    private Integer id;

    private UserDTO userDTO;

    private String address;

    private Integer status;

    public Bill() {
        super();
    }

    public Bill(Integer id, UserDTO userDTO, String address, Integer status, Boolean deleted, Date modifyDate,
                Date createDate, String createBy, String modifyBy) {
        super(modifyDate, createDate, createBy, modifyBy, deleted);
        this.id = id;
        this.userDTO = userDTO;
        this.address = address;
        this.status = status;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
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
                "userDTO=" + userDTO + '\'' +
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
