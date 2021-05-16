package com.http.service_impl;

import com.http.config.AppConfig;
import com.http.convert.Convert;
import com.http.convert.ProductConvert;
import com.http.dao.ProductDao;
import com.http.dao_impl.ProductDaoImpl;
import com.http.dto.ProductDTO;
import com.http.dto.UserDTO;
import com.http.model.Product;
import com.http.service.CommentService;
import com.http.service.ProductService;

import java.util.Date;
import java.util.List;

public class ProductService_Impl implements ProductService {
    private final ProductDao productDao = new ProductDaoImpl();
    private final CommentService commentService = new CommentService_Impl();
    private final Convert<Product, ProductDTO> convert = new ProductConvert();

    @Override
    public List<Product> findAll() throws Exception {
        return productDao.findAll();
    }

    @Override
    public Product findById(Integer id) throws Exception {
        return id != null && id > 0 ? productDao.findById(id) : null;
    }

    @Override
    public Product insert(Product product) throws Exception {
        UserDTO userInSystem = AppConfig.userInSysTem;
        if (AppConfig.checkAdmin(userInSystem) && product != null) {
            Date date = new Date();
            product.setCreateDate(date);
            product.setModifyDate(date);
            product.setCreateBy(userInSystem.getEmail());
            product.setModifyBy(userInSystem.getEmail());
            product.setDeleted(false);

            return productDao.insert(product);
        }
        return null;
    }

    @Override
    public List<Product> search(Product product) throws Exception {
        return product != null ? productDao.search(product) : findAll();
    }

    @Override
    public Product update(Product product) throws Exception {
        UserDTO userInSystem = AppConfig.userInSysTem;
        if (AppConfig.checkAdmin(userInSystem) && product != null) {
            Date date = new Date();
            product.setModifyDate(date);
            product.setModifyBy(userInSystem.getEmail());
            product.setDeleted(false);

            return productDao.update(product);
        }
        return null;
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        UserDTO userInSystem = AppConfig.userInSysTem;
        return id != null && id > 0 && AppConfig.checkAdmin(userInSystem)
                && productDao.delete(id, userInSystem.getEmail(), new Date())
                && commentService.deleteByProduct(id);
    }

    @Override
    public List<ProductDTO> getAllForShow() throws Exception {
        return convert.toDtoList(findAll());
    }

    @Override
    public ProductDTO getProductDTOById(Integer id) throws Exception {
        return convert.toDTO(findById(id));
    }

    @Override
    public List<ProductDTO> searchForShow(Product product) throws Exception {
        return convert.toDtoList(search(product));
    }
}
