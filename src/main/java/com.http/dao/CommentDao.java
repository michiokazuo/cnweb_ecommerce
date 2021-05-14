package com.http.dao;

import com.http.model.Comment;

import java.sql.SQLException;
import java.util.List;

public interface CommentDao extends BaseDao<Comment> {

    List<Comment> getAllByProduct(Integer id_product) throws SQLException;

    List<Comment> getALlByUser(Integer id_user) throws SQLException;

    boolean deleteByProduct(Integer id_product) throws SQLException;

    boolean deleteByUser(Integer id_user) throws SQLException;

    boolean updateRateByUser(Integer rate, Integer id_user) throws SQLException;

}
