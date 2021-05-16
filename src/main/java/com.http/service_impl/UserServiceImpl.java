package com.http.service_impl;

import com.http.config.AppConfig;
import com.http.convert.Convert;
import com.http.convert.UserConvert;
import com.http.dao.UserDao;
import com.http.dao_impl.UserDaoImpl;
import com.http.dto.UserDTO;
import com.http.model.User;
import com.http.payload.LoginForm;
import com.http.service.UserService;
import com.http.utils.PasswordUtil;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao userDao = new UserDaoImpl();
    private final Convert<User, UserDTO> convert = new UserConvert();

    @Override
    public List<User> findAll() throws SQLException {
        if (AppConfig.checkAdmin(AppConfig.userInSysTem))
            return userDao.findAll();
        return null;
    }

    @Override
    public User findById(Integer id) throws Exception {
        return id != null && id > 0 ? userDao.findById(id) : null;
    }

    @Override
    public User insert(User user) throws Exception {
        if (user != null && !userDao.existsByEmailOrPhone(user.getEmail(), user.getPhone())) {
            Date date = new Date();
            user.setCreateDate(date);
            user.setModifyDate(date);
            user.setDeleted(false);
            user.setPhone(PasswordUtil.encode(user.getPassword()));

            return userDao.insert(user);
        }

        return null;
    }

    @Override
    public List<User> search(User user) throws Exception {
        if (AppConfig.checkAdmin(AppConfig.userInSysTem))
            return user != null ? userDao.search(user) : null;
        return null;
    }

    @Override
    public User update(User user) throws Exception {
        UserDTO userInSystem = AppConfig.userInSysTem;
        User updatedUser = null;
        if (user != null && userInSystem != null && (userInSystem.getId().equals(user.getId())
                || AppConfig.checkAdmin(userInSystem))) {
            User oldUser = userDao.findById(user.getId());
            List<User> userList = userDao.getListByEmailOrPhone(user.getEmail(), user.getPhone());
            if (oldUser == null || (userList != null && (userList.size() > 1
                    || (userList.size() == 1 && !userList.get(0).getId().equals(user.getId())))))
                return null;

            Date date = new Date();
            user.setModifyDate(date);
            user.setModifyBy(userInSystem.getEmail());
            user.setDeleted(false);
            user.setPhone(PasswordUtil.encode(user.getPassword()));

            updatedUser = userDao.update(user);
            if (!oldUser.getEmail().equals(updatedUser.getEmail()))
                userDao.updateCreateAndModifyBy(oldUser.getEmail(), updatedUser.getEmail());
        }

        return updatedUser;
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        UserDTO userInSystem = AppConfig.userInSysTem;
        return id != null && id > 0 && AppConfig.checkAdmin(userInSystem)
                && userDao.delete(id, userInSystem.getEmail(), new Date());
    }

    @Override
    public UserDTO login(LoginForm loginForm) throws Exception {
        if (loginForm != null) {
            return convert.toDTO(userDao.getUserByEmailAndPassword(loginForm));
        }

        return null;
    }
}
