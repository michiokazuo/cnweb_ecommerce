package com.http.dao_impl;

import com.http.config.AppConfig;
import com.http.dao.CategoryDao;
import com.http.model.Category;
import com.http.model.MyConnection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    private final MyConnection connection = new MyConnection();

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
        String sql = "SELECT * FROM " + AppConfig.TABLE_CATEGORY + " WHERE deleted = false";

        PreparedStatement preparedStatement = connection.prepare(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        return getList(resultSet);
    }

    @Override
    public Category findById(int id) throws SQLException {
        Category category = null;
        String sql = "SELECT * FROM " + AppConfig.TABLE_CATEGORY + " WHERE deleted = false AND id = ?";

        PreparedStatement preparedStatement = connection.prepare(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.first()) {
            category = getObject(resultSet);
        }

        return category;
    }

    @Override
    public Category insert(Category category) throws SQLException {
        Category new_category = null;
        String sql = "INSERT INTO " + AppConfig.TABLE_CATEGORY + " (name, deleted, modify_date, create_date, create_by"
                + ", modify_by) VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareUpdate(sql);
        preparedStatement.setString(1, category.getName());
        preparedStatement.setBoolean(2, category.getDeleted());
        preparedStatement.setDate(3, new Date(category.getModifyDate().getTime()));
        preparedStatement.setDate(4, new Date(category.getCreateDate().getTime()));
        preparedStatement.setString(5, category.getCreateBy());
        preparedStatement.setString(6, category.getModifyBy());

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
        String sql = "SELECT * FROM " + AppConfig.TABLE_CATEGORY + " WHERE deleted = false AND ? IS NULL OR name LIKE ?"
                + " AND (? IS NULL OR DATE(" + AppConfig.TABLE_CATEGORY + ".create_date) >= ?)"
                + " AND (? IS NULL OR DATE(" + AppConfig.TABLE_CATEGORY + ".create_date) <= ?) ";
        ;

        PreparedStatement preparedStatement = connection.prepare(sql);
        preparedStatement.setString(1, category.getName());
        preparedStatement.setString(2, "%" + category.getName() + "%");
        preparedStatement.setDate(3, category.getCreateDate() == null ? null
                : new Date(category.getCreateDate().getTime()));
        preparedStatement.setDate(4, category.getCreateDate() == null ? null
                : new Date(category.getCreateDate().getTime()));
        preparedStatement.setDate(5, category.getCreateDate() == null ? null
                : new Date(category.getCreateDate().getTime()));
        preparedStatement.setDate(6, category.getCreateDate() == null ? null
                : new Date(category.getCreateDate().getTime()));

        ResultSet resultSet = preparedStatement.executeQuery();

        return getList(resultSet);
    }

    @Override
    public Category update(Category category) throws SQLException {
        Category update_category = null;
        String sql = "UPDATE " + AppConfig.TABLE_CATEGORY + " SET name = ?, modify_date = ?, modify_by = ? WHERE id = ?";

        PreparedStatement preparedStatement = connection.prepareUpdate(sql);
        preparedStatement.setString(1, category.getName());
        preparedStatement.setDate(2, new Date(category.getModifyDate().getTime()));
        preparedStatement.setString(3, category.getModifyBy());
        preparedStatement.setInt(4, category.getId());

        int update = preparedStatement.executeUpdate();
        if (update > 0) {
            update_category = findById(category.getId());
        }

        return update_category;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        String sql = "UPDATE " + AppConfig.TABLE_CATEGORY + " SET deleted = true WHERE id = ?";

        PreparedStatement preparedStatement = connection.prepareUpdate(sql);
        preparedStatement.setInt(1, id);

        int delete = preparedStatement.executeUpdate();
        return delete >= 0;
    }
}
