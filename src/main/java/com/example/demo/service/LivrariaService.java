package com.example.demo.service;

import com.example.demo.model.Livraria;
import com.example.demo.repository.LivrariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivrariaService {

    @Autowired
    LivrariaRepository livrariaRepository;

    public Livraria create(Livraria livraria) {
        return livrariaRepository.save(livraria);
    }

    public List<Livraria> findall() {
        return livrariaRepository.findAll();
    }

    public void delete(Long id) {
        livrariaRepository.deleteById(id);
    }

    public void update(Livraria livraria, Long id) {
        Livraria livros = livrariaRepository.findById(id).orElse(null);
        if (livros != null) {
            livros.setNome(livraria.getNome());
            livros.setAutor(livraria.getAutor());
            livrariaRepository.save(livros);
        }
    }



}
