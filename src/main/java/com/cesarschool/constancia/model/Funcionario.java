package com.cesarschool.constancia.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Funcionario {
    private String cpf;
    private String nome;
    private String cargo;
    private String idade;
    private String cep;
    private String bairro;
    private String rua;
    private String numero;
    private String complemento;
    private String supervisorCpf; // Relacionamento com outro Funcionario
}

