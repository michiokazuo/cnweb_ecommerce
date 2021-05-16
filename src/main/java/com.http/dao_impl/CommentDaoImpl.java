package com.http.dao_impl;

import com.http.config.AppConfig;
import com.http.dao.CommentDao;
import com.http.dto.ProductDTO;
import com.http.dto.UserDTO;
import com.http.model.Comment;
import com.http.model.MyConnection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDaoImpl implements CommentDao {
    private final MyConnection connection = new MyConnection();

    @Override
    public Comment getObject(ResultSet resultSet) throws SQLException {
        return new Comment(resultSet.getInt(AppConfig.TABLE_COMMENT + ".id"),
                resultSet.getString(AppConfig.TABLE_COMMENT + ".comment"),
                resultSet.getDouble(AppConfig.TABLE_COMMENT + ".rate"),
                new ProductDTO(resultSet.getInt(AppConfig.TABLE_PRODUCT + ".id"),
                        resultSet.getString(AppConfig.TABLE_PRODUCT + ".name"),
                        resultSet.getString(AppConfig.TABLE_PRODUCT + ".image"),
                        resultSet.getBoolean(AppConfig.TABLE_PRODUCT + ".deleted")),
                new UserDTO(resultSet.getInt(AppConfig.TABLE_USER + ".id"),
                        resultSet.getString(AppConfig.TABLE_USER + ".name"),
                        resultSet.getString(AppConfig.TABLE_USER + ".avatar"),
                        resultSet.getString(AppConfig.TABLE_USER + ".email"),
                        resultSet.getBoolean(AppConfig.TABLE_USER + ".deleted")),
                resultSet.getBoolean(AppConfig.TABLE_COMMENT + ".deleted"),
                resultSet.getDate(AppConfig.TABLE_COMMENT + ".modify_date"),
                resultSet.getDate(AppConfig.TABLE_COMMENT + ".create_date"),
                resultSet.getString(AppConfig.TABLE_COMMENT + ".create_by"),
                resultSet.getString(AppConfig.TABLE_COMMENT + ".modify_by"));
    }

    @Override
    public List<Comment> getList(ResultSet resultSet) throws SQLException {
        List<Comment> commentList = new ArrayList<>();
        Comment comment = null;

        if (resultSet.first()) {
            do {
                comment = getObject(resultSet);
                if (comment != null) {
                    commentList.add(comment);
                }
            } while (resultSet.next());
        }

        return commentList.isEmpty() ? null : commentList;
    }

    @Override
    public List<Comment> findAll() throws SQLException {
        String sql = AppConfig.createSqlThreeTableSelect(AppConfig.TABLE_COMMENT, "product_id",
                "user_id", true,
                AppConfig.TABLE_PRODUCT, "id", true,
                AppConfig.TABLE_USER, "id", true);

        PreparedStatement preparedStatement = connection.prepare(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        return getList(resultSet);
    }

    @Override
    public Comment findById(Integer id) throws SQLException {
        boolean need_cmt = true;
        boolean need_product = true;
        boolean need_user = true;
        Comment comment = null;
        String sql = AppConfig.createSqlThreeTableSelect(AppConfig.TABLE_COMMENT, "product_id",
                "user_id", need_cmt,
                AppConfig.TABLE_PRODUCT, "id", need_product,
                AppConfig.TABLE_USER, "id", need_user)
                + ((need_cmt || need_product || need_user) ? " AND " : " WHERE ") + AppConfig.TABLE_COMMENT + ".id = ?";

        PreparedStatement preparedStatement = connection.prepare(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.first()) {
            comment = getObject(resultSet);
        }

        return comment;
    }

    @Override
    public Comment insert(Comment comment) throws SQLException {
        Comment new_comment = null;
        String sql = "INSERT INTO " + AppConfig.TABLE_COMMENT + " (comment, rate, product_id, user_id, deleted" +
                ", modify_date, create_date, create_by, modify_by) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareUpdate(sql);
        preparedStatement.setString(1, comment.getComment());
        preparedStatement.setDouble(2, comment.getRate());
        preparedStatement.setInt(3, comment.getProductDTO().getId());
        preparedStatement.setInt(4, comment.getUserDTO().getId());
        preparedStatement.setBoolean(5, comment.getDeleted());
        preparedStatement.setDate(6, new Date(comment.getModifyDate().getTime()));
        preparedStatement.setDate(7, new Date(comment.getCreateDate().getTime()));
        preparedStatement.setString(8, comment.getCreateBy());
        preparedStatement.setString(9, comment.getModifyBy());

        int rs = preparedStatement.executeUpdate();
        if (rs > 0) {
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.first()) {
                new_comment = findById((int) resultSet.getLong(1));
            }
        }
        return new_comment;
    }

    @Override
    public List<Comment> search(Comment comment) throws SQLException {
        return null;
    }

    @Override
    public Comment update(Comment comment) throws SQLException {
        Comment update_comment = null;
        String sql = "UPDATE " + AppConfig.TABLE_COMMENT + " SET comment = ?, rate = ?, product_id = ?, user_id = ?" +
                ", modify_date = ?, modify_by = ? WHERE id = ?";

        PreparedStatement preparedStatement = connection.prepareUpdate(sql);
        preparedStatement.setString(1, comment.getComment());
        preparedStatement.setDouble(2, comment.getRate());
        preparedStatement.setInt(3, comment.getProductDTO().getId());
        preparedStatement.setInt(4, comment.getUserDTO().getId());
        preparedStatement.setDate(5, new Date(comment.getModifyDate().getTime()));
        preparedStatement.setString(6, comment.getModifyBy());
        preparedStatement.setInt(6, comment.getId());

        int rs = preparedStatement.executeUpdate();
        if (rs > 0) {
            update_comment = findById(comment.getId());
        }
        return update_comment;
    }

    @Override
    public boolean delete(Integer id, String email, java.util.Date modify) throws SQLException {
        String sql = "UPDATE " + AppConfig.TABLE_COMMENT
                + " SET deleted = false, modify_date = ?, modify_by = ? WHERE id = ?";

        PreparedStatement preparedStatement = connection.prepareUpdate(sql);
        preparedStatement.setInt(3, id);
        preparedStatement.setString(2, email);
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
        String sql = "UPDATE " + AppConfig.TABLE_COMMENT + " SET " + column + " = ?  WHERE " + column + " = ?";

        PreparedStatement preparedStatement = connection.prepareUpdate(sql);
        preparedStatement.setString(1, newEmail);
        preparedStatement.setString(2, oldEmail);

        preparedStatement.executeUpdate();
    }

    @Override
    public List<Comment> getAllByProduct(Integer id_product) throws SQLException {
        boolean need_cmt = true;
        boolean need_product = true;
        boolean need_user = true;
        String sql = AppConfig.createSqlThreeTableSelect(AppConfig.TABLE_COMMENT, "product_id",
                "user_id", need_cmt,
                AppConfig.TABLE_PRODUCT, "id", need_product,
                AppConfig.TABLE_USER, "id", need_user)
                + ((need_cmt || need_product || need_user) ? " AND " : " WHERE ") + AppConfig.TABLE_PRODUCT + ".id = ?";

        PreparedStatement preparedStatement = connection.prepare(sql);
        preparedStatement.setInt(1, id_product);
        ResultSet resultSet = preparedStatement.executeQuery();

        return getList(resultSet);
    }

    @Override
    public List<Comment> getALlByUser(Integer id_user) throws SQLException {
        boolean need_cmt = true;
        boolean need_product = true;
        boolean need_user = true;
        String sql = AppConfig.createSqlThreeTableSelect(AppConfig.TABLE_COMMENT, "product_id",
                "user_id", need_cmt,
                AppConfig.TABLE_PRODUCT, "id", need_product,
                AppConfig.TABLE_USER, "id", need_user)
                + ((need_cmt || need_product || need_user) ? " AND " : " WHERE ") + AppConfig.TABLE_USER + ".id = ?";

        PreparedStatement preparedStatement = connection.prepare(sql);
        preparedStatement.setInt(1, id_user);
        ResultSet resultSet = preparedStatement.executeQuery();

        return getList(resultSet);
    }

    @Override
    public boolean deleteByProduct(Integer id_product, String email, java.util.Date modify) throws SQLException {
        String sql = "UPDATE " + AppConfig.TABLE_COMMENT
                + " SET deleted = false, modify_date = ?, modify_by = ? WHERE product_id = ?";

        PreparedStatement preparedStatement = connection.prepareUpdate(sql);
        preparedStatement.setInt(3, id_product);
        preparedStatement.setString(2, email);
        preparedStatement.setDate(1, new Date(modify.getTime()));

        int delete = preparedStatement.executeUpdate();

        return delete >= 0;
    }

    @Override
    public boolean deleteByUser(Integer id_user, String email, java.util.Date modify) throws SQLException {
        String sql = "UPDATE " + AppConfig.TABLE_COMMENT
                + " SET deleted = false, modify_date = ?, modify_by = ? WHERE user_id = ?";

        PreparedStatement preparedStatement = connection.prepareUpdate(sql);
        preparedStatement.setString(2, email);
        preparedStatement.setInt(3, id_user);
        preparedStatement.setDate(1, new Date(modify.getTime()));

        int delete = preparedStatement.executeUpdate();

        return delete >= 0;
    }

    @Override
    public boolean updateRateByUser(Double rate, Integer id_user, Integer id_product, String email
            , java.util.Date modify) throws SQLException {
        String sql = "UPDATE " + AppConfig.TABLE_COMMENT
                + " SET rate = ?, modify_date = ?, modify_by = ? WHERE user_id = ? AND product_id = ?";

        PreparedStatement preparedStatement = connection.prepareUpdate(sql);
        preparedStatement.setDouble(1, rate);
        preparedStatement.setString(3, email);
        preparedStatement.setDate(2, new Date(modify.getTime()));
        preparedStatement.setInt(4, id_user);
        preparedStatement.setInt(5, id_product);

        int update = preparedStatement.executeUpdate();

        return update >= 0;
    }

    @Override
    public ProductDTO getAboutRateOfProduct(Integer id_product) throws SQLException {
        String sql = "SELECT COUNT(*) NUM, AVG(rate) rate FROM " + AppConfig.TABLE_COMMENT
                + " WHERE product_id = ? GROUP BY user_id";

        PreparedStatement preparedStatement = connection.prepare(sql);
        preparedStatement.setDouble(1, id_product);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.first()) {
            return new ProductDTO(resultSet.getInt("NUM"), resultSet.getDouble("rate"));
        }
        return null;
    }

    @Override
    public int countCmtByProduct(Integer id_product) throws SQLException {
        String sql = "SELECT COUNT(*) NUM FROM " + AppConfig.TABLE_COMMENT + " WHERE product_id = ?";

        PreparedStatement preparedStatement = connection.prepare(sql);
        preparedStatement.setDouble(1, id_product);
        ResultSet resultSet = preparedStatement.executeQuery();

        return resultSet.getInt("NUM");
    }
}
