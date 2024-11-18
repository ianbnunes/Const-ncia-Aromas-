package com.cesarschool.constancia.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Cliente {
    private String cpf;
    private String nome;
    private int telefoneId; // Relacionamento com a tabela Telefone
    private String email;
    private String senha;
    private int enderecoId; // Relacionamento com a tabela Endereco
}

