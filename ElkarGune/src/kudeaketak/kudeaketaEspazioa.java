package kudeaketak;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import metodoak.DBKonexioa;
import objetuak.Espazioa;

public class kudeaketaEspazioa {

    public List<Espazioa> lortuEspazioa() {
        List<Espazioa> lista = new ArrayList<>();
        String sql = "SELECT idEspazioa, izena, gaitasuna, egoera " +
                     "FROM espazioa ORDER BY idEspazioa ASC";

        try (Connection conn = DBKonexioa.konexioaEgin();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Espazioa es = mapResultSetToEspazioa(rs);
                lista.add(es);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    private Espazioa mapResultSetToEspazioa(ResultSet rs) throws SQLException {
        return new Espazioa(
            rs.getInt("idEspazioa"),
            rs.getString("izena"),
            rs.getInt("gaitasuna"),
            rs.getBoolean("egoera")
        );
    }

    public void sortuEspazioa(Espazioa espazioa) {
        String sql = "INSERT INTO espazioa (izena, gaitasuna, egoera) VALUES (?, ?, ?)";

        try (Connection conn = DBKonexioa.konexioaEgin();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            mapEspazioaToPreparedStatement(espazioa, ps);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eguneratuEspazioa(Espazioa espazioa) {
        String sql = "UPDATE espazioa SET izena = ?, gaitauna = ?, egoera = ? WHERE idEspazioa = ?";

        try (Connection conn = DBKonexioa.konexioaEgin();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            mapEspazioaToPreparedStatement(espazioa, ps);
            ps.setInt(4, espazioa.getIdEspazioa());  // Establecemos el ID para la actualización
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para eliminar un producto de la base de datos
    public boolean ezabatuEspazioa(int idEspazioa) {
        String sql = "DELETE FROM espazioa WHERE idEspazioa = ?";

        try (Connection conn = DBKonexioa.konexioaEgin();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idEspazioa);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método auxiliar para mapear un Producto a PreparedStatement
    private void mapEspazioaToPreparedStatement(Espazioa espazioa, PreparedStatement ps) throws SQLException {
        ps.setString(1, espazioa.getIzena());
        ps.setInt(2, espazioa.getGaitasuna());
        ps.setBoolean(3, espazioa.isEgoera());

    }
}


