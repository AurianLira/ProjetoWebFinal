package edu.ifpe.ProjetoWeb2Final;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.ifpe.ProjetoWeb2Final.entities.Relato;
import edu.ifpe.ProjetoWeb2Final.entities.Setor;

public class SetorRepository implements Repository<Setor, Integer> {

    @Override
    public void create(Setor setor) throws SQLException {
        String sql = "INSERT INTO Setores (nome) VALUES (?)";
        try (Connection conn = ConnectionManager.getCurrentConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, setor.getNome());
            pstm.execute();
        }
    }

    @Override
    public void update(Setor setor) throws SQLException {
        String sql = "UPDATE Setores SET nome = ? WHERE id = ?";
        try (Connection conn = ConnectionManager.getCurrentConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, setor.getNome());
            pstm.setInt(2, setor.getId());
            pstm.execute();
        }
    }

    @Override
    public Setor read(Integer id) throws SQLException {
        String sql = "SELECT * FROM Setores WHERE id = ?";
        try (Connection conn = ConnectionManager.getCurrentConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, id);
            ResultSet result = pstm.executeQuery();
            if (result.next()) {
                Setor setor = new Setor();
                setor.setId(result.getInt("id"));
                setor.setNome(result.getString("nome"));
                return setor;
            }
        }
        return null;
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String sql = "DELETE FROM Setores WHERE id = ?";
        try (Connection conn = ConnectionManager.getCurrentConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, id);
            pstm.execute();
        }
    }

    @Override
    public List<Setor> readAll() throws SQLException {
        String sql = "SELECT * FROM Setores";
        List<Setor> setores = new ArrayList<>();
        try (Connection conn = ConnectionManager.getCurrentConnection();
             PreparedStatement pstm = conn.prepareStatement(sql);
             ResultSet result = pstm.executeQuery()) {
            while (result.next()) {
                Setor setor = new Setor();
                setor.setId(result.getInt("id"));
                setor.setNome(result.getString("nome"));
                setores.add(setor);
            }
        }
        return setores;
    }

    public List<Relato> getRelatosBySetor(Integer setorId) throws SQLException {
        String sql = """
            SELECT r.id AS id, r.data_relato, r.id_funcionario AS idFuncionario, 
                   r.id_problema AS idProblema, p.tipo AS problemaTipo, f.nome AS funcionarioNome
            FROM Relatos r
            JOIN Funcionarios f ON r.id_funcionario = f.id
            JOIN Setores s ON f.id_setor = s.id
            JOIN Problemas p ON r.id_problema = p.id
            WHERE s.id = ?
        """;
    
        List<Relato> relatos = new ArrayList<>();
        try (Connection conn = ConnectionManager.getCurrentConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, setorId);
            ResultSet result = pstm.executeQuery();
            while (result.next()) {
                Relato relato = new Relato(
                    result.getInt("id"),
                    result.getDate("data_relato"),
                    result.getInt("idFuncionario"),
                    result.getInt("idProblema"),
                    result.getString("problemaTipo"),    
                    result.getString("funcionarioNome")  
                );
                relatos.add(relato);
            }
        }
        return relatos;
    }
    
    
}
