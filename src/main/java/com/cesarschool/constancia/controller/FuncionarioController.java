package com.cesarschool.constancia.controller;

import com.cesarschool.constancia.DAO.FuncionarioDAO;
import com.cesarschool.constancia.model.Funcionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioDAO funcionarioDAO;

    // Buscar todos os funcionários
    @GetMapping
    public List<Funcionario> listarFuncionarios() {
        return funcionarioDAO.findAll();
    }

    // Buscar um funcionário pelo CPF
    @GetMapping("/{cpf}")
    public Funcionario buscarFuncionarioPorCpf(@PathVariable String cpf) {
        return funcionarioDAO.findByCpf(cpf);
    }

    // Criar um novo funcionário
    @PostMapping
    public String salvarFuncionario(@RequestBody Funcionario funcionario) {
        funcionarioDAO.salvarFuncionario(funcionario);
        return "Funcionário salvo com sucesso!";
    }

    // Atualizar um funcionário existente
    @PutMapping("/{cpf}")
    public String atualizarFuncionario(@PathVariable String cpf, @RequestBody Funcionario funcionario) {
        funcionario.setCpf(cpf); // Garante que o CPF da URL seja usado
        funcionarioDAO.editarFuncionario(funcionario);
        return "Funcionário atualizado com sucesso!";
    }

    // Deletar um funcionário pelo CPF
    @DeleteMapping("/{cpf}")
    public String deletarFuncionario(@PathVariable String cpf) {
        funcionarioDAO.deletarFuncionario(cpf);
        return "Funcionário deletado com sucesso!";
    }
}

