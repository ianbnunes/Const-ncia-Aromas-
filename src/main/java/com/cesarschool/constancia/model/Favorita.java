package com.cesarschool.constancia.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Favorita {
    private String clienteCpf; // Relacionamento com Cliente
    private int produtoCodigo; // Relacionamento com Produto
}

