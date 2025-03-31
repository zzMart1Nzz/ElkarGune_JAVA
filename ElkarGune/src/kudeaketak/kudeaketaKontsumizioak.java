package kudeaketak;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import metodoak.DBKonexioa;
import objetuak.Kontsumizioak;

public class kudeaketaKontsumizioak {

    public List<Kontsumizioak> lortuKontsumizioak() {
        List<Kontsumizioak> lista = new ArrayList<>();
        String sql = "SELECT idKontsumizioa, erreserbaZkia, idFaktura, idProduktua," +
        			 "kopurua, prezioa, totala " +
                     "FROM kontsumizioak ORDER BY idKontsumizioa ASC";

        try (Connection conn = DBKonexioa.konexioaEgin();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Kontsumizioak k = mapResultSetToKontsumizioa(rs);
                lista.add(k);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    private Kontsumizioak mapResultSetToKontsumizioa(ResultSet rs) throws SQLException {
        return new Kontsumizioak(
            rs.getInt("idKontsumizioa"),
            rs.getInt("erreserbaZkia"),
            rs.getInt("idFaktura"),
            rs.getInt("idProduktua"),
            rs.getInt("kopurua"),
            rs.getDouble("prezioa"),
            rs.getDouble("totala")
            );
    }

    public void sortuKontsumizioa(Kontsumizioak kontsumizioa) {
        String sql = "INSERT INTO kontsumizioak (erreserbaZkia, idFaktura, idProduktua, kopurua, prezioa) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBKonexioa.konexioaEgin();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            mapKontsumizioaToPreparedStatement(kontsumizioa, ps);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eguneratuKontsumizioa(Kontsumizioak kontsumizioa) {
        String sql = "UPDATE kontsumizioak SET erreserbaZkia = ?, idFaktura = ?, idProduktua = ?, kopurua = ?, prezioa = ? WHERE idKontsumizioa = ?";

        try (Connection conn = DBKonexioa.konexioaEgin();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            mapKontsumizioaToPreparedStatement(kontsumizioa, ps);
            ps.setInt(6, kontsumizioa.getIdKontsumizioa());  // Establecemos el ID para la actualización
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para eliminar un producto de la base de datos
    public boolean ezabatuKontsumizioa(int idKontsumizioa) {
        String sql = "DELETE FROM kontsumizioak WHERE idKontsumizioa = ?";

        try (Connection conn = DBKonexioa.konexioaEgin();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idKontsumizioa);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método auxiliar para mapear un Producto a PreparedStatement
    private void mapKontsumizioaToPreparedStatement(Kontsumizioak kontsumizioa, PreparedStatement ps) throws SQLException {
        ps.setInt(1, kontsumizioa.getErreserbaZkia());
        ps.setInt(2, kontsumizioa.getIdFaktura());
        ps.setInt(3, kontsumizioa.getIdProduktua());
        ps.setInt(4, kontsumizioa.getKopurua());
        ps.setDouble(5, kontsumizioa.getPrezioa());

    }
}



