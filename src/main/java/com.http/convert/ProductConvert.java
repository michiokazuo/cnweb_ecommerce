package com.http.convert;

import com.http.dao.CommentDao;
import com.http.dao_impl.CommentDaoImpl;
import com.http.dto.ProductDTO;
import com.http.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductConvert implements Convert<Product, ProductDTO> {
    private final CommentDao commentDao = new CommentDaoImpl();

    @Override
    public ProductDTO toDTO(Product product) throws Exception {
        if (product == null) return null;
        ProductDTO dto = new ProductDTO(product.getId(), product.getName(), product.getPrice(), product.getDiscount(),
                product.getImage(), 0, 0.0, commentDao.countCmtByProduct(product.getId()),
                product.getDeleted(), product.getCategory());
        ProductDTO tmp = commentDao.getAboutRateOfProduct(dto.getId());
        if (tmp != null) {
            dto.setNumberOfRate(tmp.getNumberOfRate());
            dto.setRate(tmp.getRate());
        }
        return dto;
    }

    @Override
    public List<ProductDTO> toDtoList(List<Product> products) throws Exception {
        if (products == null) return null;
        List<ProductDTO> productDTOS = new ArrayList<>();
        for (Product p : products) {
            if (p != null)
                productDTOS.add(toDTO(p));
        }

        return productDTOS.isEmpty() ? null : productDTOS;
    }

    @Override
    public Product toModel(ProductDTO productDTO) throws Exception {
        return null;
    }
}
