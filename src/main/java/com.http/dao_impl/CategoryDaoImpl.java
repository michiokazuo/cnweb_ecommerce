package com.http.dao_impl;

import com.http.config.AppConfig;
import com.http.dao.CategoryDao;
import com.http.model.Category;
import com.http.service_database.ContentValues;
import com.http.service_database.DatabaseService;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {

    @Override
    public Category getObject(ResultSet resultSet) throws SQLException {
        return new Category(resultSet.getInt("id"), resultSet.getString("name"),
                resultSet.getBoolean("deleted"), resultSet.getDate("modify_date"),
                resultSet.getDate("create_date"), resultSet.getString("create_by"),
                resultSet.getString("modify_by"));
    }

    @Override
    public List<Category> getList(ResultSet resultSet) throws SQLException {
        List<Category> categoryList = new ArrayList<>();
        Category category = null;

        if (resultSet.first()) { // tro den ban ghi dau tien -- neu co tra ve true -- khong tra ve false
            do {
                category = getObject(resultSet);
                if (category != null) {
                    categoryList.add(category);
                }
            } while (resultSet.next());
        }

        return categoryList.isEmpty() ? null : categoryList;
    }

    @Override
    public List<Category> findAll() throws SQLException {
        ResultSet resultSet = DatabaseService.getInstance().execQuery(AppConfig.TABLE_CATEGORY, null, "deleted = false", null);

        return getList(resultSet);
    }

    @Override
    public Category findById(Integer id) throws SQLException {
        Category category = null;

        String whereClause = "deleted = false AND id = ?";
        ResultSet resultSet = DatabaseService.getInstance().execQuery(AppConfig.TABLE_CATEGORY, null, whereClause, new Object[] {id});

        if (resultSet.first()) {
            category = getObject(resultSet);
        }

        return category;
    }

    @Override
    public Category insert(Category category) throws SQLException {
        Category new_category = null;

        ContentValues contentValues = new ContentValues();

        contentValues.put("name", category.getName());
        contentValues.put("deleted", category.getDeleted());
        contentValues.put("modify_date", new Date(category.getModifyDate().getTime()));
        contentValues.put("create_date", new Date(category.getCreateDate().getTime()));
        contentValues.put("create_by", category.getCreateBy());
        contentValues.put("modify_by", category.getModifyBy());
        PreparedStatement preparedStatement = DatabaseService.getInstance().getPreparedStatementInsert(AppConfig.TABLE_CATEGORY, contentValues);
        int rs = preparedStatement.executeUpdate();
        if (rs > 0) {
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.first()) {
                new_category = findById((int) resultSet.getLong(1));
            }
        }

        return new_category;
    }

    @Override
    public List<Category> search(Category category) throws SQLException {

        String whereClause = "deleted = false "
                + " AND (? IS NULL OR name LIKE ?) "
                + " AND (? IS NULL OR DATE(" + AppConfig.TABLE_CATEGORY + ".create_date) >= ?) "
                + " AND (? IS NULL OR DATE(" + AppConfig.TABLE_CATEGORY + ".create_date) <= ?) ";

        Object[] whereArgs = new Object[] {
                category.getName(),
                "%" + category.getName() + "%",
                category.getCreateDate() == null ? null : new Date(category.getCreateDate().getTime()),
                category.getCreateDate() == null ? null : new Date(category.getCreateDate().getTime()),
                category.getCreateDate() == null ? null : new Date(category.getCreateDate().getTime()),
                category.getCreateDate() == null ? null : new Date(category.getCreateDate().getTime())
        };

        ResultSet resultSet = DatabaseService.getInstance().execQuery(AppConfig.TABLE_CATEGORY, null, whereClause, whereArgs);

        return getList(resultSet);
    }

    @Override
    public Category update(Category category) throws SQLException {
        Category update_category = null;

        ContentValues contentValues = new ContentValues();

        contentValues.put("name", category.getName());
        contentValues.put("modify_date", new Date(category.getModifyDate().getTime()));
        contentValues.put("modify_by", category.getModifyBy());

        int update = DatabaseService.getInstance().update(AppConfig.TABLE_CATEGORY, contentValues, "id = ?", new Object[] {category.getId()});
        if (update > 0) {
            update_category = findById(category.getId());
        }

        return update_category;
    }

    @Override
    public boolean delete(Integer id, String email, java.util.Date modify) throws SQLException {
        ContentValues contentValues = new ContentValues();

        contentValues.put("deleted", true);
        contentValues.put("modify_date", new Date(modify.getTime()));
        contentValues.put("modify_by", email);

        int delete = DatabaseService.getInstance().update(AppConfig.TABLE_CATEGORY, contentValues, "id = ?", new Object[] {id});

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

        DatabaseService.getInstance().update(AppConfig.TABLE_CATEGORY, contentValues, column + " = ? ", new Object[] {oldEmail});
    }
}
