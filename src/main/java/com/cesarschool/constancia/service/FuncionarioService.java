package com.cesarschool.constancia.service;

import com.cesarschool.constancia.DAO.FuncionarioDAO;
import com.cesarschool.constancia.model.Funcionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {

    private final FuncionarioDAO funcionarioDAO;

    @Autowired
    public FuncionarioService(FuncionarioDAO funcionarioDAO) {
        this.funcionarioDAO = funcionarioDAO;
    }

    // Lista todos os funcionários
    public List<Funcionario> listarFuncionarios() {
        return funcionarioDAO.findAll();
    }

    // Busca um funcionário pelo CPF
    public Funcionario buscarFuncionarioPorCpf(String cpf) {
        Funcionario funcionario = funcionarioDAO.findByCpf(cpf);
        if (funcionario == null) {
            throw new RuntimeException("Funcionário com CPF " + cpf + " não encontrado.");
        }
        return funcionario;
    }

    // Salva um novo funcionário
    public void salvarFuncionario(Funcionario funcionario) {
        validarFuncionario(funcionario);
        funcionarioDAO.salvarFuncionario(funcionario);
    }

    // Atualiza um funcionário existente
    public void atualizarFuncionario(String cpf, Funcionario funcionarioAtualizado) {
        Funcionario funcionarioExistente = funcionarioDAO.findByCpf(cpf);
        if (funcionarioExistente == null) {
            throw new RuntimeException("Funcionário com CPF " + cpf + " não encontrado.");
        }
        funcionarioAtualizado.setCpf(cpf); // Garante que o CPF não será alterado
        validarFuncionario(funcionarioAtualizado);
        funcionarioDAO.editarFuncionario(funcionarioAtualizado);
    }

    // Deleta um funcionário pelo CPF
    public void deletarFuncionario(String cpf) {
        Funcionario funcionarioExistente = funcionarioDAO.findByCpf(cpf);
        if (funcionarioExistente == null) {
            throw new RuntimeException("Funcionário com CPF " + cpf + " não encontrado.");
        }
        funcionarioDAO.deletarFuncionario(cpf);
    }

    // Validação básica do funcionário
    private void validarFuncionario(Funcionario funcionario) {
        if (funcionario.getCpf() == null || funcionario.getCpf().isEmpty()) {
            throw new IllegalArgumentException("O CPF é obrigatório.");
        }
        if (funcionario.getNome() == null || funcionario.getNome().isEmpty()) {
            throw new IllegalArgumentException("O nome é obrigatório.");
        }
        if (funcionario.getCargo() == null || funcionario.getCargo().isEmpty()) {
            throw new IllegalArgumentException("O cargo é obrigatório.");
        }
        if (funcionario.getIdade() == null) {
            throw new IllegalArgumentException("A data de nascimento é obrigatória.");
        }
        if (funcionario.getCep() == null || funcionario.getCep().isEmpty()) {
            throw new IllegalArgumentException("O CEP é obrigatório.");
        }
        if (funcionario.getBairro() == null || funcionario.getBairro().isEmpty()) {
            throw new IllegalArgumentException("O bairro é obrigatório.");
        }
        if (funcionario.getRua() == null || funcionario.getRua().isEmpty()) {
            throw new IllegalArgumentException("A rua é obrigatória.");
        }
        if (funcionario.getNumero() == null || funcionario.getNumero().isEmpty()) {
            throw new IllegalArgumentException("O número é obrigatório.");
        }
    }
}
