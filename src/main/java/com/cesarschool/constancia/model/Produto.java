package com.cesarschool.constancia.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

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
    private BigDecimal preco;
}

// Falta criar preco
