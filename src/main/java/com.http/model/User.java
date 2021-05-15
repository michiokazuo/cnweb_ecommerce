package com.http.model;

import java.util.Date;

public class User extends Base {

    private Integer id;

    private String email;

    private String password;

    private String name;

    private String phone;

    private Role role;

    private String avatar;

    private String job;

    private String gender;

    private String homeTown;

    private String workplace;

    private Date birthday;

    public User() {
        super();
    }

    public User(Integer id, String email, String password, String name, String phone, Role role, String avatar,
                Boolean deleted, String job, String gender, String homeTown, String workplace, Date birthday,
                Date modifyDate, Date createDate, String createBy, String modifyBy) {
        super(modifyDate, createDate, createBy, modifyBy, deleted);
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.role = role;
        this.avatar = avatar;
        this.job = job;
        this.gender = gender;
        this.homeTown = homeTown;
        this.workplace = workplace;
        this.birthday = birthday;
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

    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatar() {
        return avatar;
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
                "job=" + job + '\'' +
                "gender=" + gender + '\'' +
                "homeTown=" + homeTown + '\'' +
                "workplace=" + workplace + '\'' +
                "birthday=" + birthday + '\'' +
                "deleted=" + getDeleted() + '\'' +
                "modifyDate=" + getModifyDate() + '\'' +
                "createDate=" + getCreateDate() + '\'' +
                "createBy=" + getCreateBy() + '\'' +
                "modifyBy=" + getModifyBy() + '\'' +
                '}';
    }
}
