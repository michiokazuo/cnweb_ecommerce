package com.http.dao_impl;

import com.http.dao.CommentDao;
import com.http.model.Comment;
import com.http.model.MyConnection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDaoImpl implements CommentDao {
    public static final String NAME_COMMENT = "comment";
    private final MyConnection connection = new MyConnection();

    @Override
    public Comment getObject(ResultSet resultSet) throws SQLException {
        return new Comment(resultSet.getInt("id"), resultSet.getString("comment"),
                resultSet.getDouble("rate"), resultSet.getInt("product_id"),
                resultSet.getInt("user_id"), resultSet.getBoolean("deleted"),
                resultSet.getDate("modify_date"), resultSet.getDate("create_date"),
                resultSet.getString("create_by"), resultSet.getString("modify_by"));
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
        String sql = "SELECT * FROM " + NAME_COMMENT + " WHERE deleted = false";

        PreparedStatement preparedStatement = connection.prepare(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        return getList(resultSet);
    }

    @Override
    public Comment findById(int id) throws SQLException {
        Comment comment = null;
        String sql = "SELECT * FROM " + NAME_COMMENT + " WHERE deleted = false AND id = ?";

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
        String sql = "INSERT INTO " + NAME_COMMENT + " (comment, rate, product_id, user_id, deleted, modify_date" +
                ", create_date, create_by, modify_by) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareUpdate(sql);
        preparedStatement.setString(1, comment.getComment());
        preparedStatement.setDouble(2, comment.getRate());
        preparedStatement.setInt(3, comment.getProductId());
        preparedStatement.setInt(4, comment.getUserId());
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
        String sql = "UPDATE " + NAME_COMMENT + " SET comment = ?, rate = ?, product_id = ?, user_id = ?" +
                ", modify_date = ?, modify_by = ? WHERE id = ?";

        PreparedStatement preparedStatement = connection.prepareUpdate(sql);
        preparedStatement.setString(1, comment.getComment());
        preparedStatement.setDouble(2, comment.getRate());
        preparedStatement.setInt(3, comment.getProductId());
        preparedStatement.setInt(4, comment.getUserId());
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
    public boolean delete(int id) throws SQLException {
        String sql = "UPDATE " + NAME_COMMENT + " SET deleted = true WHERE id = ?";

        PreparedStatement preparedStatement = connection.prepareUpdate(sql);
        preparedStatement.setInt(1, id);

        int delete = preparedStatement.executeUpdate();
        return delete >= 0;
    }

    @Override
    public List<Comment> getAllByProduct(Integer id_product) throws SQLException {
        Comment comment = null;
        String sql = "SELECT * FROM " + NAME_COMMENT + " WHERE deleted = false AND product_id = ?";

        PreparedStatement preparedStatement = connection.prepare(sql);
        preparedStatement.setInt(1, id_product);
        ResultSet resultSet = preparedStatement.executeQuery();

        return getList(resultSet);
    }

    @Override
    public List<Comment> getALlByUser(Integer id_user) throws SQLException {
        Comment comment = null;
        String sql = "SELECT * FROM " + NAME_COMMENT + " WHERE deleted = false AND user_id = ?";

        PreparedStatement preparedStatement = connection.prepare(sql);
        preparedStatement.setInt(1, id_user);
        ResultSet resultSet = preparedStatement.executeQuery();

        return getList(resultSet);
    }

    @Override
    public boolean deleteByProduct(Integer id_product) throws SQLException {
        String sql = "UPDATE " + NAME_COMMENT + " SET deleted = true WHERE product_id = ?";

        PreparedStatement preparedStatement = connection.prepareUpdate(sql);
        preparedStatement.setInt(1, id_product);

        int delete = preparedStatement.executeUpdate();
        return delete >= 0;
    }

    @Override
    public boolean deleteByUser(Integer id_user) throws SQLException {
        String sql = "UPDATE " + NAME_COMMENT + " SET deleted = true WHERE user_id = ?";

        PreparedStatement preparedStatement = connection.prepareUpdate(sql);
        preparedStatement.setInt(1, id_user);

        int delete = preparedStatement.executeUpdate();
        return delete >= 0;
    }

    @Override
    public boolean updateRateByUser(Integer rate, Integer id_user) throws SQLException {
        String sql = "UPDATE " + NAME_COMMENT + " SET rate = ? WHERE id_user = ?";

        PreparedStatement preparedStatement = connection.prepareUpdate(sql);
        preparedStatement.setInt(1, rate);
        preparedStatement.setInt(1, id_user);

        int update = preparedStatement.executeUpdate();
        return update >= 0;
    }
}
