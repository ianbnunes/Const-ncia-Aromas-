package com.cesarschool.constancia.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class CompraNotaFiscal {
    private int numero;
    private Date data;
    private double valor;
    private String status;
    private String nota;
    private String descItens;
    private String cpfCnpjComprador;
    private Date dataEmissao;
}
