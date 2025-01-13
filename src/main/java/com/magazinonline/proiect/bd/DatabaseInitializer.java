package com.magazinonline.proiect.bd;


import com.magazinonline.proiect.CategoryService;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final CategoryService categoryService;
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public DatabaseInitializer(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostConstruct
    public void initialize() {
        categoryService.removeDuplicates();
    }

    @Override
    public void run(String... args) throws Exception {
        // Verificăm dacă categoriile și produsele există deja pentru a evita duplicarea
        if (categoryRepository.count() == 0 && productRepository.count() == 0) {
            // Creăm categoriile cu nume și imagini asociate
            Category mingi = categoryRepository.findById(1L).orElse(new Category(1L, "Mingi", "Produse din categoria mingi"));
            Category accesorii = categoryRepository.findById(2L).orElse(new Category(2L, "Accesorii", "Produse din categoria accesorii"));
            Category articoleVestimentare = categoryRepository.findById(3L).orElse(new Category(3L, "Articole vestimentare", "Produse din categoria articole vestimentare"));
            Category pantofi = categoryRepository.findById(4L).orElse(new Category(4L, "Pantofi", "Produse din categoria pantofi"));


            categoryRepository.saveAll(Arrays.asList(mingi, accesorii, articoleVestimentare, pantofi));

            // Adăugăm produse pentru fiecare categorie
            Product produs1 = new Product(1L,"Minge de fotbal Nike", "Minge fotbal Nike Park Team, marime 5, Alb/Negru. Suprafata neteda ofera o senzatie excelenta a mingii.", 350.50, 100,"produs1.jpg", mingi);
            Product produs2 = new Product(1L,"Mingea de baschet Spalding", "Producator: Spalding Categorii: Minge baschet Marime: 6 Culoare: portocaliu Material: Piele compozita", 200.00, 50,"produs2.jpg", mingi);
            Product produs3 = new Product(1L,"Minge de tenis Wilson", "Mingea de tenis WILSON Roland Garros Jumbo va fi un obiect minunat de achiziționat pentru casa ta sau un suvenir minunat de oferit pentru un prieten fan al tenisului", 150.00, 200,"produs3.jpg", mingi);
            Product produs4 = new Product(1L,"Minge de volei Mikasa", "Minge volei marca Mikasa. Confectionata din 8 panouri de piele sintetica de calitate superioara. Minge oficiala FIVB. Marime 5", 80.00, 150,"produs4.jpg", mingi);
            Product produs5 = new Product(1L,"Minge de rugby Gilbert", "Un upgrade al popularului clasic Barbarian Match Ball. Aceast balon oferă cea mai bună experiență de joc", 300.00, 75,"produs5.jpg", mingi);
            Product produs6 = new Product(2L,"Geantă pentru echipament sportiv", "Geantă încăpătoare și rezistentă", 250.00, 20,"produs6.jpg", accesorii);
            Product produs7 = new Product(2L,"Bentițe absorbante", "Bentițe pentru frunte, set de 3", 30.00, 15,"produs7.jpg", accesorii);
            Product produs8 = new Product(2L,"Sticlă de apă Nike", "Sticlă de hidratare de 1L", 60.00, 50,"produs8.jpg", accesorii);
            Product produs9 = new Product(2L,"Suport pentru mingi", "Suport pentru depozitarea mingilor", 150.00, 20,"produs9.jpg", accesorii);
            Product product10 = new Product(2L,"Ochelari de înot Speedo", "Ochelari pentru competiții de înot", 125.00, 35,"produs10.jpg", accesorii);
            Product product11 = new Product(3L,"Tricou sport Nike", "Tricou ușor, material respirabil", 225.00, 200,"produs11.jpg", articoleVestimentare);
            Product product12 = new Product(3L,"Pantaloni de antrenament Adidas", "Pantaloni lungi pentru fitness", 100.00, 150,"produs12.jpg", articoleVestimentare);
            Product product13 = new Product(3L,"Șapcă sport Puma", "Șapcă din material ușor", 70.00, 40,"produs13.jpg", articoleVestimentare);
            Product product14 = new Product(3L,"Hanorac sportiv Reebok", "Hanorac pentru vreme rece", 250.00, 40,"produs14.jpg", articoleVestimentare);
            Product product15 = new Product(3L,"Jachetă impermeabilă Columbi", "Jachetă ușoară pentru outdoor", 400.00, 20,"produs15.jpg", articoleVestimentare);
            Product product16 = new Product(4L,"Adidași pentru alergare Nike", "Pantofi ușori și confortabili", 450.00, 100,"produs16.jpg", pantofi);
            Product product17 = new Product(4L,"Pantofi de baschet Air Jordan", "Pantofi special concepuți pentru baschet", 600.00, 15,"produs17.jpg", pantofi);
            Product product18 = new Product(4L,"Pantofi pentru drumeții Salomon", "Pantofi rezistenți la apă", 550.00, 10,"produs18.jpg", pantofi);
            Product product19 = new Product(4L,"Sandale sport Columbia", "Sandale ideale pentru vara", 200.00, 35,"produs19.jpg", pantofi);
            Product product20 = new Product(4L,"Papuci sport Adidas", "Papuci pentru piscină și duș", 80.00, 40,"ptodus20.jpg", pantofi);

            productRepository.saveAll(Arrays.asList(produs1, produs2, produs3, produs4, produs5, produs6, produs7, produs8, produs9, product10, product11, product12, product13, product14, product15, product16, product17, product18, product19, product20));
        }
    }

    @PostConstruct
    @Transactional // Asigură o tranzacție activă
    public void removeDuplicates() {
        // Obține ID-urile care trebuie păstrate
        List<Long> idsToKeep = categoryRepository.findIdsToKeep();

        // Șterge toate înregistrările care nu sunt în această listă
        if (idsToKeep != null && !idsToKeep.isEmpty()) {
            categoryRepository.deleteByIdNotIn(idsToKeep);

        }

    }
}