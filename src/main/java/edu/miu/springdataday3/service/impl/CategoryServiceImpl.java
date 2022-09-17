package edu.miu.springdataday3.service.impl;

import edu.miu.springdataday3.aspect.annotation.ExecutionTime;
import edu.miu.springdataday3.entity.Category;
import edu.miu.springdataday3.entity.Product;
import edu.miu.springdataday3.entity.dto.CategoryDTO;
import edu.miu.springdataday3.entity.dto.ProductDTO;
import edu.miu.springdataday3.repo.CategoryRepo;
import edu.miu.springdataday3.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {


    private final CategoryRepo categoryRepo;
    private final ModelMapper modelMapper;

    @Override
    @ExecutionTime
    public List<CategoryDTO> getAll() {
        List<Category> category = categoryRepo.findAll();
        return category.stream().map(category1 -> modelMapper.map(category1, CategoryDTO.class)).collect(Collectors.toList());
    }

    @Override
    public CategoryDTO save(CategoryDTO categoryDTO) {
        return modelMapper.map(categoryRepo.save(modelMapper.map(categoryDTO, Category.class)), CategoryDTO.class);
    }

    @Override
    public CategoryDTO update(Long id, CategoryDTO categoryDTO) {
        if (id != categoryDTO.getId()) throw new RuntimeException("Category Not Found");
        return modelMapper.map(categoryRepo.save(modelMapper.map(categoryDTO, Category.class)), CategoryDTO.class);
    }

    @Override
    public CategoryDTO delete(Long id) {
        Category category = categoryRepo.findById(id).orElseThrow(() -> new RuntimeException("Category Not found"));
        categoryRepo.delete(category);
        return modelMapper.map(category, CategoryDTO.class);
    }

    @Override
    public List<ProductDTO> findProductsWithCateAndMaxPrice(String name, Long maxPrice) {
        List<Category> categories = categoryRepo.findAllByNameContaining(name);
        List<Product> productList = categories.stream().flatMap(category -> category.getProduct().stream()).distinct().collect(Collectors.toList());
        productList = productList.stream().filter(product -> product.getPrice() < maxPrice).collect(Collectors.toList());
        return productList.stream().map(product -> modelMapper.map( product, ProductDTO.class)).collect(Collectors.toList());
    }


}
