package com.example.demo.model;

public enum StatusLivros {
    DISPONIVEL(1),
    EM_EMPRESTIMO(2),
    RESERVADO(3);

    private int i;

    StatusLivros(int i) {
        this.i = i;
    }
    public int getDescricao() {
        return i;
    }
}
