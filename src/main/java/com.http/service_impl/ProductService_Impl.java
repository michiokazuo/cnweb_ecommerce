package com.http.service_impl;

import com.http.dao.ProductDao;
import com.http.dao_impl.ProductDaoImpl;
import com.http.model.Product;
import com.http.service.ProductService;

import java.sql.SQLException;
import java.util.List;

public class ProductService_Impl implements ProductService {
    private final ProductDao productDao = new ProductDaoImpl();

    @Override
    public List<Product> findAll() throws SQLException {
        return null;
    }

    @Override
    public Product findById(int id) throws SQLException {
        return null;
    }

    @Override
    public Product insert(Product product) throws SQLException {
        return null;
    }

    @Override
    public List<Product> search(Product product) throws SQLException {
        return null;
    }

    @Override
    public Product update(Product product) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }
}
