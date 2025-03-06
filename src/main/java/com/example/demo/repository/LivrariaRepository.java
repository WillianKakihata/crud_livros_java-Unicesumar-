package com.example.demo.repository;

import com.example.demo.model.Livraria;
import com.example.demo.model.StatusLivros;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivrariaRepository extends JpaRepository<Livraria, Long> {
    Livraria findByStatus(StatusLivros status);
}
