package com.http.service;

import com.http.dto.ProductDTO;
import com.http.model.Product;

import java.util.List;

public interface ProductService extends BaseService<Product> {

    List<ProductDTO> getAllForShow() throws Exception;

    ProductDTO getProductDTOById(Integer id) throws Exception;

    List<ProductDTO> searchForShow(Product product) throws Exception;
}
