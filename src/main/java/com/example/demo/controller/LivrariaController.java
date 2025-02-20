package com.example.demo.controller;

import com.example.demo.model.Livraria;
import com.example.demo.service.LivrariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> updatelivro(@RequestBody Livraria livros, @PathVariable Long id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("custom-header", "header Customizavel");
        service.update(livros, id);
        return ResponseEntity.ok("Alterado com sucesso!");
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
