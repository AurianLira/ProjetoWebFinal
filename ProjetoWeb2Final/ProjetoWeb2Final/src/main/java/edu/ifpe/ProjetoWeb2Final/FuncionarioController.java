package edu.ifpe.ProjetoWeb2Final;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/funcionario")
@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://127.0.0.1:5501", "http://127.0.0.1:5502", "http://127.0.0.1:5503"}, allowCredentials = "true")
public class FuncionarioController {
    private final FuncionarioRepository funcionarioRepository = new FuncionarioRepository();

    @GetMapping("/setor/{idSetor}")
    public List<Map<String, Object>> getFuncionariosBySetor(@PathVariable Integer idSetor) {
        try {
            return funcionarioRepository.readBySetor(idSetor);
        } catch (SQLException e) {
            return List.of(); 
        }
    }

    @PostMapping
    public void createFuncionario(@RequestBody Map<String, Object> funcionario) {
        try {
            funcionarioRepository.create(funcionario);
        } catch (SQLException e) {
        }
    }

    @GetMapping("/funcionarios")
    @CrossOrigin(origins = "http://127.0.0.1:5501", allowCredentials = "true")
public List<Map<String, Object>> getFuncionarios() {
    try {
        return funcionarioRepository.readAll();
    } catch (SQLException e) {
        return List.of(); 
    }
}

}

