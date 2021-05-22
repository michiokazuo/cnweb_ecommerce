package com.http.dao_impl;

import com.http.config.AppConfig;
import com.http.dao.ProductDao;
import com.http.dto.CategoryDTO;
import com.http.model.Product;
import com.http.service_database.ContentValues;
import com.http.service_database.DatabaseService;
import com.http.service_database.SQLBuilder;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    @Override
    public Product getObject(ResultSet resultSet) throws SQLException {
        return new Product(resultSet.getInt(AppConfig.TABLE_PRODUCT + ".id"),
                resultSet.getString(AppConfig.TABLE_PRODUCT + ".name"),
                resultSet.getDouble(AppConfig.TABLE_PRODUCT + ".price"),
                resultSet.getInt(AppConfig.TABLE_PRODUCT + ".discount"),
                resultSet.getBoolean(AppConfig.TABLE_PRODUCT + ".deleted"),
                resultSet.getString(AppConfig.TABLE_PRODUCT + ".image"),
                resultSet.getString(AppConfig.TABLE_PRODUCT + ".introduction"),
                resultSet.getString(AppConfig.TABLE_PRODUCT + ".CPU"),
                resultSet.getString(AppConfig.TABLE_PRODUCT + ".display"),
                resultSet.getInt(AppConfig.TABLE_PRODUCT + ".memory"),
                resultSet.getInt(AppConfig.TABLE_PRODUCT + ".storage"),
                resultSet.getString(AppConfig.TABLE_PRODUCT + ".GPU"),
                resultSet.getDouble(AppConfig.TABLE_PRODUCT + ".battery"),
                resultSet.getDouble(AppConfig.TABLE_PRODUCT + ".weight"),
                resultSet.getString(AppConfig.TABLE_PRODUCT + ".operating_system"),
                resultSet.getBoolean(AppConfig.TABLE_PRODUCT + ".sold_out"),
                resultSet.getInt(AppConfig.TABLE_PRODUCT + ".guarantee"),
                new CategoryDTO(resultSet.getInt(AppConfig.TABLE_CATEGORY + ".id"),
                        resultSet.getString(AppConfig.TABLE_CATEGORY + ".name")),
                resultSet.getInt(AppConfig.TABLE_PRODUCT + ".bought"),
                resultSet.getDate(AppConfig.TABLE_PRODUCT + ".modify_date"),
                resultSet.getDate(AppConfig.TABLE_PRODUCT + ".create_date"),
                resultSet.getString(AppConfig.TABLE_PRODUCT + ".create_by"),
                resultSet.getString(AppConfig.TABLE_PRODUCT + ".modify_by"),
                resultSet.getString(AppConfig.TABLE_PRODUCT + ".others"));
    }

    @Override
    public List<Product> getList(ResultSet resultSet) throws SQLException {
        List<Product> productList = new ArrayList<>();
        Product product = null;

        if (resultSet.first()) {
            do {
                product = getObject(resultSet);
                if (product != null) {
                    productList.add(product);
                }
            } while (resultSet.next());
        }

        return productList.isEmpty() ? null : productList;
    }

    @Override
    public List<Product> findAll() throws SQLException {
        String table = new SQLBuilder.BuildTable(AppConfig.TABLE_PRODUCT)
                .addTable(AppConfig.TABLE_CATEGORY, "id", AppConfig.TABLE_PRODUCT, "category_id")
                .build();

        ResultSet resultSet = DatabaseService.getInstance().execQuery(table, null, "product.deleted = false", null);

        return getList(resultSet);
    }

    @Override
    public Product findById(Integer id) throws SQLException {
        Product product = null;
        String table = new SQLBuilder.BuildTable(AppConfig.TABLE_PRODUCT)
                .addTable(AppConfig.TABLE_CATEGORY, "id", AppConfig.TABLE_PRODUCT, "category_id")
                .build();

        ResultSet resultSet = DatabaseService.getInstance().execQuery(table, null, "product.deleted = false AND product.id = ?", new Object[] {id});

        if (resultSet.first()) {
            product = getObject(resultSet);
        }

        return product;
    }

    @Override
    public Product insert(Product product) throws SQLException {
        Product new_product = null;

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", product.getName());
        contentValues.put("price", product.getPrice());
        contentValues.put("discount", product.getDiscount());
        contentValues.put("deleted", product.getDeleted());
        contentValues.put("image", product.getImage());
        contentValues.put("introduction", product.getIntroduction());
        contentValues.put("CPU", product.getCPU());
        contentValues.put("display", product.getDisplay());
        contentValues.put("memory", product.getMemory());
        contentValues.put("storage", product.getStorage());
        contentValues.put("GPU", product.getGPU());
        contentValues.put("battery", product.getBattery());
        contentValues.put("weight", product.getWeight());
        contentValues.put("operating_system", product.getOperatingSystem());
        contentValues.put("sold_out", product.isSoldOut());
        contentValues.put("guarantee", product.getGuarantee());
        contentValues.put("category_id", product.getCategory().getId());
        contentValues.put("bought", product.getBought());
        contentValues.put("modify_date", new Date(product.getModifyDate().getTime()));
        contentValues.put("create_date", new Date(product.getCreateDate().getTime()));
        contentValues.put("create_by", product.getCreateBy());
        contentValues.put("modify_by", product.getModifyBy());
        contentValues.put("others", product.getOthers());

        PreparedStatement preparedStatement = DatabaseService.getInstance().getPreparedStatementInsert( AppConfig.TABLE_PRODUCT, contentValues);
        int rs = preparedStatement.executeUpdate();
        if (rs > 0) {
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.first()) {
                new_product = findById((int) resultSet.getLong(1));
            }
        }

        return new_product;
    }

    @Override
    public List<Product> search(Product product) throws SQLException {
        String table = new SQLBuilder.BuildTable(AppConfig.TABLE_PRODUCT)
                .addTable(AppConfig.TABLE_CATEGORY, "id", AppConfig.TABLE_PRODUCT, "category_id")
                .build();
        String whereClause =  " product.deleted = false AND "
                + " (? IS NULL OR " + AppConfig.TABLE_PRODUCT + ".id = ?) "
                + " AND (? IS NULL OR " + AppConfig.TABLE_PRODUCT + ".name LIKE ?) "
                + " AND (? IS NULL OR ROUND(" + AppConfig.TABLE_PRODUCT + ".price) = ROUND(?)) "
                + " AND (? IS NULL OR " + AppConfig.TABLE_PRODUCT + ".discount = ?) "
                + " AND (? IS NULL OR " + AppConfig.TABLE_PRODUCT + ".introduction LIKE ?) "
                + " AND (? IS NULL OR " + AppConfig.TABLE_PRODUCT + ".CPU LIKE ?) "
                + " AND (? IS NULL OR " + AppConfig.TABLE_PRODUCT + ".display LIKE ?) "
                + " AND (? IS NULL OR " + AppConfig.TABLE_PRODUCT + ".memory = ?) "
                + " AND (? IS NULL OR " + AppConfig.TABLE_PRODUCT + ".storage = ?) "
                + " AND (? IS NULL OR " + AppConfig.TABLE_PRODUCT + ".GPU LIKE ?) "
                + " AND (? IS NULL OR ROUND(" + AppConfig.TABLE_PRODUCT + ".battery) = ROUND(?)) "
                + " AND (? IS NULL OR ROUND(" + AppConfig.TABLE_PRODUCT + ".weight) = ROUND(?)) "
                + " AND (? IS NULL OR " + AppConfig.TABLE_PRODUCT + ".operating_system LIKE ?) "
                + " AND (? IS NULL OR " + AppConfig.TABLE_PRODUCT + ".sold_out = ?) "
                + " AND (? IS NULL OR " + AppConfig.TABLE_PRODUCT + ".guarantee = ?) "
                + " AND (? IS NULL OR " + AppConfig.TABLE_CATEGORY + ".id = ?) "
                + " AND (? IS NULL OR " + AppConfig.TABLE_CATEGORY + ".name LIKE ?) "
                + " AND (? IS NULL OR " + AppConfig.TABLE_PRODUCT + ".bought = ?) "
                + " AND (? IS NULL OR " + AppConfig.TABLE_PRODUCT + ".others LIKE ?) "
                + " AND (? IS NULL OR DATE(" + AppConfig.TABLE_PRODUCT + ".create_date) >= ?) "
                + " AND (? IS NULL OR DATE(" + AppConfig.TABLE_PRODUCT + ".create_date) <= ?) ";

        Object[] whereArgs = new Object[] {
                product.getId(),
                product.getId(),
                product.getName(),
                "%" + product.getName() + "%",
                product.getPrice(),
                product.getPrice(),
                product.getDiscount(),
                product.getDiscount(),
                product.getIntroduction(),
                "%" + product.getIntroduction() + "%",
                product.getCPU(),
                "%" + product.getCPU() + "%",
                product.getDisplay(),
                "%" + product.getDisplay() + "%",
                product.getMemory(),
                product.getMemory(),
                product.getStorage(),
                product.getStorage(),
                product.getGPU(),
                "%" + product.getGPU() + "%",
                product.getBattery(),
                product.getBattery(),
                product.getWeight(),
                product.getWeight(),
                product.getOperatingSystem(),
                "%" + product.getOperatingSystem() + "%",
                product.isSoldOut(),
                product.isSoldOut(),
                product.getGuarantee(),
                product.getGuarantee(),
                product.getCategory() == null ? null : product.getCategory().getId(),
                product.getCategory() == null ? null : product.getCategory().getId(),
                product.getCategory() == null ? null : product.getCategory().getName(),
                product.getCategory() == null ? null : "%" + product.getCategory().getName() + "%",
                product.getBought(),
                product.getBought(),
                product.getOthers(),
                "%" + product.getOthers() + "%",
                product.getCreateDate() == null ? null : new Date(product.getCreateDate().getTime()),
                product.getCreateDate() == null ? null : new Date(product.getCreateDate().getTime()),
                product.getCreateDate() == null ? null : new Date(product.getCreateDate().getTime()),
                product.getCreateDate() == null ? null : new Date(product.getCreateDate().getTime())
        };

        ResultSet resultSet = DatabaseService.getInstance().execQuery(table, null, whereClause, whereArgs);

        return getList(resultSet);

    }

    @Override
    public Product update(Product product) throws SQLException {
        Product update_product = null;

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", product.getName());
        contentValues.put("price", product.getPrice());
        contentValues.put("discount", product.getDiscount());
        contentValues.put("image", product.getImage());
        contentValues.put("introduction", product.getIntroduction());
        contentValues.put("CPU", product.getCPU());
        contentValues.put("display", product.getDisplay());
        contentValues.put("memory", product.getMemory());
        contentValues.put("storage", product.getStorage());
        contentValues.put("GPU", product.getGPU());
        contentValues.put("battery", product.getBattery());
        contentValues.put("weight", product.getWeight());
        contentValues.put("operating_system", product.getOperatingSystem());
        contentValues.put("sold_out", product.isSoldOut());
        contentValues.put("guarantee", product.getGuarantee());
        contentValues.put("category_id", product.getCategory().getId());
        contentValues.put("bought", product.getBought());
        contentValues.put("modify_date", new Date(product.getModifyDate().getTime()));
        contentValues.put("modify_by", product.getModifyBy());
        contentValues.put("others", product.getOthers());

        int rs = DatabaseService.getInstance().update(AppConfig.TABLE_PRODUCT, contentValues, "id = ?", new Object[] {product.getId()});
        if (rs > 0) {
            update_product = findById(product.getId());
        }
        return update_product;
    }

    @Override
    public boolean delete(Integer id, String email, java.util.Date modify) throws SQLException {

        ContentValues contentValues = new ContentValues();

        contentValues.put("deleted", true);
        contentValues.put("modify_by", email);
        contentValues.put("modify_date", new Date(modify.getTime()));

        int delete = DatabaseService.getInstance().update(AppConfig.TABLE_PRODUCT, contentValues, "id = ?", new Object[] {id});

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

        DatabaseService.getInstance().update(AppConfig.TABLE_PRODUCT, contentValues, column + " = ? ", new Object[] {oldEmail});
    }
}
