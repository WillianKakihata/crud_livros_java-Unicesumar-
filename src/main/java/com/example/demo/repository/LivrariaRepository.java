package com.example.demo.repository;

import com.example.demo.model.Livraria;
import com.example.demo.model.StatusLivros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LivrariaRepository extends JpaRepository<Livraria, Long> {

    List<Livraria> findByStatus(StatusLivros status);
}
