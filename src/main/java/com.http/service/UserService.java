package com.http.service;

import com.http.dto.UserDTO;
import com.http.model.User;
import com.http.payload.LoginForm;

public interface UserService extends BaseService<User> {

    UserDTO login(LoginForm loginForm) throws Exception;
}
