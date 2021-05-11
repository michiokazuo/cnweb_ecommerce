package com.http.dao_impl;

import com.http.dao.CategoryDao;
import com.http.dto.CategoryDTO;
import com.http.model.MyConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    public static final String NAME_CATEGORY = "category";
    private final MyConnection connection = new MyConnection();

    @Override
    public CategoryDTO getObject(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    public List<CategoryDTO> getList(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    public List<CategoryDTO> findAll() throws SQLException {
        return null;
    }

    @Override
    public CategoryDTO findById(int id) throws SQLException {
        return null;
    }

    @Override
    public CategoryDTO insert(CategoryDTO categoryDTO) throws SQLException {
        return null;
    }

    @Override
    public List<CategoryDTO> search(CategoryDTO categoryDTO) throws SQLException {
        return null;
    }

    @Override
    public CategoryDTO update(CategoryDTO categoryDTO) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }
}
