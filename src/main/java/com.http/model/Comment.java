package com.http.model;

import com.http.dto.ProductDTO;
import com.http.dto.UserDTO;

import java.util.Date;

public class Comment extends Base {

    private Integer id;

    private String comment;

    private Double rate;

    private ProductDTO productDTO;

    private UserDTO userDTO;

    public Comment() {
        super();
    }

    public Comment(Integer id, String comment, Double rate, ProductDTO productDTO, UserDTO userDTO,
                   Boolean deleted, Date modifyDate, Date createDate, String createBy, String modifyBy) {
        super(modifyDate, createDate, createBy, modifyBy, deleted);
        this.id = id;
        this.comment = comment;
        this.rate = rate;
        this.productDTO = productDTO;
        this.userDTO = userDTO;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getRate() {
        return rate;
    }

    public ProductDTO getProductDTO() {
        return productDTO;
    }

    public void setProductDTO(ProductDTO productDTO) {
        this.productDTO = productDTO;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id + '\'' +
                "comment=" + comment + '\'' +
                "rate=" + rate + '\'' +
                "productDTO=" + productDTO + '\'' +
                "userDTO=" + userDTO + '\'' +
                "deleted=" + getDeleted() + '\'' +
                "modifyDate=" + getModifyDate() + '\'' +
                "createDate=" + getCreateDate() + '\'' +
                "createBy=" + getCreateBy() + '\'' +
                "modifyBy=" + getModifyBy() + '\'' +
                '}';
    }
}
