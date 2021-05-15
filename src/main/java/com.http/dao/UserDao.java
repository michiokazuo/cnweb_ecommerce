package com.http.dao;

import com.http.model.Role;
import com.http.model.User;
import com.http.payload.LoginForm;

import java.sql.SQLException;
import java.util.List;

public interface UserDao extends BaseDao<User> {

    User getUserByEmailAndPassword(LoginForm loginForm) throws SQLException;

    boolean existsByEmailOrPhone(String email, String phone) throws SQLException;

    List<User> getListByEmailOrPhone(String email, String phone) throws SQLException;

    List<Role> getRole() throws SQLException;
}
