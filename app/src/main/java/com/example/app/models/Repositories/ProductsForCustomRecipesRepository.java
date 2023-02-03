package com.example.app.models.Repositories;

import com.example.app.models.entities.ProductsForCustomRecipes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsForCustomRecipesRepository extends JpaRepository<ProductsForCustomRecipes, Long> {
}
