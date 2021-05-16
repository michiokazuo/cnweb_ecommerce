package com.http.dto;

import com.http.model.Role;

public class UserDTO {
    private Integer id;

    private String name;

    private String avatar;

    private String email;

    private Role role;

    private Boolean deleted;

    private Integer numberOfBill;

    public UserDTO() {
    }

    public UserDTO(Integer id, String name, String avatar, String email, Boolean deleted, Integer numberOfBill) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.email = email;
        this.deleted = deleted;
        this.numberOfBill = numberOfBill;
    }

    public UserDTO(Integer id, String name, String avatar, Boolean deleted) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.deleted = deleted;
    }

    public UserDTO(Integer id, String name, String avatar, String email, Boolean deleted) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.email = email;
        this.deleted = deleted;
    }

    public UserDTO(Integer id, String name, String avatar, String email, Role role, Boolean deleted) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.email = email;
        this.role = role;
        this.deleted = deleted;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getNumberOfBill() {
        return numberOfBill;
    }

    public void setNumberOfBill(Integer numberOfBill) {
        this.numberOfBill = numberOfBill;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", deleted=" + deleted +
                '}';
    }
}
