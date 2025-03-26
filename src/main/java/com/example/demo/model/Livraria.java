package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "livros")

public class Livraria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOME")
    private String nome;
    @Column(name = "AUTOR")
    private String autor;

    @Column(name = "STATUS")
    @Enumerated(EnumType.ORDINAL)
    private StatusLivros status;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public StatusLivros getStatus() {
        return status;
    }

    public void setStatus(StatusLivros status) {
        this.status = status;
    }

    public Livraria(Long id, String nome, String autor, StatusLivros status) {
        this.id = id;
        this.nome = nome;
        this.autor = autor;
        this.status = status;
    }
}


