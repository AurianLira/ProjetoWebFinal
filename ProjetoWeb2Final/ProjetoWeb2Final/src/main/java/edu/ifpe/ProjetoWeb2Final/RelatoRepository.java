package edu.ifpe.ProjetoWeb2Final;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RelatoRepository implements Repository<Map<String, Object>, Integer> {

    @Override
    public void create(Map<String, Object> relato) throws SQLException {
        String sql = "INSERT INTO Relatos (id_funcionario, id_problema, data_relato) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionManager.getCurrentConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, (Integer) relato.get("id_funcionario"));
            pstm.setInt(2, (Integer) relato.get("id_problema"));
            pstm.setDate(3, Date.valueOf((String) relato.get("data_relato"))); 
            pstm.execute();
        }
    }

    @Override
    public List<Map<String, Object>> readAll() throws SQLException {
        String sql = "SELECT * FROM Relatos";
        List<Map<String, Object>> relatos = new ArrayList<>();
        
        try (Connection conn = ConnectionManager.getCurrentConnection();
             PreparedStatement pstm = conn.prepareStatement(sql);
             ResultSet result = pstm.executeQuery()) {
            
            while (result.next()) {
                relatos.add(Map.of(
                    "id", result.getInt("id"),
                    "id_funcionario", result.getInt("id_funcionario"),
                    "id_problema", result.getInt("id_problema"),
                    "data_relato", result.getDate("data_relato")
                ));
            }
        }
        return relatos;
    }

    @Override
    public Map<String, Object> read(Integer id) throws SQLException {
        String sql = "SELECT * FROM Relatos WHERE id = ?";
        try (Connection conn = ConnectionManager.getCurrentConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, id);
            ResultSet result = pstm.executeQuery();
            if (result.next()) {
                return Map.of(
                    "id", result.getInt("id"),
                    "id_funcionario", result.getInt("id_funcionario"),
                    "id_problema", result.getInt("id_problema"),
                    "data_relato", result.getDate("data_relato")
                );
            }
        }
        return null; 
    }

    @Override
    public void update(Map<String, Object> relato) throws SQLException {
        String sql = "UPDATE Relatos SET id_funcionario = ?, id_problema = ?, data_relato = ? WHERE id = ?";
        try (Connection conn = ConnectionManager.getCurrentConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, (Integer) relato.get("id_funcionario"));
            pstm.setInt(2, (Integer) relato.get("id_problema"));
            pstm.setDate(3, Date.valueOf((String) relato.get("data_relato")));
            pstm.setInt(4, (Integer) relato.get("id"));
            pstm.executeUpdate();
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String sql = "DELETE FROM Relatos WHERE id = ?";
        try (Connection conn = ConnectionManager.getCurrentConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, id);
            pstm.execute();
        }
    }
}
