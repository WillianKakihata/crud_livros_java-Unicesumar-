package com.example.demo.controller;

import com.example.demo.model.Livraria;
import com.example.demo.service.LivrariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LivrariaController {

    @Autowired
    LivrariaService service;

    @GetMapping()
    public List<Livraria> findAll(){
        return service.findall();
    }
    @PostMapping()
    public Livraria createLivros(@RequestBody Livraria livros) {
        return service.create(livros);
    }

    @PutMapping(name = "/{id}")
    public Livraria updatelivro(@RequestBody Livraria livros, @PathVariable Long id) {
        service.update(livros, id);
        return livros;
    }

    @DeleteMapping(name = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
