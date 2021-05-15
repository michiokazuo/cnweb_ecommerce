package com.http.dto;

public class CommentDTO {
    private Integer id;

    private String comment;

    private Double rate;

    private ProductDTO productDTO;

    private UserDTO userDTO;

    public CommentDTO() {
    }

    public CommentDTO(Integer id, String comment, Double rate, ProductDTO productDTO, UserDTO userDTO) {
        this.id = id;
        this.comment = comment;
        this.rate = rate;
        this.productDTO = productDTO;
        this.userDTO = userDTO;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
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
        return "CommentDTO{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", rate=" + rate +
                ", productDTO=" + productDTO +
                ", userDTO=" + userDTO +
                '}';
    }
}
