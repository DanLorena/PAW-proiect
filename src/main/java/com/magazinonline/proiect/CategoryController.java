package com.magazinonline.proiect;


import com.magazinonline.proiect.bd.Category;
import com.magazinonline.proiect.bd.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;


    @DeleteMapping("/remove-duplicates")
    public ResponseEntity<String> removeDuplicates() {
        categoryService.removeDuplicates();
        return ResponseEntity.ok("Duplicate categories removed successfully!");
    }

    @RequestMapping("/")
    public String showCategories(Model model) {
        List<Category> categories = categoryService.getAllCategories(); // Obținem toate categoriile
        model.addAttribute("categories", categories);
        return "categories"; // Va căuta fișierul categories.html
    }

    // Endpoint pentru a vizualiza produsele dintr-o categorie
    @GetMapping("/{categoryName}")
    public String showCategory(@PathVariable String categoryName, Model model) {
        // Obținem categoria pe baza numelui
        Category category = categoryService.getCategoryByName(categoryName);

        // Obținem produsele din acea categorie
        List<Product> products = productService.getProductsByCategory(category.getId());


        // Adăugăm datele în model pentru a le transmite la view
        model.addAttribute("category", category);
        model.addAttribute("products", products);

        // Returnăm numele view-ului, care va căuta category.html în directorul templates
        return "category"; // Va căuta fișierul category.html în directorul templates
    }

    @GetMapping("/category/{id}")
    public String getCategoryProducts(@PathVariable Long id, Model model) {
        // Obține categoria în funcție de ID
        Category category = categoryService.getCategoryById(id);

        // Obține produsele care aparțin acestei categorii
        List<Product> products = productService.getProductsByCategory(id);

        // Adăugăm categoria și lista de produse în model
        model.addAttribute("category", category);
        model.addAttribute("products", products);

        // Returnează fișierul HTML care va arăta produsele
        return "categoryProducts"; // Numele fișierului HTML care va fi procesat de Thymeleaf
    }


}
