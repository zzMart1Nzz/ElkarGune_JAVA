package kudeaketak;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import metodoak.DBKonexioa;
import objetuak.ErreserbaHistorikoa;

public class kudeaketaErreserbaHistorikoa {

    public List<ErreserbaHistorikoa> lortuErreserbaElementua() {
        List<ErreserbaHistorikoa> lista = new ArrayList<>();
        String sql = "SELECT idErreserba, erreserbaZkia, idBazkidea, mota, data " +
                "FROM erreserba " +
                "WHERE DATE(data) < CURDATE() " +
                "ORDER BY idErreserba ASC";

        try (Connection conn = DBKonexioa.konexioaEgin();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                ErreserbaHistorikoa ee = mapResultSetToErreserbaElementua(rs);
                lista.add(ee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    private ErreserbaHistorikoa mapResultSetToErreserbaElementua(ResultSet rs) throws SQLException {
        return new ErreserbaHistorikoa(
            rs.getInt("idErreserba"),
            rs.getInt("erreserbaZkia"),
            rs.getInt("idBazkidea"),
            rs.getBoolean("mota"),
            rs.getTimestamp("data")
        );
    }

   /* public void sortuErreserbaElementua(ErreserbaHistorikoa erreserbaElementua) {
        String sql = "INSERT INTO erreserbaelementua (idErreserba, idEspazioa) VALUES (?, ?)";

        try (Connection conn = DBKonexioa.konexioaEgin();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            mapErreserbaElementuaToPreparedStatement(erreserbaElementua, ps);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eguneratuErreserbaElementua(ErreserbaHistorikoa erreserbaElementua) {
        String sql = "UPDATE erreserbaelementua SET idErreserba = ?, idEspazioa = ? WHERE idErreserbaElementua = ?";

        try (Connection conn = DBKonexioa.konexioaEgin();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            mapErreserbaElementuaToPreparedStatement(erreserbaElementua, ps);
            ps.setInt(3, erreserbaElementua.getIdErreserba());  // Establecemos el ID para la actualización
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
    }

    // Método auxiliar para mapear un Producto a PreparedStatement
    private void mapErreserbaElementuaToPreparedStatement(ErreserbaHistorikoa erreserbaElementua, PreparedStatement ps) throws SQLException {
        ps.setInt(1, erreserbaElementua.getIdErreserba());
        ps.setInt(2, erreserbaElementua.getIdEspazioa());

    }*/
}


