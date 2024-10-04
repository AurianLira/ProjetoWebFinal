package edu.ifpe.ProjetoWeb2Final;

import java.sql.SQLException;
import java.util.List;

public interface Repository<C, KEY> {
    void create(C c) throws SQLException;
    void update(C c) throws SQLException;
    C read(KEY k) throws SQLException;
    void delete(KEY k) throws SQLException;
    List<C> readAll() throws SQLException;
}
