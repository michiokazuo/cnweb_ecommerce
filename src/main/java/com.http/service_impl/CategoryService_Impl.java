package com.http.service_impl;

import com.http.config.AppConfig;
import com.http.convert.CategoryConvert;
import com.http.convert.Convert;
import com.http.dao.CategoryDao;
import com.http.dao_impl.CategoryDaoImpl;
import com.http.dto.CategoryDTO;
import com.http.dto.UserDTO;
import com.http.model.Category;
import com.http.service.CategoryService;

import java.util.Date;
import java.util.List;

public class CategoryService_Impl implements CategoryService {
    private final CategoryDao categoryDao = new CategoryDaoImpl();
    private final Convert<Category, CategoryDTO> convert = new CategoryConvert();

    @Override
    public List<CategoryDTO> findAll() throws Exception {
        return convert.toDtoList(categoryDao.findAll());
    }

    @Override
    public CategoryDTO findById(Integer id) throws Exception {
        return id != null && id > 0 ? convert.toDTO(categoryDao.findById(id)) : null;
    }

    @Override
    public CategoryDTO insert(CategoryDTO categoryDTO) throws Exception {
        UserDTO userInSystem = AppConfig.userInSysTem;
        if (categoryDTO != null && AppConfig.checkAdmin(userInSystem)) {
            Category category = convert.toModel(categoryDTO);
            Date date = new Date();
            category.setCreateDate(date);
            category.setModifyDate(date);
            category.setCreateBy(userInSystem.getEmail());
            category.setModifyBy(userInSystem.getEmail());
            category.setDeleted(false);

            return convert.toDTO(categoryDao.insert(category));
        }

        return null;
    }

    @Override
    public List<CategoryDTO> search(CategoryDTO categoryDTO) throws Exception {
        return categoryDTO != null ? convert.toDtoList(categoryDao.search(convert.toModel(categoryDTO))) : findAll();
    }

    @Override
    public CategoryDTO update(CategoryDTO categoryDTO) throws Exception {
        UserDTO userInSystem = AppConfig.userInSysTem;
        if (categoryDTO != null && AppConfig.checkAdmin(userInSystem)) {
            Category category = convert.toModel(categoryDTO);
            Date date = new Date();
            category.setModifyDate(date);
            category.setModifyBy(userInSystem.getEmail());
            category.setDeleted(false);

            return convert.toDTO(categoryDao.update(category));
        }

        return null;
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        UserDTO userInSystem = AppConfig.userInSysTem;
        return id != null && id > 0 && AppConfig.checkAdmin(userInSystem)
                && categoryDao.delete(id, userInSystem.getEmail(), new Date());
    }
}
