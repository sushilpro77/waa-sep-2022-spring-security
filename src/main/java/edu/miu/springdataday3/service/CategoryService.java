package edu.miu.springdataday3.service;

import edu.miu.springdataday3.entity.Category;
import edu.miu.springdataday3.entity.dto.CategoryDTO;
import edu.miu.springdataday3.entity.dto.ProductDTO;

import java.util.List;

public interface CategoryService {
    // get
    List<CategoryDTO> getAll();

    //post
    CategoryDTO save(CategoryDTO categoryDTO);

    //update
    CategoryDTO update(Long id, CategoryDTO categoryDTO);

    //delete
    CategoryDTO delete(Long id);

    List<ProductDTO> findProductsWithCateAndMaxPrice(String name, Long maxPrice);
}
