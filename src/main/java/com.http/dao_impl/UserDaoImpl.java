package com.http.dao_impl;

import com.http.dao.UserDao;
import com.http.dto.UserDTO;
import com.http.model.MyConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {
    public static final String NAME_USER = "user";
    private final MyConnection connection = new MyConnection();

    @Override
    public UserDTO getObject(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    public List<UserDTO> getList(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    public List<UserDTO> findAll() throws SQLException {
        return null;
    }

    @Override
    public UserDTO findById(int id) throws SQLException {
        return null;
    }

    @Override
    public UserDTO insert(UserDTO userDTO) throws SQLException {
        return null;
    }

    @Override
    public List<UserDTO> search(UserDTO userDTO) throws SQLException {
        return null;
    }

    @Override
    public UserDTO update(UserDTO userDTO) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }
}
