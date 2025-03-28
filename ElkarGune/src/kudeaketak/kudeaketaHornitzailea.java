package kudeaketak;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import metodoak.DBKonexioa;
import objetuak.Hornitzailea;

public class kudeaketaHornitzailea {

    public List<Hornitzailea> lortuHornitzailea() {
        List<Hornitzailea> lista = new ArrayList<>();
        String sql = "SELECT idHornitzailea, izena, nif, helbidea, telefonoZkia, postaElektronikoa " +
                     "FROM hornitzailea ORDER BY idHornitzailea ASC";

        try (Connection conn = DBKonexioa.konexioaEgin();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Hornitzailea h = mapResultSetToHornitzailea(rs);
                lista.add(h);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    private Hornitzailea mapResultSetToHornitzailea(ResultSet rs) throws SQLException {
        return new Hornitzailea(
            rs.getInt("idHornitzailea"),
            rs.getString("izena"),
            rs.getString("nif"),
            rs.getString("helbidea"),
            rs.getString("telefonoZkia"),
            rs.getString("postaElektronikoa")
        );
    }

    public void sortuHornitzailea(Hornitzailea hornitzailea) {
        String sql = "INSERT INTO hornitzailea (izena, nif, helbidea, telefonoZkia, postaElektronikoa) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBKonexioa.konexioaEgin();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            mapHornitzaileaToPreparedStatement(hornitzailea, ps);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eguneratuHornitzailea(Hornitzailea hornitzailea) {
        String sql = "UPDATE hornitzailea SET izena = ?, nif = ?, helbidea = ?, telefonoZkia = ?, postaElektronikoa = ? WHERE idHornitzailea = ?";

        try (Connection conn = DBKonexioa.konexioaEgin();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            mapHornitzaileaToPreparedStatement(hornitzailea, ps);
            ps.setInt(6, hornitzailea.getIdHornitzailea());  // Establecemos el ID para la actualización
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para eliminar un producto de la base de datos
    public boolean ezabatuHornitzailea(int idHornitzailea) {
        String sql = "DELETE FROM hornitzailea WHERE idHornitzailea = ?";

        try (Connection conn = DBKonexioa.konexioaEgin();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idHornitzailea);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método auxiliar para mapear un Producto a PreparedStatement
    private void mapHornitzaileaToPreparedStatement(Hornitzailea hornitzailea, PreparedStatement ps) throws SQLException {
        ps.setString(1, hornitzailea.getIzena());
        ps.setString(2, hornitzailea.getNif());
        ps.setString(3, hornitzailea.getHelbidea());
        ps.setString(4, hornitzailea.getTelefonoZkia());
        ps.setString(5, hornitzailea.getPostaElektronikoa());

    }
}



