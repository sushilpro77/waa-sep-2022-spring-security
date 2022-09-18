package edu.miu.springdataday3.controller;

import edu.miu.springdataday3.aspect.offensive.OffensiveFilter;
import edu.miu.springdataday3.entity.dto.ProductDTO;
import edu.miu.springdataday3.entity.dto.ReviewDTO;
import edu.miu.springdataday3.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productService;
    //get
    @GetMapping
    public List<ProductDTO> getAll(){
        return productService.getAll();
    }

    //post
    @PostMapping
    @OffensiveFilter
    public ProductDTO create(@RequestBody ProductDTO productDTO){
        return productService.create(productDTO);
    }

    //put
    @PutMapping("/{id}")
    public ProductDTO update(@PathVariable Long id, @RequestBody ProductDTO productDTO){
        return productService.update(id, productDTO);
    }

    //delete
    @DeleteMapping("/{id}")
    public ProductDTO delete(@PathVariable Long id){
        return productService.delete(id);
    }

    // more than minPrice
    @GetMapping("/min-price/{price}")
    public List<ProductDTO> minPrice(@PathVariable Long price){
        return productService.minPrice(price);
    }

    @GetMapping("/keyword")
    public List<ProductDTO> findProductWithKeyword(@RequestParam String keyword){
        return productService.findProductWithKeyword(keyword);
    }

    @GetMapping("/{id}/reviews")
    public List<ReviewDTO> findReviewsOfProduct(@PathVariable Long id){
        return productService.findReviewsOfProduct(id);
    }
}
