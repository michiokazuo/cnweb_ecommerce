package com.http.service_impl;

import com.http.dao.CategoryDao;
import com.http.dao_impl.CategoryDaoImpl;
import com.http.dto.CategoryDTO;
import com.http.service.CategoryService;

import java.sql.SQLException;
import java.util.List;

public class CategoryService_Impl implements CategoryService {
    private final CategoryDao categoryDao = new CategoryDaoImpl();

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
