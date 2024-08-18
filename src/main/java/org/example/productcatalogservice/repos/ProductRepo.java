package org.example.productcatalogservice.repos;

import org.example.productcatalogservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    @Override
    Product save(Product product);

    Optional<Product> findById(Long id);
}
