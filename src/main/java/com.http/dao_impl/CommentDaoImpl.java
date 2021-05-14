package com.http.dao_impl;

import com.http.dao.CommentDao;
import com.http.model.Comment;
import com.http.model.MyConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CommentDaoImpl implements CommentDao {
    public static final String NAME_COMMENT = "comment";
    private final MyConnection connection = new MyConnection();

    @Override
    public Comment getObject(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    public List<Comment> getList(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    public List<Comment> findAll() throws SQLException {
        return null;
    }

    @Override
    public Comment findById(int id) throws SQLException {
        return null;
    }

    @Override
    public Comment insert(Comment comment) throws SQLException {
        return null;
    }

    @Override
    public List<Comment> search(Comment comment) throws SQLException {
        return null;
    }

    @Override
    public Comment update(Comment comment) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }
}
