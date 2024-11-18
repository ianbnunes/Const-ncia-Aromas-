package com.cesarschool.constancia.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Produto {
    private int codigo;
    private String categoria;
    private String marca;
    private String ncm;
    private String cfop;
    private int estoque;
    private int produtoTipo;
}

// Falta criar preco
