package com.http.service_impl;

import com.http.config.AppConfig;
import com.http.dao.CommentDao;
import com.http.dao_impl.CommentDaoImpl;
import com.http.dto.UserDTO;
import com.http.model.Comment;
import com.http.service.CommentService;

import java.util.Date;
import java.util.List;

public class CommentService_Impl implements CommentService {
    private final CommentDao commentDao = new CommentDaoImpl();

    @Override
    public List<Comment> findAll() throws Exception {
        if (AppConfig.checkAdmin(AppConfig.userInSysTem))
            return commentDao.findAll();
        return null;
    }

    @Override
    public Comment findById(Integer id) throws Exception {
        return id != null && id > 0 ? commentDao.findById(id) : null;
    }

    @Override
    public Comment insert(Comment comment) throws Exception {
        UserDTO userInSystem = AppConfig.userInSysTem;
        if (userInSystem != null && comment != null) {
            Date date = new Date();
            comment.setCreateDate(date);
            comment.setModifyDate(date);
            comment.setModifyBy(userInSystem.getEmail());
            comment.setCreateBy(userInSystem.getEmail());
            comment.setDeleted(false);

            return commentDao.insert(comment);
        }
        return null;
    }

    @Override
    public List<Comment> search(Comment comment) throws Exception {
        return null;
    }

    @Override
    public Comment update(Comment comment) throws Exception {
        UserDTO userInSystem = AppConfig.userInSysTem;
        if (comment != null && (AppConfig.checkAdmin(userInSystem)
                || (userInSystem != null && userInSystem.getId().equals(comment.getUserDTO().getId())))) {
            Date date = new Date();
            comment.setModifyDate(date);
            comment.setModifyBy(userInSystem.getEmail());
            comment.setDeleted(false);

            Comment updatedComment = commentDao.update(comment);
            commentDao.updateRateByUser(updatedComment.getRate(), updatedComment.getUserDTO().getId(),
                    updatedComment.getProductDTO().getId(), updatedComment.getModifyBy(),
                    updatedComment.getModifyDate());
            return updatedComment;
        }

        return null;
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        UserDTO userInSystem = AppConfig.userInSysTem;
        if (id != null && id > 0) {
            Comment comment = commentDao.findById(id);
            if (AppConfig.checkAdmin(userInSystem) || (userInSystem != null && comment != null
                    && userInSystem.getId().equals(comment.getUserDTO().getId())))
                return commentDao.delete(id, userInSystem.getEmail(), new Date());
        }
        return false;
    }

    @Override
    public List<Comment> getAllByProduct(Integer id_product) throws Exception {
        return id_product != null && id_product > 0 ? commentDao.getAllByProduct(id_product) : null;
    }

    @Override
    public List<Comment> getALlByUser(Integer id_user) throws Exception {
        return id_user != null && id_user > 0 ? commentDao.getALlByUser(id_user) : null;
    }

    @Override
    public boolean deleteByProduct(Integer id_product) throws Exception {
        UserDTO userInSysTem = AppConfig.userInSysTem;
        if (AppConfig.checkAdmin(userInSysTem) && id_product != null && id_product > 0)
            return commentDao.deleteByProduct(id_product, userInSysTem.getEmail(), new Date());
        return false;
    }

    @Override
    public boolean deleteByUser(Integer id_user) throws Exception {
        UserDTO userInSysTem = AppConfig.userInSysTem;
        if (AppConfig.checkAdmin(userInSysTem) && id_user != null && id_user > 0)
            return commentDao.deleteByUser(id_user, userInSysTem.getEmail(), new Date());
        return false;
    }

    @Override
    public boolean updateRateByUser(Double rate, Integer id_user, Integer id_product) throws Exception {
        UserDTO userInSysTem = AppConfig.userInSysTem;
        if (id_user != null && id_user > 0 && id_product != null && id_product > 0
                && AppConfig.checkAdmin(userInSysTem) || (userInSysTem != null && userInSysTem.getId().equals(id_user)))
            return commentDao.updateRateByUser(rate, id_user, id_product, userInSysTem.getEmail(), new Date());
        return false;
    }
}
