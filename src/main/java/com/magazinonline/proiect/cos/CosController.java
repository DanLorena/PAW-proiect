package com.magazinonline.proiect.cos;


import com.magazinonline.proiect.cos.Cos;
import com.magazinonline.proiect.bd.Product;
import com.magazinonline.proiect.cos.CosService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cos")
public class CosController {
    private final CosService cosService;

    public CosController(CosService cosService) {
        this.cosService = cosService;
    }

    @PostMapping("/nou")
    public Cos creeazaCosNou() {
        return cosService.creeazaCosNou();
    }

    @GetMapping("/{id}")
    public Optional<Cos> getCosById(@PathVariable Long id) {
        return cosService.getCosById(id);
    }

    @GetMapping("/{id}/total")
    public Double getTotalCos(@PathVariable Long id) {
        Cos cos = cosService.getCosById(id).orElseThrow(() -> new RuntimeException("Coșul nu există!"));
        return cos.getTotal(); // Returnăm totalul coșului
    }

    @PostMapping("/{cosId}/adauga")
    public Cos adaugaProdusInCos(@PathVariable Long cosId, @RequestBody Product produs) {
        return cosService.adaugaProdusInCos(cosId, produs);
    }

    @DeleteMapping("/{cosId}/sterge")
    public Cos stergeProdusDinCos(@PathVariable Long cosId, @RequestBody Product produs) {
        return cosService.stergeProdusDinCos(cosId, produs);
    }

    @PostMapping("/{cosId}/finalizeaza")
    public void finalizeazaCos(@PathVariable Long cosId) {
        cosService.finalizeazaCos(cosId);
    }

    @GetMapping("/cos")
    public String afiseazaCos(Model model) {
        Optional<Cos> produseDinCos = cosService.getCosById(1L);  // sau orice logică pentru a obține produsele
        model.addAttribute("produse", produseDinCos);
        return "cos";  // Va căuta un fișier cos.html în resources/templates
    }

}

