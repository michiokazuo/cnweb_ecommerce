package com.http.dao_impl;

import com.http.config.AppConfig;
import com.http.dao.BillDao;
import com.http.dto.UserDTO;
import com.http.model.Bill;
import com.http.model.MyConnection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BillDaoImpl implements BillDao {
    private final MyConnection connection = new MyConnection();

    @Override
    public Bill getObject(ResultSet resultSet) throws SQLException {
        return new Bill(resultSet.getInt(AppConfig.TABLE_BILL + ".id"),
                new UserDTO(resultSet.getInt(AppConfig.TABLE_USER + ".id"),
                        resultSet.getString(AppConfig.TABLE_USER + ".name"),
                        resultSet.getString(AppConfig.TABLE_USER + ".avatar"),
                        resultSet.getString(AppConfig.TABLE_USER + ".email"),
                        resultSet.getBoolean(AppConfig.TABLE_USER + ".deleted")),
                resultSet.getString(AppConfig.TABLE_BILL + ".address"),
                resultSet.getInt(AppConfig.TABLE_BILL + ".status"),
                resultSet.getBoolean(AppConfig.TABLE_BILL + ".deleted"),
                resultSet.getDate(AppConfig.TABLE_BILL + ".modify_date"),
                resultSet.getDate(AppConfig.TABLE_BILL + ".create_date"),
                resultSet.getString(AppConfig.TABLE_BILL + ".create_by"),
                resultSet.getString(AppConfig.TABLE_BILL + ".modify_by"));
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
        String sql = AppConfig
                .createSqlTwoTableSelect(AppConfig.TABLE_BILL, "id_user", true,
                        AppConfig.TABLE_USER, "id", false);

        PreparedStatement preparedStatement = connection.prepare(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        return getList(resultSet);
    }

    @Override
    public Bill findById(Integer id) throws SQLException {
        boolean need_bill = true;
        boolean need_user = false;
        Bill bill = null;
        String sql = AppConfig
                .createSqlTwoTableSelect(AppConfig.TABLE_BILL, "id_user", need_bill,
                        AppConfig.TABLE_USER, "id", need_user)
                + ((need_user || need_bill) ? " AND " : " WHERE ") + AppConfig.TABLE_BILL + ".id = ?";

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
        String sql = "INSERT INTO " + AppConfig.TABLE_BILL + " (id_user, address, status, deleted, modify_date" +
                ", create_date, create_by, modify_by) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareUpdate(sql);
        preparedStatement.setInt(1, bill.getUserDTO().getId());
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
    public boolean delete(Integer id, String email, java.util.Date modify) throws SQLException {
        String sql = "UPDATE " + AppConfig.TABLE_BILL
                + " SET deleted = true, modify_date = ?, modify_by = ? WHERE id = ?";

        PreparedStatement preparedStatement = connection.prepareUpdate(sql);
        preparedStatement.setDate(1, new Date(modify.getTime()));
        preparedStatement.setInt(3, id);
        preparedStatement.setString(2, email);

        int delete = preparedStatement.executeUpdate();

        return delete >= 0;
    }

    @Override
    public void updateCreateAndModifyBy(String oldEmail, String newEmail) throws SQLException {
        updateBy(oldEmail, newEmail, "create_by");
        updateBy(oldEmail, newEmail, "modify_by");
    }

    private void updateBy(String oldEmail, String newEmail, String column) throws SQLException {
        String sql = "UPDATE " + AppConfig.TABLE_BILL + " SET " + column + " = ?  WHERE " + column + " = ?";

        PreparedStatement preparedStatement = connection.prepareUpdate(sql);
        preparedStatement.setString(1, newEmail);
        preparedStatement.setString(2, oldEmail);

        preparedStatement.executeUpdate();
    }

    @Override
    public List<Bill> getAllBillByUser(Integer id) throws SQLException {
        boolean need_bill = true;
        boolean need_user = false;
        String sql = AppConfig
                .createSqlTwoTableSelect(AppConfig.TABLE_BILL, "id_user", need_bill,
                        AppConfig.TABLE_USER, "id", need_user)
                + ((need_bill || need_user) ? " AND " : " WHERE ") + AppConfig.TABLE_USER + ".id = ?";

        PreparedStatement preparedStatement = connection.prepare(sql);
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        return getList(resultSet);
    }

    @Override
    public boolean acceptBill(Integer id, String email, java.util.Date modify) throws SQLException {
        String sql = "UPDATE " + AppConfig.TABLE_BILL
                + " SET status = 1, modify_date = ?, modify_by = ? WHERE id = ? AND deleted = false";

        PreparedStatement preparedStatement = connection.prepareUpdate(sql);
        preparedStatement.setDate(1, new Date(modify.getTime()));
        preparedStatement.setInt(3, id);
        preparedStatement.setString(2, email);

        int delete = preparedStatement.executeUpdate();

        return delete >= 0;
    }

}
