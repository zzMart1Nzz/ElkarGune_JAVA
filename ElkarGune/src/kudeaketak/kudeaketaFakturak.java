package kudeaketak;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import metodoak.DBKonexioa;
import objetuak.Fakturak;

public class kudeaketaFakturak {

    public List<Fakturak> lortuFakturak() {
        List<Fakturak> lista = new ArrayList<>();
        String sql = "SELECT idFaktura, idBazkidea, erreserbaZkia, data, totala, egoera, fakturaPDF " +
                     "FROM fakturak ORDER BY idFaktura ASC";

        try (Connection conn = DBKonexioa.konexioaEgin();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Fakturak f = mapResultSetToFakturak(rs);
                lista.add(f);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    private Fakturak mapResultSetToFakturak(ResultSet rs) throws SQLException {
        return new Fakturak(
            rs.getInt("idFaktura"),
            rs.getInt("idBazkidea"),
            rs.getInt("erreserbaZkia"),
            rs.getTimestamp("data"),
            rs.getDouble("totala"),
            rs.getBoolean("egoera"),
            rs.getString("fakturaPDF")
        );
    }
}

    /*public void sortuFaktura(Fakturak fakturak) {
        String sql = "INSERT INTO faktura (idBazkidea, erreserbaZkia, data, fakturaPDF) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBKonexioa.konexioaEgin();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            mapFakturakToPreparedStatement(fakturak, ps);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eguneratuFakturak(Fakturak fakturak) {
        String sql = "UPDATE fakturak SET idBazkidea = ?, erreserbaZkia = ? WHERE idErreserbaElementua = ?";

        try (Connection conn = DBKonexioa.konexioaEgin();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            mapErreserbaElementuaToPreparedStatement(erreserbaElementua, ps);
            ps.setInt(3, erreserbaElementua.getIdErreserba());  // Establecemos el ID para la actualización
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    // Método para eliminar un producto de la base de datos
    public boolean ezabatuErreserba(int idErreserbaElementua) {
        String sql = "DELETE FROM erreserbaelementua WHERE idErreserbaElementua = ?";

        try (Connection conn = DBKonexioa.konexioaEgin();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idErreserbaElementua);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }


    // Método auxiliar para mapear un Producto a PreparedStatement
    private void mapToPreparedStatement(ErreserbaElementua erreserbaElementua, PreparedStatement ps) throws SQLException {
        ps.setInt(1, erreserbaElementua.getIdErreserba());
        ps.setInt(2, erreserbaElementua.getIdEspazioa());

    }
}
*/

