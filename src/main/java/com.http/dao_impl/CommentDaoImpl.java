package com.http.dao_impl;

import com.http.config.AppConfig;
import com.http.dao.CommentDao;
import com.http.dto.ProductDTO;
import com.http.dto.UserDTO;
import com.http.model.Comment;
import com.http.service_database.ContentValues;
import com.http.service_database.DatabaseService;
import com.http.service_database.SQLBuilder;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDaoImpl implements CommentDao {

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
        String table = new SQLBuilder.BuildTable(AppConfig.TABLE_COMMENT)
                .addTable(AppConfig.TABLE_PRODUCT, "id", AppConfig.TABLE_COMMENT, "product_id")
                .addTable(AppConfig.TABLE_USER, "id", AppConfig.TABLE_COMMENT, "user_id")
                .build();

        String whereClause = "comment.deleted = false  AND product.deleted = false AND user.deleted = false";

        ResultSet resultSet = DatabaseService.getInstance().execQuery(table, null, whereClause, null);

        return getList(resultSet);
    }

    @Override
    public Comment findById(Integer id) throws SQLException {

        Comment comment = null;
        String table = new SQLBuilder.BuildTable(AppConfig.TABLE_COMMENT)
                .addTable(AppConfig.TABLE_PRODUCT, "id", AppConfig.TABLE_COMMENT, "product_id")
                .addTable(AppConfig.TABLE_USER, "id", AppConfig.TABLE_COMMENT, "user_id")
                .build();
        String whereClause = "comment.deleted = false  AND product.deleted = false AND user.deleted = false AND " + AppConfig.TABLE_COMMENT + ".id = ?";

        ResultSet resultSet = DatabaseService.getInstance().execQuery(table, null, whereClause, new Object[] {id});

        if (resultSet.first()) {
            comment = getObject(resultSet);
        }

        return comment;
    }

    @Override
    public Comment insert(Comment comment) throws SQLException {
        Comment new_comment = null;

        ContentValues contentValues = new ContentValues();
        contentValues.put("comment", comment.getComment());
        contentValues.put("rate", comment.getRate());
        contentValues.put("product_id", comment.getProductDTO().getId());
        contentValues.put("user_id", comment.getUserDTO().getId());
        contentValues.put("deleted", comment.getDeleted());
        contentValues.put("modify_date", new Date(comment.getModifyDate().getTime()));
        contentValues.put("create_date", new Date(comment.getCreateDate().getTime()));
        contentValues.put("create_by", comment.getCreateBy());
        contentValues.put("modify_by", comment.getModifyBy());

        PreparedStatement preparedStatement = DatabaseService.getInstance().getPreparedStatementInsert(AppConfig.TABLE_COMMENT, contentValues);
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

        ContentValues contentValues = new ContentValues();
        contentValues.put("comment", comment.getComment());
        contentValues.put("rate", comment.getRate());
        contentValues.put("product_id", comment.getProductDTO().getId());
        contentValues.put("user_id", comment.getUserDTO().getId());
        contentValues.put("modify_date", new Date(comment.getModifyDate().getTime()));
        contentValues.put("modify_by", comment.getModifyBy());

        int rs = DatabaseService.getInstance().update(AppConfig.TABLE_COMMENT, contentValues, "id = ?", new Object[] {comment.getId()});
        if (rs > 0) {
            update_comment = findById(comment.getId());
        }
        return update_comment;
    }

    @Override
    public boolean delete(Integer id, String email, java.util.Date modify) throws SQLException {
        ContentValues contentValues = new ContentValues();
        contentValues.put("deleted", true);
        contentValues.put("modify_by", email);
        contentValues.put("modify_date", new Date(modify.getTime()));

        int delete = DatabaseService.getInstance().update(AppConfig.TABLE_COMMENT, contentValues, "id = ?", new Object[] {id});

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

        DatabaseService.getInstance().update(AppConfig.TABLE_COMMENT, contentValues, column + " = ? ", new Object[] {oldEmail});
    }

    @Override
    public List<Comment> getAllByProduct(Integer id_product) throws SQLException {
        String table = new SQLBuilder.BuildTable(AppConfig.TABLE_COMMENT)
                .addTable(AppConfig.TABLE_PRODUCT, "id", AppConfig.TABLE_COMMENT, "product_id")
                .addTable(AppConfig.TABLE_USER, "id", AppConfig.TABLE_COMMENT, "user_id")
                .build();
        String whereClause = "comment.deleted = false  AND product.deleted = false AND user.deleted = false AND " + AppConfig.TABLE_PRODUCT + ".id = ?";

        ResultSet resultSet = DatabaseService.getInstance().execQuery(table, null, whereClause, new Object[] {id_product});

        return getList(resultSet);
    }

    @Override
    public List<Comment> getALlByUser(Integer id_user) throws SQLException {
        String table = new SQLBuilder.BuildTable(AppConfig.TABLE_COMMENT)
                .addTable(AppConfig.TABLE_PRODUCT, "id", AppConfig.TABLE_COMMENT, "product_id")
                .addTable(AppConfig.TABLE_USER, "id", AppConfig.TABLE_COMMENT, "user_id")
                .build();
        String whereClause = "comment.deleted = false  AND product.deleted = false AND user.deleted = false AND " + AppConfig.TABLE_USER + ".id = ?";

        ResultSet resultSet = DatabaseService.getInstance().execQuery(table, null, whereClause, new Object[] {id_user});

        return getList(resultSet);
    }

    @Override
    public boolean deleteByProduct(Integer id_product, String email, java.util.Date modify) throws SQLException {
        ContentValues contentValues = new ContentValues();
        contentValues.put("deleted", true);
        contentValues.put("modify_by", email);
        contentValues.put("modify_date", new Date(modify.getTime()));

        int delete = DatabaseService.getInstance().update(AppConfig.TABLE_COMMENT, contentValues, "product_id = ?", new Object[] {id_product});

        return delete >= 0;
    }

    @Override
    public boolean deleteByUser(Integer id_user, String email, java.util.Date modify) throws SQLException {
        ContentValues contentValues = new ContentValues();
        contentValues.put("deleted", true);
        contentValues.put("modify_by", email);
        contentValues.put("modify_date", new Date(modify.getTime()));

        int delete = DatabaseService.getInstance().update(AppConfig.TABLE_COMMENT, contentValues, "user_id = ?", new Object[] {id_user});

        return delete >= 0;
    }

    @Override
    public boolean updateRateByUser(Double rate, Integer id_user, Integer id_product, String email
            , java.util.Date modify) throws SQLException {

        ContentValues contentValues = new ContentValues();
        contentValues.put("rate", rate);
        contentValues.put("modify_by", email);
        contentValues.put("modify_date", new Date(modify.getTime()));

        int update = DatabaseService.getInstance().update(AppConfig.TABLE_COMMENT, contentValues, "user_id = ? AND product_id = ?", new Object[] {id_user, id_product});

        return update >= 0;
    }

    @Override
    public ProductDTO getAboutRateOfProduct(Integer id_product) throws SQLException {
        ResultSet resultSet = DatabaseService.getInstance().execQuery(AppConfig.TABLE_COMMENT, new String[] {"COUNT(*) NUM", "AVG(rate) rate"},
                "product_id = ?", new Object[] {id_product},
                new String[] {"user_id"});

        if (resultSet.first()) {
            return new ProductDTO(resultSet.getInt("NUM"), resultSet.getDouble("rate"));
        }
        return null;
    }

    @Override
    public int countCmtByProduct(Integer id_product) throws SQLException {
        ResultSet resultSet = DatabaseService.getInstance().execQuery(AppConfig.TABLE_COMMENT, new String[] {"COUNT(*) AS NUM"}, "product_id = ?", new Object[] {id_product});

        return resultSet.first() ? resultSet.getInt("NUM") : 0;
    }
}
