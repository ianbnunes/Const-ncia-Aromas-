package com.cesarschool.constancia.controller;

import com.cesarschool.constancia.DAO.FuncionarioDAO;
import com.cesarschool.constancia.model.Funcionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioDAO funcionarioDAO;

    // Buscar todos os funcionários
    @GetMapping
    public ResponseEntity<List<Funcionario>> listarFuncionarios() {
        try {
            List<Funcionario> funcionarios = funcionarioDAO.findAll();
            return ResponseEntity.ok(funcionarios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    // Buscar um funcionário pelo CPF
    @GetMapping("/{cpf}")
    public ResponseEntity<Funcionario> buscarFuncionarioPorCpf(@PathVariable String cpf) {
        try {
            Funcionario funcionario = funcionarioDAO.findByCpf(cpf);
            if (funcionario == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(funcionario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    // Criar um novo funcionário
    @PostMapping
    public ResponseEntity<String> salvarFuncionario(@RequestBody Funcionario funcionario) {
        try {
            funcionarioDAO.salvarFuncionario(funcionario);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Funcionário salvo com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao salvar o funcionário: " + e.getMessage());
        }
    }

    // Atualizar um funcionário existente
    @PutMapping("/{cpf}")
    public ResponseEntity<String> atualizarFuncionario(@PathVariable String cpf, @RequestBody Funcionario funcionario) {
        try {
            Funcionario funcionarioExistente = funcionarioDAO.findByCpf(cpf);
            if (funcionarioExistente == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Funcionário com CPF " + cpf + " não encontrado.");
            }
            funcionario.setCpf(cpf); // Garante que o CPF da URL seja usado
            funcionarioDAO.editarFuncionario(funcionario);
            return ResponseEntity.ok("Funcionário atualizado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao atualizar o funcionário: " + e.getMessage());
        }
    }

    // Deletar um funcionário pelo CPF
    @DeleteMapping("/{cpf}")
    public ResponseEntity<String> deletarFuncionario(@PathVariable String cpf) {
        try {
            Funcionario funcionarioExistente = funcionarioDAO.findByCpf(cpf);
            if (funcionarioExistente == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Funcionário com CPF " + cpf + " não encontrado.");
            }
            funcionarioDAO.deletarFuncionario(cpf);
            return ResponseEntity.ok("Funcionário deletado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao deletar o funcionário: " + e.getMessage());
        }
    }
}
