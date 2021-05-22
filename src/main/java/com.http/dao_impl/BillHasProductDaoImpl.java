package com.http.dao_impl;

import com.http.config.AppConfig;
import com.http.dao.BillHasProductDao;
import com.http.dto.ProductDTO;
import com.http.model.BillHasProduct;

import com.http.service_database.ContentValues;
import com.http.service_database.DatabaseService;
import com.http.service_database.SQLBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BillHasProductDaoImpl implements BillHasProductDao {

    @Override
    public BillHasProduct getObject(ResultSet resultSet) throws SQLException {
        return new BillHasProduct(
                new ProductDTO(resultSet.getInt(AppConfig.TABLE_PRODUCT + ".id"),
                        resultSet.getString(AppConfig.TABLE_PRODUCT + ".name"),
                        resultSet.getString(AppConfig.TABLE_PRODUCT + ".image"),
                        resultSet.getBoolean(AppConfig.TABLE_PRODUCT + ".deleted")),
                resultSet.getInt(AppConfig.TABLE_BILL_HAS_PRODUCT + ".bill_id"),
                resultSet.getInt(AppConfig.TABLE_BILL_HAS_PRODUCT + ".quantity"),
                resultSet.getDouble(AppConfig.TABLE_BILL_HAS_PRODUCT + ".product_price"));
    }

    @Override
    public List<BillHasProduct> getList(ResultSet resultSet) throws SQLException {
        List<BillHasProduct> billHasProducts = new ArrayList<>();
        BillHasProduct billHasProduct = null;

        if (resultSet.first()) {
            do {
                billHasProduct = getObject(resultSet);
                if (billHasProduct != null) {
                    billHasProducts.add(billHasProduct);
                }
            } while (resultSet.next());
        }

        return billHasProducts.isEmpty() ? null : billHasProducts;
    }

    @Override
    public List<BillHasProduct> findAll() throws SQLException {
        return null;
    }

    @Override
    public BillHasProduct findById(Integer id) throws SQLException {
        return null;
    }

    @Override
    public BillHasProduct insert(BillHasProduct billHasProduct) throws SQLException {
        ContentValues contentValues = new ContentValues();

        contentValues.put("bill_id", billHasProduct.getBillId());
        contentValues.put("product_id", billHasProduct.getProductDTO().getId());
        contentValues.put("quantity", billHasProduct.getQuantity());
        contentValues.put("product_price", billHasProduct.getProductPrice());

        DatabaseService.getInstance().insert(AppConfig.TABLE_BILL_HAS_PRODUCT, contentValues);
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
    public boolean delete(Integer id, String email, Date modify) throws SQLException {
        return false;
    }

    @Override
    public void updateCreateAndModifyBy(String oldEmail, String newEmail) throws SQLException {
    }

    @Override
    public void insertAll(List<BillHasProduct> billHasProducts) throws SQLException {
        for (BillHasProduct p : billHasProducts)
            insert(p);
    }

    @Override
    public List<BillHasProduct> getListByBill(Integer id_bill) throws SQLException {
        String table = new SQLBuilder.BuildTable(AppConfig.TABLE_BILL_HAS_PRODUCT)
                .addTable(AppConfig.TABLE_PRODUCT, "id", AppConfig.TABLE_BILL_HAS_PRODUCT, "product_id")
                .build();
        String whereClause = AppConfig.TABLE_BILL_HAS_PRODUCT + ".bill_id = ?";

        ResultSet resultSet = DatabaseService.getInstance().execQuery(table, null, whereClause, new Object[] {id_bill});

        return getList(resultSet);
    }
}
