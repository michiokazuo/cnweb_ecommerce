package com.http.dao_impl;

import com.http.dao.CommentDao;
import com.http.dto.CommentDTO;
import com.http.model.MyConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CommentDaoImpl implements CommentDao {
    public static final String NAME_COMMENT = "comment";
    private final MyConnection connection = new MyConnection();

    @Override
    public CommentDTO getObject(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    public List<CommentDTO> getList(ResultSet resultSet) throws SQLException {
        return null;
    }

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
