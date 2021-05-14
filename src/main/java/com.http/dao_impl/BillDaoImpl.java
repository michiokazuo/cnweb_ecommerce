package com.http.dao_impl;

import com.http.dao.BillDao;
import com.http.model.Bill;
import com.http.model.MyConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BillDaoImpl implements BillDao {
    public static final String NAME_BILL = "bill";
    private final MyConnection connection = new MyConnection();

    @Override
    public Bill getObject(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    public List<Bill> getList(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    public List<Bill> findAll() throws SQLException {
        return null;
    }

    @Override
    public Bill findById(int id) throws SQLException {
        return null;
    }

    @Override
    public Bill insert(Bill bill) throws SQLException {
        return null;
    }

    @Override
    public List<Bill> search(Bill bill) throws SQLException {
        return null;
    }

    @Override
    public Bill update(Bill bill) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }
}
