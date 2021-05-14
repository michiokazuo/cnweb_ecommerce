package com.http.dao_impl;

import com.http.dao.BillHasProductDao;
import com.http.model.BillHasProduct;
import com.http.model.MyConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BillHasProductDaoImpl implements BillHasProductDao {
    public static final String NAME_BILL_HAS_PRODUCT = "bill_has_product";
    private final MyConnection connection = new MyConnection();

    @Override
    public BillHasProduct getObject(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    public List<BillHasProduct> getList(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    public List<BillHasProduct> findAll() throws SQLException {
        return null;
    }

    @Override
    public BillHasProduct findById(int id) throws SQLException {
        return null;
    }

    @Override
    public BillHasProduct insert(BillHasProduct billHasProduct) throws SQLException {
        return null;
    }

    @Override
    public List<BillHasProduct> search(BillHasProduct billHasProduct) throws SQLException {
        return null;
    }

    @Override
    public BillHasProduct update(BillHasProduct billHasProduct) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }
}
