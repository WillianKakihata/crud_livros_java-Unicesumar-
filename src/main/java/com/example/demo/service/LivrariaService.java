package com.example.demo.service;

import com.example.demo.model.Livraria;
import com.example.demo.model.StatusLivros;
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

    public Livraria findStatus(StatusLivros status) {
        Livraria livros = livrariaRepository.findByStatus(status);
        return livros;
    }

    public void update(Livraria livraria, Long id) {
        Livraria livros = livrariaRepository.findById(id).orElse(null);
        if (livros != null) {
            livros.setNome(livraria.getNome());
            livros.setAutor(livraria.getAutor());
            livros.setStatus(livraria.getStatus());
            livrariaRepository.save(livros);
        }
    }

    public void emprestimoLivro(Livraria livraria, Long id) {
        Livraria livros = livrariaRepository.findById(id).orElse(null);
        if (livros != null) {
            if (livros.getStatus() == StatusLivros.DISPONIVEL) {
                livros.setNome(livraria.getNome());
                livros.setAutor(livraria.getAutor());
                livros.setStatus(StatusLivros.EM_EMPRESTIMO);
                livrariaRepository.save(livros);
            }
        }


    }

    public void reservarLivro(Livraria livraria, Long id) {
        Livraria livros = livrariaRepository.findById(id).orElse(null);
        if (livros != null) {
            if (livros.getStatus() == StatusLivros.DISPONIVEL) {
                livros.setNome(livraria.getNome());
                livros.setAutor(livraria.getAutor());
                livros.setStatus(StatusLivros.RESERVADO);
                livrariaRepository.save(livros);
            }
        }


    }



}
