package com.http.dao_impl;

import com.http.dao.ProductDao;
import com.http.dto.ProductDTO;
import com.http.model.MyConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    public static final String NAME_PRODUCT = "product";
    private final MyConnection connection = new MyConnection();

    @Override
    public ProductDTO getObject(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    public List<ProductDTO> getList(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    public List<ProductDTO> findAll() throws SQLException {
        return null;
    }

    @Override
    public ProductDTO findById(int id) throws SQLException {
        return null;
    }

    @Override
    public ProductDTO insert(ProductDTO productDTO) throws SQLException {
        return null;
    }

    @Override
    public List<ProductDTO> search(ProductDTO productDTO) throws SQLException {
        return null;
    }

    @Override
    public ProductDTO update(ProductDTO productDTO) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }
}
