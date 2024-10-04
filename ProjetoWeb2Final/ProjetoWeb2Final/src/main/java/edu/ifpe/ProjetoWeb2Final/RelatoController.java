package edu.ifpe.ProjetoWeb2Final;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/relato")
@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://127.0.0.1:5501", "http://127.0.0.1:5502", "http://127.0.0.1:5503"}, allowCredentials = "true")
public class RelatoController {
    private final RelatoRepository relatoRepository = new RelatoRepository();

    @PostMapping
    public void createRelato(@RequestBody Map<String, Object> relato) {
        System.out.println("Dados recebidos: " + relato);
        try {
            relatoRepository.create(relato);
        } catch (SQLException e) {
        }
    }

    @GetMapping
    public List<Map<String, Object>> getRelatos() {
        try {
            return relatoRepository.readAll();
        } catch (SQLException e) {
            return List.of(); 
        }
    }

    @GetMapping("/{id}")
    public Map<String, Object> getRelatoById(@PathVariable Integer id) {
        try {
            return relatoRepository.read(id);
        } catch (SQLException e) {
            return Map.of(); 
        }
    }

    @PutMapping
    public void updateRelato(@RequestBody Map<String, Object> relato) {
        try {
            relatoRepository.update(relato);
        } catch (SQLException e) {
        }
    }

    @DeleteMapping("/{id}")
    public void deleteRelato(@PathVariable Integer id) {
        try {
            relatoRepository.delete(id);
        } catch (SQLException e) {
        }
    }

    @GetMapping("/tipos")
    public List<Map<String, Object>> getTiposRelato() {
        try {
            return relatoRepository.readAll(); 
        } catch (SQLException e) {
            return List.of(); 
        }
    }
}
