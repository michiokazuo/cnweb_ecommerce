package com.http.dao;

import com.http.model.BillHasProduct;

import java.sql.SQLException;
import java.util.List;

public interface BillHasProductDao extends BaseDao<BillHasProduct> {

    void insertAll(List<BillHasProduct> billHasProducts) throws SQLException;

    List<BillHasProduct> getListByBill(Integer id_bill) throws SQLException;
}
