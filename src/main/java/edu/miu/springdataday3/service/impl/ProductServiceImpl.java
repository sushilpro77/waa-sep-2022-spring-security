package edu.miu.springdataday3.service.impl;

import edu.miu.springdataday3.aspect.annotation.ExecutionTime;
import edu.miu.springdataday3.entity.Product;
import edu.miu.springdataday3.entity.dto.ProductDTO;
import edu.miu.springdataday3.entity.dto.ReviewDTO;
import edu.miu.springdataday3.repo.ProductRepo;
import edu.miu.springdataday3.repo.UserRepo;
import edu.miu.springdataday3.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;
    private final ModelMapper modelMapper;
    private final UserRepo userRepo;

    @Override
    @ExecutionTime
    public List<ProductDTO> getAll() {
        List<Product> productList = productRepo.findAll();
        return productList.stream().map(product -> modelMapper.map(product, ProductDTO.class)).collect(Collectors.toList());
    }

    @Override
    public ProductDTO create(ProductDTO productDTO) {

        Object principal = SecurityContextHolder. getContext(). getAuthentication(). getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal). getUsername();
        } else {
            username = principal. toString();
        }
        productDTO.setUser(userRepo.findByEmail(username));
        return modelMapper.map(productRepo.save(modelMapper.map(productDTO, Product.class)), ProductDTO.class);
    }

    @Override
//    @Transactional
    public ProductDTO update(Long id, ProductDTO productDTO) {
        if (id != productDTO.getId()) throw new RuntimeException("ID Not found during updating");
        // Product product = productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product Not Found!"));
//        product.setName(productDTO.getName());
//        product.setPrice(productDTO.getPrice());
//        product.setRating(productDTO.getRating());
//        product.setCategory(modelMapper.map(productDTO.getCategory(), Category.class));
        return modelMapper.map(productRepo.save(modelMapper.map(productDTO, Product.class)), ProductDTO.class);
    }

    @Override
    public ProductDTO delete(Long id) {
        Product product = productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product Not Found!"));
        productRepo.delete(product);
        return modelMapper.map(product, ProductDTO.class);
    }

    @Override
    public List<ProductDTO> minPrice(Long price) {
        List<Product> productList = productRepo.findAllByPriceGreaterThan(price);
        return productList.stream().map(product -> modelMapper.map(product, ProductDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> findProductWithKeyword(String keyword) {
        return productRepo.findAllByNameContaining(keyword).stream().map(p -> modelMapper.map(p, ProductDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<ReviewDTO> findReviewsOfProduct(Long id) {
        Product product = productRepo.findById(id).orElseThrow(() -> new RuntimeException("Invalid ID!"));
        return product.getReviews().stream().map(r -> modelMapper.map(r, ReviewDTO.class)).collect(Collectors.toList());
    }



}
