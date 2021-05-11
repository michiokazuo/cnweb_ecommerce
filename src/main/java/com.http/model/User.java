package com.http.model;

import java.util.Date;

public class User {

    private Integer id;

    private String email;

    private String password;

    private String name;

    private String phone;

    private Integer role;

    private String avatar;

    private Boolean deleted;

    private String job;

    private String gender;

    private String homeTown;

    private String workplace;

    private Date birthday;

    private Date modifyDate;

    private Date createDate;

    private String createBy;

    private String modifyBy;

    public User() {
    }

    public User(Integer id, String email, String password, String name, String phone, Integer role, String avatar,
                Boolean deleted, String job, String gender, String homeTown, String workplace, Date birthday,
                Date modifyDate, Date createDate, String createBy, String modifyBy) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.role = role;
        this.avatar = avatar;
        this.deleted = deleted;
        this.job = job;
        this.gender = gender;
        this.homeTown = homeTown;
        this.workplace = workplace;
        this.birthday = birthday;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getRole() {
        return role;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getJob() {
        return job;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }

    public String getHomeTown() {
        return homeTown;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getBirthday() {
        return birthday;
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
        return "User{" +
                "id=" + id + '\'' +
                "email=" + email + '\'' +
                "password=" + password + '\'' +
                "name=" + name + '\'' +
                "phone=" + phone + '\'' +
                "role=" + role + '\'' +
                "avatar=" + avatar + '\'' +
                "deleted=" + deleted + '\'' +
                "job=" + job + '\'' +
                "gender=" + gender + '\'' +
                "homeTown=" + homeTown + '\'' +
                "workplace=" + workplace + '\'' +
                "birthday=" + birthday + '\'' +
                "modifyDate=" + modifyDate + '\'' +
                "createDate=" + createDate + '\'' +
                "createBy=" + createBy + '\'' +
                "modifyBy=" + modifyBy + '\'' +
                '}';
    }
}
