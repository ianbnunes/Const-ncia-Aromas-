package com.cesarschool.constancia.controller;

import com.cesarschool.constancia.DAO.ClienteDAO;
import com.cesarschool.constancia.model.Cliente;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "http://localhost:3000") // Permitir requisições do frontend
public class ClienteController {

    private final ClienteDAO clienteDAO = new ClienteDAO();

    // Lista todos os clientes
    @GetMapping
    public ResponseEntity<List<Cliente>> getAllClientes() {
        try {
            List<Cliente> clientes = clienteDAO.getAllClientes();
            return ResponseEntity.ok(clientes);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null); // Erro interno ao acessar o banco
        }
    }

    // Busca cliente por CPF
    @GetMapping("/{cpf}")
    public ResponseEntity<Cliente> getClienteByCpf(@PathVariable String cpf) {
        try {
            Cliente cliente = clienteDAO.getClienteByCpf(cpf);
            if (cliente == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(cliente);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Adiciona um novo cliente
    @PostMapping
    public ResponseEntity<String> addCliente(@RequestBody Cliente cliente) {
        try {
            clienteDAO.addCliente(cliente);
            return ResponseEntity.status(HttpStatus.CREATED).body("Cliente adicionado com sucesso!");
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao adicionar cliente: " + e.getMessage());
        }
    }

    // Atualiza um cliente existente
    @PutMapping("/{cpf}")
    public ResponseEntity<String> updateCliente(@PathVariable String cpf, @RequestBody Cliente cliente) {
        try {
            Cliente clienteExistente = clienteDAO.getClienteByCpf(cpf);
            if (clienteExistente == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");
            }
            cliente.setCpf(cpf); // Garante que o CPF não será alterado
            clienteDAO.updateCliente(cliente);
            return ResponseEntity.ok("Cliente atualizado com sucesso!");
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao atualizar cliente: " + e.getMessage());
        }
    }

    // Remove um cliente pelo CPF
    @DeleteMapping("/{cpf}")
    public ResponseEntity<String> deleteCliente(@PathVariable String cpf) {
        try {
            Cliente clienteExistente = clienteDAO.getClienteByCpf(cpf);
            if (clienteExistente == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");
            }
            clienteDAO.deleteCliente(cpf);
            return ResponseEntity.ok("Cliente deletado com sucesso!");
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao deletar cliente: " + e.getMessage());
        }
    }

    // Verifica se existe cliente pelo CPF
    @GetMapping("/exists/{cpf}")
    public ResponseEntity<Boolean> existsClienteByCpf(@PathVariable String cpf) {
        try {
            boolean exists = clienteDAO.existsClienteByCpf(cpf);
            return ResponseEntity.ok(exists);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }

    // Busca CPF por email
    @GetMapping("/cpfByEmail/{email}")
    public ResponseEntity<?> getCpfByEmail(@PathVariable String email) {
        try {
            Optional<String> cpf = clienteDAO.getCpfByEmail(email);
            if (cpf.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Cliente não encontrado com o email: " + email);
            }
            return ResponseEntity.ok(cpf.get());
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno ao acessar o banco de dados.");
        }
    }
}
