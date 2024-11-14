package com.cesarschool.constancia.model;

import java.util.Date;
import java.util.List;

public class Funcionario {
    private String cpf;
    private String nome;
    private String cargo;
    private Date dataNasc;
    private String endereco;
    private List<String> telefones; // Associação com Telefone_Funcionario

    // Getters e setters
}

