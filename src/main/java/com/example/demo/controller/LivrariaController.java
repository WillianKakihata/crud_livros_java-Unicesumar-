package com.example.demo.controller;

import com.example.demo.dto.LivroDTO;
import com.example.demo.model.Livraria;
import com.example.demo.model.StatusLivros;
import com.example.demo.service.LivrariaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LivrariaController {

    @Autowired
    LivrariaService service;

    @Autowired
    ModelMapper modelMapper;
    @GetMapping()
    public List<LivroDTO> findAll(){
        return service.findall();
    }

    @PostMapping()
    public ResponseEntity<LivroDTO> createLivros(@RequestBody LivroDTO livro) {
        Livraria novaLivraria = modelMapper.map(livro, Livraria.class);
        LivroDTO livros = service.create(novaLivraria);
        return new ResponseEntity<>(livros,HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<LivroDTO> updatelivro(@RequestBody LivroDTO dto, @PathVariable Long id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("atualizar o livro", "Valor do livro encontrado");

        Livraria novoLivro = modelMapper.map(dto, Livraria.class);
        Livraria livroAtualizado = service.update(novoLivro, id);
        if (livroAtualizado != null) {
            LivroDTO resposta = modelMapper.map(livroAtualizado, LivroDTO.class);
            return ResponseEntity.ok(resposta);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>("Deletado com sucesso", HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/status/{status}")
    public List<Livraria> findStatusLivros(@PathVariable StatusLivros status) {
        List<Livraria> livros = service.findStatus(status);
        return livros;
    }

    @PutMapping(value = "/status/reserva/{id}")
    public ResponseEntity<String> reservarLivro(@RequestBody LivroDTO dto, @PathVariable Long id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Reserva de livro", "Valor do livro (RESERVADO)");
        Livraria livro = service.reservarLivro(dto.transformarParaObjeto(), id);
        if (livro != null) {
            return new ResponseEntity<>("reservado com sucesso,", HttpStatus.BAD_REQUEST);
        }else
            return  new ResponseEntity<>("Falha na reserva", HttpStatus.BAD_REQUEST);

    }

    @PutMapping(value = "/status/emprestimo/{id}")
    public ResponseEntity<String> emprestarLivro(@RequestBody LivroDTO dto, @PathVariable Long id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Emprestimo de livro", "Valor do livro (EMPRESTADO)");
        Livraria livro = service.emprestimoLivro(dto.transformarParaObjeto(), id);
        if (livro != null) {
            return new ResponseEntity<>("Emprestimo com sucesso!", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("Falha no emprestimo!", HttpStatus.BAD_REQUEST);
        }
    }

}
