package edu.miu.springdataday3.repo;

import edu.miu.springdataday3.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    List<Product> findAllByPriceGreaterThan(Long minPrice);
    List<Product> findAllByNameContaining(String keyword);

}
