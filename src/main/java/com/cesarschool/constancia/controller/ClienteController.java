package com.cesarschool.constancia.controller;

import com.cesarschool.constancia.DAO.ClienteDAO;
import com.cesarschool.constancia.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteDAO clienteDAO;

    @Autowired
    public ClienteController(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    // Lista todos os clientes
    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteDAO.findAll();
    }

    // Busca um cliente pelo CPF
    @GetMapping("/{cpf}")
    public ResponseEntity<Cliente> buscarClientePorCpf(@PathVariable String cpf) {
        Cliente cliente = clienteDAO.findByCpf(cpf);
        if (cliente != null) {
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Cria um novo cliente
    @PostMapping
    public ResponseEntity<String> criarCliente(@RequestBody Cliente cliente) {
        clienteDAO.salvarCliente(cliente);
        return ResponseEntity.ok("Cliente salvo com sucesso!");
    }

    // Atualiza um cliente existente
    @PutMapping("/{cpf}")
    public ResponseEntity<String> atualizarCliente(@PathVariable String cpf, @RequestBody Cliente clienteAtualizado) {
        Cliente clienteExistente = clienteDAO.findByCpf(cpf);
        if (clienteExistente != null) {
            clienteAtualizado.setCpf(cpf); // Garante que o CPF do cliente não será alterado
            clienteDAO.editarCliente(clienteAtualizado);
            return ResponseEntity.ok("Cliente atualizado com sucesso!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Remove um cliente pelo CPF
    @DeleteMapping("/{cpf}")
    public ResponseEntity<String> deletarCliente(@PathVariable String cpf) {
        Cliente clienteExistente = clienteDAO.findByCpf(cpf);
        if (clienteExistente != null) {
            clienteDAO.deletarCliente(cpf);
            return ResponseEntity.ok("Cliente deletado com sucesso!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
