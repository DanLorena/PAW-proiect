package com.magazinonline.proiect;


import com.magazinonline.proiect.bd.Category;
import com.magazinonline.proiect.bd.Product;
import com.magazinonline.proiect.bd.CategoryRepository;
import com.magazinonline.proiect.bd.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    // Endpointul pentru pagina principală
    @GetMapping("/")
    public String home(Model model) {
        // Preluăm toate categoriile din baza de date
        List<Category> categories = categoryRepository.findAll();
        // Adăugăm lista de categorii în model
        model.addAttribute("categories", categories);
        return "index"; // Încărcăm pagina index.html
    }

    // Endpointul pentru a arăta produsele dintr-o categorie
    @GetMapping("/category/{categoryId}")
    public String showCategoryProducts(@PathVariable("categoryId") Long categoryId, Model model) {
        // Preluăm produsele pentru categoria respectivă
        List<Product> products = productRepository.findByCategoryId(categoryId);

        // Preluăm informațiile despre categoria respectivă
        Category category = categoryRepository.findById(categoryId).orElse(null);

        // Adăugăm produsele și categoria în model
        model.addAttribute("products", products);
        model.addAttribute("category", category);

        return "category"; // Încărcăm pagina category.html
    }
}
