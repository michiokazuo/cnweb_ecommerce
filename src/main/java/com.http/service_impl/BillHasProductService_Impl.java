package com.http.service_impl;

import com.http.dao.BillHasProductDao;
import com.http.dao_impl.BillHasProductDaoImpl;
import com.http.model.BillHasProduct;
import com.http.service.BillHasProductService;

import java.sql.SQLException;
import java.util.List;

public class BillHasProductService_Impl implements BillHasProductService {
    private final BillHasProductDao billHasProductDao = new BillHasProductDaoImpl();

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
