package com.http.service_impl;

import com.http.dao.CommentDao;
import com.http.dao_impl.CommentDaoImpl;
import com.http.dto.CommentDTO;
import com.http.service.CommentService;

import java.sql.SQLException;
import java.util.List;

public class CommentService_Impl implements CommentService {
    private final CommentDao commentDao = new CommentDaoImpl();

    @Override
    public List<CommentDTO> findAll() throws SQLException {
        return null;
    }

    @Override
    public CommentDTO findById(int id) throws SQLException {
        return null;
    }

    @Override
    public CommentDTO insert(CommentDTO commentDTO) throws SQLException {
        return null;
    }

    @Override
    public List<CommentDTO> search(CommentDTO commentDTO) throws SQLException {
        return null;
    }

    @Override
    public CommentDTO update(CommentDTO commentDTO) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }
}
