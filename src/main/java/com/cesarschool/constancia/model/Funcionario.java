package com.cesarschool.constancia.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Funcionario {
    private String cpf;
    private String nome;
    private String cargo;
    private Date dataNasc;
    private String cep;
    private String bairro;
    private String rua;
    private String numero;
    private String complemento;
    private String supervisorCpf; // Relacionamento com outro Funcionario
}

