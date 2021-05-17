package com.http.dao_impl;

import com.http.config.AppConfig;
import com.http.dao.BillHasProductDao;
import com.http.dto.ProductDTO;
import com.http.model.BillHasProduct;
import com.http.model.MyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BillHasProductDaoImpl implements BillHasProductDao {
    private final MyConnection connection = new MyConnection();

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
        String sql = "INSERT INTO " + AppConfig.TABLE_BILL_HAS_PRODUCT
                + "(bill_id, product_id, quantity, product_price) VALUES(?, ?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareUpdate(sql);
        preparedStatement.setInt(1, billHasProduct.getBillId());
        preparedStatement.setInt(2, billHasProduct.getProductDTO().getId());
        preparedStatement.setInt(3, billHasProduct.getQuantity());
        preparedStatement.setDouble(4, billHasProduct.getProductPrice());

        preparedStatement.executeUpdate();

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
        boolean need_bill_has_product = false;
        boolean need_product = false;
        String sql = AppConfig.createSqlTwoTableSelect(
                AppConfig.TABLE_BILL_HAS_PRODUCT, "product_id", need_bill_has_product,
                AppConfig.TABLE_PRODUCT, "id", need_product)
                + ((need_bill_has_product || need_product) ? " AND " : " WHERE ")
                + AppConfig.TABLE_BILL_HAS_PRODUCT + ".bill_id = ?";

        PreparedStatement preparedStatement = connection.prepare(sql);
        preparedStatement.setInt(1, id_bill);
        ResultSet resultSet = preparedStatement.executeQuery();

        return getList(resultSet);
    }
}
