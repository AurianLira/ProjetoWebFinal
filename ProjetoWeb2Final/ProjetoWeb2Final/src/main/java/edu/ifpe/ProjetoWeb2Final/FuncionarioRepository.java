package edu.ifpe.ProjetoWeb2Final;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FuncionarioRepository implements Repository<Map<String, Object>, Integer> {

    @Override
    public void create(Map<String, Object> funcionario) throws SQLException {
        String sql = "INSERT INTO Funcionarios (nome, registro, id_setor) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionManager.getCurrentConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, (String) funcionario.get("nome"));
            pstm.setString(2, (String) funcionario.get("registro"));
            pstm.setInt(3, Integer.parseInt((String) funcionario.get("id_setor"))); 
            
            pstm.execute();
        }
    }
    

    public List<Map<String, Object>> readBySetor(Integer idSetor) throws SQLException {
        String sql = "SELECT * FROM Funcionarios WHERE id_setor = ?";
        List<Map<String, Object>> funcionarios = new ArrayList<>();
        try (Connection conn = ConnectionManager.getCurrentConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, idSetor);
            ResultSet result = pstm.executeQuery();
            while (result.next()) {
                funcionarios.add(Map.of(
                    "id", result.getInt("id"),
                    "nome", result.getString("nome"),
                    "registro", result.getString("registro")
                ));
            }
        }
        return funcionarios;
    }

    @Override
    public void update(Map<String, Object> c) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Map<String, Object> read(Integer k) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'read'");
    }

    @Override
    public void delete(Integer k) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
public List<Map<String, Object>> readAll() throws SQLException {
    String sql = "SELECT * FROM Funcionarios";
    List<Map<String, Object>> funcionarios = new ArrayList<>();
    
    try (Connection conn = ConnectionManager.getCurrentConnection();
         PreparedStatement pstm = conn.prepareStatement(sql);
         ResultSet result = pstm.executeQuery()) {
        
        while (result.next()) {
            funcionarios.add(Map.of(
                "id", result.getInt("id"),
                "nome", result.getString("nome"),
                "registro", result.getString("registro"),
                "id_setor", result.getInt("id_setor")
            ));
        }
    }
    return funcionarios;
}

}

