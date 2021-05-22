package com.http.dao_impl;

import com.http.config.AppConfig;
import com.http.dao.BillDao;
import com.http.dto.UserDTO;
import com.http.model.Bill;
import com.http.service_database.ContentValues;
import com.http.service_database.DatabaseService;
import com.http.service_database.SQLBuilder;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BillDaoImpl implements BillDao {

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
        String table = new SQLBuilder.BuildTable(AppConfig.TABLE_BILL)
                .addTable(AppConfig.TABLE_USER, "id", AppConfig.TABLE_BILL, "id_user")
                .build();

        ResultSet resultSet = DatabaseService.getInstance().execQuery(table, null, "bill.deleted = false", null);

        return getList(resultSet);
    }

    @Override
    public Bill findById(Integer id) throws SQLException {
        Bill bill = null;
        String table = new SQLBuilder.BuildTable(AppConfig.TABLE_BILL)
                .addTable(AppConfig.TABLE_USER, "id", AppConfig.TABLE_BILL, "id_user")
                .build();

        String whereClause = "bill.deleted = false AND bill.id = ?";

        ResultSet resultSet = DatabaseService.getInstance().execQuery(table, null, whereClause, new Object[] {id});

        if (resultSet.first()) {
            bill = getObject(resultSet);
        }

        return bill;
    }

    @Override
    public Bill insert(Bill bill) throws SQLException {
        Bill new_bill = null;

        ContentValues contentValues = new ContentValues();

        contentValues.put("id_user", bill.getUserDTO().getId());
        contentValues.put("address", bill.getAddress());
        contentValues.put("status", bill.getStatus());
        contentValues.put("deleted", bill.getDeleted());
        contentValues.put("modify_date", new Date(bill.getModifyDate().getTime()));
        contentValues.put("create_date", new Date(bill.getCreateDate().getTime()));
        contentValues.put("create_by", bill.getCreateBy());
        contentValues.put("modify_by", bill.getModifyBy());

        PreparedStatement preparedStatement = DatabaseService.getInstance().getPreparedStatementInsert(AppConfig.TABLE_BILL, contentValues);
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
        ContentValues contentValues = new ContentValues();

        contentValues.put("deleted", true);
        contentValues.put("modify_date", new Date(modify.getTime()));
        contentValues.put("modify_by", email);

        int delete = DatabaseService.getInstance().update(AppConfig.TABLE_BILL, contentValues, "id = ?", new Object[] {id});

        return delete >= 0;
    }

    @Override
    public void updateCreateAndModifyBy(String oldEmail, String newEmail) throws SQLException {
        updateBy(oldEmail, newEmail, "create_by");
        updateBy(oldEmail, newEmail, "modify_by");
    }

    private void updateBy(String oldEmail, String newEmail, String column) throws SQLException {
        ContentValues contentValues = new ContentValues();
        contentValues.put(column, newEmail);

        DatabaseService.getInstance().update(AppConfig.TABLE_BILL, contentValues, column + " = ? ", new Object[] {oldEmail});
    }

    @Override
    public List<Bill> getAllBillByUser(Integer id) throws SQLException {
        String table = new SQLBuilder.BuildTable(AppConfig.TABLE_BILL)
                .addTable(AppConfig.TABLE_USER, "id", AppConfig.TABLE_BILL, "id_user")
                .build();
        String whereClause = "bill.deleted = false AND " + AppConfig.TABLE_USER + ".id = ?";

        ResultSet resultSet = DatabaseService.getInstance().execQuery(table, null, whereClause, new Object[] {id});

        return getList(resultSet);
    }

    @Override
    public boolean acceptBill(Integer id, String email, java.util.Date modify) throws SQLException {
        ContentValues contentValues = new ContentValues();

        contentValues.put("status", 1);
        contentValues.put("modify_date", new Date(modify.getTime()));
        contentValues.put("modify_by", email);

        int delete = DatabaseService.getInstance().update(AppConfig.TABLE_BILL, contentValues, "id = ? AND deleted = false", new Object[] {id});

        return delete >= 0;
    }

}
