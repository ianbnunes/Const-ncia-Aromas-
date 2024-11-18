package com.cesarschool.constancia.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Vira {
    private int compraNumero; // Relacionamento com CompraNotaFiscal
    private int idCarrinho;   // Relacionamento com Carrinho
    private Date data;
}

