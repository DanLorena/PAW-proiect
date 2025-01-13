package com.magazinonline.proiect.bd;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Metodă pentru a găsi produsele după ID-ul categoriei
    List<Product> findByCategoryId(Long categoryId);
}
