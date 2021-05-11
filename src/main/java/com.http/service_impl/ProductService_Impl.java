package com.http.service_impl;

import com.http.dao.ProductDao;
import com.http.dao_impl.ProductDaoImpl;
import com.http.dto.ProductDTO;
import com.http.service.ProductService;

import java.sql.SQLException;
import java.util.List;

public class ProductService_Impl implements ProductService {
    private final ProductDao productDao = new ProductDaoImpl();

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
