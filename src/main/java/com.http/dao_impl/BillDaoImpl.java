package com.http.dao_impl;

import com.http.dao.BillDao;
import com.http.model.Bill;
import com.http.model.MyConnection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BillDaoImpl implements BillDao {
    public static final String NAME_BILL = "bill";
    private final MyConnection connection = new MyConnection();

    @Override
    public Bill getObject(ResultSet resultSet) throws SQLException {
        return new Bill(resultSet.getInt("id"), resultSet.getInt("id_user"),
                resultSet.getString("address"), resultSet.getInt("status"),
                resultSet.getBoolean("deleted"), resultSet.getDate("modify_date"),
                resultSet.getDate("create_date"), resultSet.getString("create_by"),
                resultSet.getString("modify_by"));
    }

    @Override
    public List<Bill> getList(ResultSet resultSet) throws SQLException {
        List<Bill> billList = new ArrayList<>();
        Bill bill = null;

        if (resultSet.first()) {
            do {
                bill = getObject(resultSet);
                if (bill != null) {
                    billList.add(bill);
                }
            } while (resultSet.next());
        }

        return billList.isEmpty() ? null : billList;
    }

    @Override
    public List<Bill> findAll() throws SQLException {
        String sql = "SELECT * FROM " + NAME_BILL + " WHERE deleted = false";

        PreparedStatement preparedStatement = connection.prepare(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        return getList(resultSet);
    }

    @Override
    public Bill findById(int id) throws SQLException {
        Bill bill = null;
        String sql = "SELECT * FROM " + NAME_BILL + " WHERE deleted = false AND id = ?";

        PreparedStatement preparedStatement = connection.prepare(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.first()) {
            bill = getObject(resultSet);
        }

        return bill;
    }

    @Override
    public Bill insert(Bill bill) throws SQLException {
        Bill new_bill = null;
        String sql = "INSERT INTO " + NAME_BILL + " (id_user, address, status, deleted, modify_date, create_date" +
                ", create_by, modify_by) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareUpdate(sql);
        preparedStatement.setInt(1, bill.getIdUser());
        preparedStatement.setString(2, bill.getAddress());
        preparedStatement.setInt(3, bill.getStatus());
        preparedStatement.setBoolean(4, bill.getDeleted());
        preparedStatement.setDate(5, new Date(bill.getModifyDate().getTime()));
        preparedStatement.setDate(6, new Date(bill.getCreateDate().getTime()));
        preparedStatement.setString(7, bill.getCreateBy());
        preparedStatement.setString(8, bill.getModifyBy());

        int rs = preparedStatement.executeUpdate();
        if (rs > 0) {
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.first()) {
                new_bill = findById((int) resultSet.getLong(1));
            }
        }
        return new_bill;
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
        String sql = "UPDATE " + NAME_BILL + " SET deleted = false WHERE id = ?";

        PreparedStatement preparedStatement = connection.prepareUpdate(sql);
        preparedStatement.setInt(1, id);

        int delete = preparedStatement.executeUpdate();

        return delete >= 0;
    }

    @Override
    public List<Bill> getAllBillByUser(Integer id) throws SQLException {
        Bill bill = null;
        String sql = "SELECT * FROM " + NAME_BILL + " WHERE deleted = false AND id_user = ?";

        PreparedStatement preparedStatement = connection.prepare(sql);
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        return getList(resultSet);
    }
}
