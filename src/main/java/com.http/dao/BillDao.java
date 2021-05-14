package com.http.dao;

import com.http.model.Bill;

import java.sql.SQLException;
import java.util.List;

public interface BillDao extends BaseDao<Bill> {

    List<Bill> getAllBillByUser(Integer id) throws SQLException;
}
