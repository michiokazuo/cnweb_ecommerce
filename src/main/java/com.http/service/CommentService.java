package com.http.service;

import com.http.dto.ProductDTO;
import com.http.model.Comment;

import java.sql.SQLException;
import java.util.List;

public interface CommentService extends BaseService<Comment> {

    List<Comment> getAllByProduct(Integer id_product) throws Exception;

    List<Comment> getALlByUser(Integer id_user) throws Exception;

    boolean deleteByProduct(Integer id_product) throws Exception;

    boolean deleteByUser(Integer id_user) throws Exception;

    boolean updateRateByUser(Double rate, Integer id_user, Integer id_product) throws Exception;

    ProductDTO getAboutRateOfProduct(Integer id_product) throws SQLException;
}
