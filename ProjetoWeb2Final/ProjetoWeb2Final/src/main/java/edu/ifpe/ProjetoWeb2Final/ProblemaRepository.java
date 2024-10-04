package edu.ifpe.ProjetoWeb2Final;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.ifpe.ProjetoWeb2Final.entities.Problema;

public class ProblemaRepository implements Repository<Problema, Integer> {

    @Override
    public void create(Problema problema) throws SQLException {
        String sql = "INSERT INTO Problemas (tipo) VALUES (?)";
        try (Connection conn = ConnectionManager.getCurrentConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, problema.getTipo());
            pstm.execute();
        }
    }

    @Override
    public List<Problema> readAll() throws SQLException {
        String sql = "SELECT * FROM Problemas";
        List<Problema> problemas = new ArrayList<>();
        
        try (Connection conn = ConnectionManager.getCurrentConnection();
             PreparedStatement pstm = conn.prepareStatement(sql);
             ResultSet result = pstm.executeQuery()) {
            
            while (result.next()) {
                Problema problema = new Problema();
                problema.setId(result.getInt("id"));
                problema.setTipo(result.getString("tipo"));
                problemas.add(problema);
            }
        }
        return problemas;
    }

    @Override
    public Problema read(Integer id) throws SQLException {
        String sql = "SELECT * FROM Problemas WHERE id = ?";
        try (Connection conn = ConnectionManager.getCurrentConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, id);
            ResultSet result = pstm.executeQuery();
            if (result.next()) {
                return new Problema(
                    result.getInt("id"),
                    result.getString("tipo")
                );
            }
        }
        return null; 
    }

    @Override
    public void update(Problema problema) throws SQLException {
        String sql = "UPDATE Problemas SET tipo = ? WHERE id = ?";
        try (Connection conn = ConnectionManager.getCurrentConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, problema.getTipo());
            pstm.setInt(2, problema.getId());
            pstm.executeUpdate();
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String sql = "DELETE FROM Problemas WHERE id = ?";
        try (Connection conn = ConnectionManager.getCurrentConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, id);
            pstm.execute();
        }
    }
}
