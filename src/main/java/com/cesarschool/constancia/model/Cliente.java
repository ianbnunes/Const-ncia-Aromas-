package com.cesarschool.constancia.model;

public class Cliente {
    private String cpf;
    private String nome;
    private Integer telefone; // Relacionamento com a tabela Telefone
    private String email;
    private String senha;
    private Integer endereco; // Relacionamento com a tabela Endereço

    // Construtor padrão
    public Cliente() {}

    // Construtor com parâmetros
    public Cliente(String cpf, String nome, Integer telefone, String email, String senha, Integer endereco) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.endereco = endereco;
    }

    // Getters e Setters
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getTelefone() {
        return telefone;
    }

    public void setTelefone(Integer telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getEndereco() {
        return endereco;
    }

    public void setEndereco(Integer endereco) {
        this.endereco = endereco;
    }
}
