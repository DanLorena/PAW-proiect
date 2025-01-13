package com.magazinonline.proiect.bd;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT MIN(c.id) FROM Category c GROUP BY c.name")
    List<Long> findIdsToKeep();

    void deleteByIdNotIn(List<Long> idsToKeep);
    @Modifying
    @Query("DELETE FROM Category c WHERE c.id NOT IN (" +
            "SELECT MIN(c2.id) FROM Category c2 GROUP BY c2.name)")
    void deleteDuplicateCategories();


}

