package com.http.dao_impl;

import com.http.config.AppConfig;
import com.http.dao.ProductDao;
import com.http.dto.CategoryDTO;
import com.http.model.MyConnection;
import com.http.model.Product;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    private final MyConnection connection = new MyConnection();

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
        String sql = AppConfig
                .createSqlTwoTableSelect(AppConfig.TABLE_PRODUCT, "category_id", true,
                        AppConfig.TABLE_CATEGORY, "id", false);

        PreparedStatement preparedStatement = connection.prepare(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        return getList(resultSet);
    }

    @Override
    public Product findById(Integer id) throws SQLException {
        boolean need_product = true;
        boolean need_category = false;
        Product product = null;
        String sql = AppConfig
                .createSqlTwoTableSelect(AppConfig.TABLE_PRODUCT, "category_id", need_product,
                        AppConfig.TABLE_CATEGORY, "id", need_category)
                + ((need_product || need_category) ? " AND " : " WHERE ") + AppConfig.TABLE_PRODUCT + ".id = ?";
        ;

        PreparedStatement preparedStatement = connection.prepare(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.first()) {
            product = getObject(resultSet);
        }

        return product;
    }

    @Override
    public Product insert(Product product) throws SQLException {
        Product new_product = null;
        String sql = "INSERT INTO " + AppConfig.TABLE_PRODUCT + " (name, price, discount, deleted, image, introduction"
                + ", CPU, display, memory, storage, GPU, battery, weight, operating_system, sold_out, guarantee"
                + ", category_id, bought, modify_date, create_date, create_by, modify_by, others)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareUpdate(sql);
        preparedStatement.setString(1, product.getName());
        preparedStatement.setDouble(2, product.getPrice());
        preparedStatement.setInt(3, product.getDiscount());
        preparedStatement.setBoolean(4, product.getDeleted());
        preparedStatement.setString(5, product.getImage());
        preparedStatement.setString(6, product.getIntroduction());
        preparedStatement.setString(7, product.getCPU());
        preparedStatement.setString(8, product.getDisplay());
        preparedStatement.setInt(9, product.getMemory());
        preparedStatement.setInt(10, product.getStorage());
        preparedStatement.setString(11, product.getGPU());
        preparedStatement.setDouble(12, product.getBattery());
        preparedStatement.setDouble(13, product.getWeight());
        preparedStatement.setString(14, product.getOperatingSystem());
        preparedStatement.setBoolean(15, product.isSoldOut());
        preparedStatement.setInt(16, product.getGuarantee());
        preparedStatement.setInt(17, product.getCategory().getId());
        preparedStatement.setInt(18, product.getBought());
        preparedStatement.setDate(19, new Date(product.getModifyDate().getTime()));
        preparedStatement.setDate(20, new Date(product.getCreateDate().getTime()));
        preparedStatement.setString(21, product.getCreateBy());
        preparedStatement.setString(22, product.getModifyBy());
        preparedStatement.setString(23, product.getOthers());

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
        boolean need_product = true;
        boolean need_category = false;
        String sql = AppConfig
                .createSqlTwoTableSelect(AppConfig.TABLE_PRODUCT, "category_id", need_product,
                        AppConfig.TABLE_CATEGORY, "id", need_category)
                + ((need_product || need_category) ? " AND " : " WHERE ")
                + " ? IS NULL OR " + AppConfig.TABLE_PRODUCT + ".id = ?"
                + " AND ? IS NULL OR " + AppConfig.TABLE_PRODUCT + ".name LIKE ?"
                + " AND ? IS NULL OR ROUND(" + AppConfig.TABLE_PRODUCT + ".price) = ROUND(?)"
                + " AND ? IS NULL OR " + AppConfig.TABLE_PRODUCT + ".discount = ?"
                + " AND ? IS NULL OR " + AppConfig.TABLE_PRODUCT + ".introduction LIKE ?"
                + " AND ? IS NULL OR " + AppConfig.TABLE_PRODUCT + ".CPU LIKE ?"
                + " AND ? IS NULL OR " + AppConfig.TABLE_PRODUCT + ".display LIKE ?"
                + " AND ? IS NULL OR " + AppConfig.TABLE_PRODUCT + ".memory = ?"
                + " AND ? IS NULL OR " + AppConfig.TABLE_PRODUCT + ".storage = ?"
                + " AND ? IS NULL OR " + AppConfig.TABLE_PRODUCT + ".GPU LIKE ?"
                + " AND ? IS NULL OR ROUND(" + AppConfig.TABLE_PRODUCT + ".battery) = ROUND(?)"
                + " AND ? IS NULL OR ROUND(" + AppConfig.TABLE_PRODUCT + ".weight) = ROUND(?)"
                + " AND ? IS NULL OR " + AppConfig.TABLE_PRODUCT + ".operating_system LIKE ?"
                + " AND ? IS NULL OR " + AppConfig.TABLE_PRODUCT + ".sold_out = ?"
                + " AND ? IS NULL OR " + AppConfig.TABLE_PRODUCT + ".guarantee = ?"
                + " AND ? IS NULL OR " + AppConfig.TABLE_CATEGORY + ".id = ?"
                + " AND ? IS NULL OR " + AppConfig.TABLE_CATEGORY + ".name LIKE ?"
                + " AND ? IS NULL OR " + AppConfig.TABLE_PRODUCT + ".bought = ?"
                + " AND ? IS NULL OR " + AppConfig.TABLE_PRODUCT + ".others LIKE ?"
                + " AND (? IS NULL OR DATE(" + AppConfig.TABLE_PRODUCT + ".create_date) >= ?)"
                + " AND (? IS NULL OR DATE(" + AppConfig.TABLE_PRODUCT + ".create_date) <= ?) ";

        PreparedStatement preparedStatement = connection.prepare(sql);
        preparedStatement.setInt(1, product.getId());
        preparedStatement.setInt(2, product.getId());
        preparedStatement.setString(3, product.getName());
        preparedStatement.setString(4, "%" + product.getName() + "%");
        preparedStatement.setDouble(5, product.getPrice());
        preparedStatement.setDouble(6, product.getPrice());
        preparedStatement.setInt(7, product.getDiscount());
        preparedStatement.setInt(8, product.getDiscount());
        preparedStatement.setString(9, product.getIntroduction());
        preparedStatement.setString(10, "%" + product.getIntroduction() + "%");
        preparedStatement.setString(11, product.getCPU());
        preparedStatement.setString(12, "%" + product.getCPU() + "%");
        preparedStatement.setString(13, product.getDisplay());
        preparedStatement.setString(14, "%" + product.getDisplay() + "%");
        preparedStatement.setInt(15, product.getMemory());
        preparedStatement.setInt(16, product.getMemory());
        preparedStatement.setInt(17, product.getStorage());
        preparedStatement.setInt(18, product.getStorage());
        preparedStatement.setString(19, product.getGPU());
        preparedStatement.setString(20, "%" + product.getGPU() + "%");
        preparedStatement.setDouble(21, product.getBattery());
        preparedStatement.setDouble(22, product.getBattery());
        preparedStatement.setDouble(23, product.getWeight());
        preparedStatement.setDouble(24, product.getWeight());
        preparedStatement.setString(25, product.getOperatingSystem());
        preparedStatement.setString(26, "%" + product.getOperatingSystem() + "%");
        preparedStatement.setBoolean(27, product.isSoldOut());
        preparedStatement.setBoolean(28, product.isSoldOut());
        preparedStatement.setInt(29, product.getGuarantee());
        preparedStatement.setInt(30, product.getGuarantee());
        preparedStatement.setInt(31, product.getCategory() == null ? null : product.getCategory().getId());
        preparedStatement.setInt(32, product.getCategory() == null ? null
                : product.getCategory().getId());
        preparedStatement.setString(33, product.getCategory() == null ? null
                : product.getCategory().getName());
        preparedStatement.setString(34, product.getCategory() == null ? null
                : "%" + product.getCategory().getName() + "%");
        preparedStatement.setInt(35, product.getBought());
        preparedStatement.setInt(36, product.getBought());
        preparedStatement.setString(37, product.getOthers());
        preparedStatement.setString(38, "%" + product.getOthers() + "%");
        preparedStatement.setDate(39, product.getCreateDate() == null ? null
                : new Date(product.getCreateDate().getTime()));
        preparedStatement.setDate(40, product.getCreateDate() == null ? null
                : new Date(product.getCreateDate().getTime()));
        preparedStatement.setDate(41, product.getCreateDate() == null ? null
                : new Date(product.getCreateDate().getTime()));
        preparedStatement.setDate(42, product.getCreateDate() == null ? null
                : new Date(product.getCreateDate().getTime()));

        ResultSet resultSet = preparedStatement.executeQuery();

        return getList(resultSet);

    }

    @Override
    public Product update(Product product) throws SQLException {
        Product update_product = null;
        String sql = "UPDATE " + AppConfig.TABLE_PRODUCT + " SET name = ?, price = ?, discount = ?, image = ?" +
                ", introduction = ?, CPU = ?, display = ?, memory = ?, storage = ?, GPU = ?, battery = ?, weight = ?" +
                ", operating_system = ?, sold_out = ?, guarantee = ?, category_id = ?, bought = ?, modify_date = ?" +
                ", modify_by = ?, others = ? WHERE id = ?";

        PreparedStatement preparedStatement = connection.prepareUpdate(sql);
        preparedStatement.setString(1, product.getName());
        preparedStatement.setDouble(2, product.getPrice());
        preparedStatement.setInt(3, product.getDiscount());
        preparedStatement.setString(4, product.getImage());
        preparedStatement.setString(5, product.getIntroduction());
        preparedStatement.setString(6, product.getCPU());
        preparedStatement.setString(7, product.getDisplay());
        preparedStatement.setInt(8, product.getMemory());
        preparedStatement.setInt(9, product.getStorage());
        preparedStatement.setString(10, product.getGPU());
        preparedStatement.setDouble(11, product.getBattery());
        preparedStatement.setDouble(12, product.getWeight());
        preparedStatement.setString(13, product.getOperatingSystem());
        preparedStatement.setBoolean(14, product.isSoldOut());
        preparedStatement.setInt(15, product.getGuarantee());
        preparedStatement.setInt(16, product.getCategory().getId());
        preparedStatement.setInt(17, product.getBought());
        preparedStatement.setDate(18, new Date(product.getModifyDate().getTime()));
        preparedStatement.setString(19, product.getModifyBy());
        preparedStatement.setString(20, product.getOthers());
        preparedStatement.setInt(21, product.getId());

        int rs = preparedStatement.executeUpdate();
        if (rs > 0) {
            update_product = findById(product.getId());
        }
        return update_product;
    }

    @Override
    public boolean delete(Integer id, String email, java.util.Date modify) throws SQLException {
        String sql = "UPDATE " + AppConfig.TABLE_PRODUCT
                + " SET deleted = true, modify_date = ?, modify_by = ? WHERE id = ?";

        PreparedStatement preparedStatement = connection.prepareUpdate(sql);
        preparedStatement.setString(2, email);
        preparedStatement.setInt(3, id);
        preparedStatement.setDate(1, new Date(modify.getTime()));

        int delete = preparedStatement.executeUpdate();

        return delete >= 0;
    }

    @Override
    public void updateCreateAndModifyBy(String oldEmail, String newEmail) throws SQLException {
        updateBy(oldEmail, newEmail, "create_by");
        updateBy(oldEmail, newEmail, "modify_by");
    }

    private void updateBy(String oldEmail, String newEmail, String column) throws SQLException {
        String sql = "UPDATE " + AppConfig.TABLE_PRODUCT + " SET " + column + " = ?  WHERE " + column + " = ?";

        PreparedStatement preparedStatement = connection.prepareUpdate(sql);
        preparedStatement.setString(1, newEmail);
        preparedStatement.setString(2, oldEmail);

        preparedStatement.executeUpdate();
    }
}
