package edu.ifpe.ProjetoWeb2Final;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ifpe.ProjetoWeb2Final.entities.Problema;

@RestController
@RequestMapping("/problema")
@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://127.0.0.1:5501", "http://127.0.0.1:5502", "http://127.0.0.1:5503"}, allowCredentials = "true")
public class ProblemaController {
    private final ProblemaRepository problemaRepository = new ProblemaRepository();

    @PostMapping
    public void createProblema(@RequestBody Problema problema) {
        try {
            problemaRepository.create(problema);
        } catch (SQLException e) {
        }
    }

    @GetMapping
    public List<Problema> getProblemas() {
        try {
            return problemaRepository.readAll();
        } catch (SQLException e) {
            return List.of(); 
        }
    }

    @GetMapping("/{id}")
    public Problema getProblemaById(@PathVariable Integer id) {
        try {
            return problemaRepository.read(id);
        } catch (SQLException e) {
            return null; 
        }
    }

    @PutMapping
    public void updateProblema(@RequestBody Problema problema) {
        try {
            problemaRepository.update(problema);
        } catch (SQLException e) {
        }
    }

    @DeleteMapping("/{id}")
    public void deleteProblema(@PathVariable Integer id) {
        try {
            problemaRepository.delete(id);
        } catch (SQLException e) {
        }
    }
}
