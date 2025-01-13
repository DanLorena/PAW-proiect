package com.magazinonline.proiect.cos;


import com.magazinonline.proiect.cos.Cos;
import com.magazinonline.proiect.bd. Product;
import com.magazinonline.proiect.cos.CosRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CosService {
    private final CosRepository cosRepository;

    public CosService(CosRepository cosRepository) {
        this.cosRepository = cosRepository;
    }

    public Cos creeazaCosNou() {
        Cos cos = new Cos();
        return cosRepository.save(cos);
    }

    public Optional<Cos> getCosById(Long id) {
        return cosRepository.findById(id);
    }

    public Cos adaugaProdusInCos(Long cosId, Product produs) {
        Cos cos = cosRepository.findById(cosId).orElseThrow(() -> new RuntimeException("Coșul nu există!"));
        cos.adaugaProdus(produs);
        return cosRepository.save(cos);
    }

    public Cos stergeProdusDinCos(Long cosId, Product produs) {
        Cos cos = cosRepository.findById(cosId).orElseThrow(() -> new RuntimeException("Coșul nu există!"));
        cos.stergeProdus(produs);
        return cosRepository.save(cos);
    }

    public void finalizeazaCos(Long cosId) {
        cosRepository.deleteById(cosId);
    }
}
