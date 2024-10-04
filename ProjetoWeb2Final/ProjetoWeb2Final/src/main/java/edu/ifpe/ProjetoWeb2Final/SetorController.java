package edu.ifpe.ProjetoWeb2Final;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ifpe.ProjetoWeb2Final.entities.Relato;
import edu.ifpe.ProjetoWeb2Final.entities.Setor;

@RestController
@RequestMapping("/setor")
@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://127.0.0.1:5501", "http://127.0.0.1:5502", "http://127.0.0.1:5503"}, allowCredentials = "true")
public class SetorController {
    private final SetorRepository setorRepository = new SetorRepository();

    @GetMapping("/setores")
    public List<Setor> getSetores() {
        try {
            return setorRepository.readAll();
        } catch (SQLException e) {
            return List.of(); 
        }
    }

    @PostMapping("/setores")
    public void createSetor(@RequestBody Setor setor) {
        try {
            setorRepository.create(setor);
        } catch (SQLException e) {
        }
    }

    @GetMapping("/{id}")
    public Setor getSetorById(@PathVariable Integer id) {
        try {
            return setorRepository.read(id);
        } catch (SQLException e) {
            return null; 
        }
    }

    @GetMapping("/{id}/relatos")
    public List<Relato> getRelatosBySetor(@PathVariable Integer id) {
        try {
            return setorRepository.getRelatosBySetor(id);
        } catch (SQLException e) {
            return List.of(); 
        }
    }
}
