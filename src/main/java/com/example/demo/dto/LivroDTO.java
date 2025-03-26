package com.example.demo.dto;

import com.example.demo.model.Livraria;
import com.example.demo.model.StatusLivros;

public class LivroDTO {
    private Long id;
    private String autor;
    private String nome;
    private StatusLivros status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Livraria transformarParaObjeto() {
        return new Livraria(id,nome,autor,status);
    }

    public StatusLivros getStatus() {
        return status;
    }

    public void setStatus(StatusLivros status) {
        this.status = status;
    }
}
