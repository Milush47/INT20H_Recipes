package com.example.app.models.Repositories;

import com.example.app.models.entities.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Products, Long> {
}
