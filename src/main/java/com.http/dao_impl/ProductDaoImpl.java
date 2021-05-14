package com.http.dao_impl;

import com.http.dao.ProductDao;
import com.http.model.MyConnection;
import com.http.model.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    public static final String NAME_PRODUCT = "product";
    private final MyConnection connection = new MyConnection();

    @Override
    public Product getObject(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    public List<Product> getList(ResultSet resultSet) throws SQLException {
        return null;
    }

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
