package com.http.dao;

import com.http.dto.ProductDTO;
import com.http.model.Comment;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface CommentDao extends BaseDao<Comment> {

    List<Comment> getAllByProduct(Integer id_product) throws SQLException;

    List<Comment> getALlByUser(Integer id_user) throws SQLException;

    boolean deleteByProduct(Integer id_product, String email, Date modify) throws SQLException;

    boolean deleteByUser(Integer id_user, String email, Date modify) throws SQLException;

    boolean updateRateByUser(Double rate, Integer id_user, Integer id_product, String email, Date modify)
            throws SQLException;

    ProductDTO getAboutRateOfProduct(Integer id_product) throws SQLException;

    int countCmtByProduct(Integer id_product) throws SQLException;
}
