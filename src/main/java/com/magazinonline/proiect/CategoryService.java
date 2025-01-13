package com.magazinonline.proiect;
import com.magazinonline.proiect.bd.Category;
import com.magazinonline.proiect.bd.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }



    @Transactional
    public void removeDuplicates() {
        List<Long> idsToKeep = categoryRepository.findIdsToKeep();
        if (idsToKeep != null && !idsToKeep.isEmpty()) {
            categoryRepository.deleteByIdNotIn(idsToKeep);
        }
    }

    public Category getCategoryByName(String categoryName) {
        return null;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null); // Poți să folosești Optional pentru a preveni erorile de tip null
    }

}
