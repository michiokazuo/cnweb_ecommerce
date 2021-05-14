package com.http.dao_impl;

import com.http.dao.BillHasProductDao;
import com.http.model.BillHasProduct;
import com.http.model.MyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BillHasProductDaoImpl implements BillHasProductDao {
    public static final String NAME_BILL_HAS_PRODUCT = "bill_has_product";
    private final MyConnection connection = new MyConnection();

    @Override
    public BillHasProduct getObject(ResultSet resultSet) throws SQLException {
        return new BillHasProduct(resultSet.getInt("product_id"), resultSet.getInt("bill_id"),
                resultSet.getInt("quantity"), resultSet.getDouble("product_price"));
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
    public BillHasProduct findById(int id) throws SQLException {
        return null;
    }

    @Override
    public BillHasProduct insert(BillHasProduct billHasProduct) throws SQLException {
        String sql = "INSERT INTO " + NAME_BILL_HAS_PRODUCT + " VALUES(?, ?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareUpdate(sql);
        preparedStatement.setInt(1, billHasProduct.getBillId());
        preparedStatement.setInt(2, billHasProduct.getProductId());
        preparedStatement.setInt(3, billHasProduct.getQuantity());
        preparedStatement.setDouble(4, billHasProduct.getProductPrice());

        int rs = preparedStatement.executeUpdate();

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

    @Override
    public void insertAll(List<BillHasProduct> billHasProducts) throws SQLException {
        for (BillHasProduct p : billHasProducts)
            insert(p);
    }

    @Override
    public List<BillHasProduct> getListByBill(Integer id_bill) throws SQLException {
        String sql = "SELECT * FROM " + NAME_BILL_HAS_PRODUCT + " WHERE bill_id = ?";

        PreparedStatement preparedStatement = connection.prepare(sql);
        preparedStatement.setInt(1, id_bill);
        ResultSet resultSet = preparedStatement.executeQuery();

        return getList(resultSet);
    }
}
