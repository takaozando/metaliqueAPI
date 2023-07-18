package com.sprint.testspring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sprint.testspring.database.FuncionarioRepository;
import com.sprint.testspring.model.Funcionario;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/funcionarios")
@CrossOrigin(origins = "http://localhost:3000")
public class FuncionarioController {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioController(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    @PostMapping("/add")
    public ResponseEntity<String> criarFuncionario(@RequestBody Funcionario funcionario) {
        try {
            Funcionario novoFuncionario = funcionarioRepository.save(funcionario);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Funcionário criado com sucesso. ID: " + novoFuncionario.getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao criar funcionário: " + e.getMessage());
        }
    }

    @GetMapping("/lista")
    public ResponseEntity<List<Funcionario>> obterFuncionarios() {
        List<Funcionario> funcionarios = funcionarioRepository.findAll();
        return ResponseEntity.ok(funcionarios);
    }

    @GetMapping("/userPwd")
    public String getUserPwdByName(@RequestParam("nome") String nome) {
        return funcionarioRepository.getUserPwd(nome);
    }

    @PostMapping("/addFuncionarios")
    public ResponseEntity<String> criarFuncionarios(@RequestBody List<Funcionario> funcionarios) {
        try {
            List<Funcionario> novosFuncionarios = funcionarioRepository.saveAll(funcionarios);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Funcionários criados com sucesso. IDs: " + novosFuncionarios.stream()
                            .map(Funcionario::getId)
                            .collect(Collectors.toList()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao criar funcionários: " + e.getMessage());
        }
    }
}
