package com.http.dao_impl;

import com.http.dao.CategoryDao;
import com.http.model.Category;
import com.http.model.MyConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    public static final String NAME_CATEGORY = "category";
    private final MyConnection connection = new MyConnection();

    @Override
    public Category getObject(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    public List<Category> getList(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    public List<Category> findAll() throws SQLException {
        return null;
    }

    @Override
    public Category findById(int id) throws SQLException {
        return null;
    }

    @Override
    public Category insert(Category category) throws SQLException {
        return null;
    }

    @Override
    public List<Category> search(Category category) throws SQLException {
        return null;
    }

    @Override
    public Category update(Category category) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }
}
