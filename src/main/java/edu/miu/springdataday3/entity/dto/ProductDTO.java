package edu.miu.springdataday3.entity.dto;


import edu.miu.springdataday3.entity.User;
import lombok.Data;

@Data
public class ProductDTO{
    private Long id;
    private String name;
    private Double price;
    private Integer rating;
//    @JsonBackReference
    private CategoryDTO category;

    private User user;
}
