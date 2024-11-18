package com.cesarschool.constancia.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Carrinho {
    private int idCarrinho;
    private int qtdProdutos;
    private Date dataAlteracao;
    private Date dataCriacao;
    private String clienteCpf; // Relacionamento com Cliente
    private int produtoCodigo; // Relacionamento com Produto
}

