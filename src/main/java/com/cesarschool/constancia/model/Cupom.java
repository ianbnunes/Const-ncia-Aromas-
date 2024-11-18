package com.cesarschool.constancia.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Cupom {
    private int codigo;
    private String descricao;
    private double valorDesconto;
    private Date dataValidade;
    private String condUso;
}

