package kudeaketak;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import metodoak.DBKonexioa;
import objetuak.Mezuak;

public class kudeaketaMezuak {

    public List<Mezuak> lortuMezuak() {
        List<Mezuak> lista = new ArrayList<>();
        String sql = "SELECT idMezua, idBazkidea, mezua, data " +
                     "FROM mezuak ORDER BY idMezua ASC";

        try (Connection conn = DBKonexioa.konexioaEgin();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Mezuak m = mapResultSetToMezuak(rs);
                lista.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    private Mezuak mapResultSetToMezuak(ResultSet rs) throws SQLException {
        return new Mezuak(
            rs.getInt("idMezua"),
            rs.getInt("idBazkidea"),
            rs.getString("mezua"),
            rs.getTimestamp("data")
        );
    }

    public void sortuMezuak(Mezuak mezuak) {
        String sql = "INSERT INTO mezuak (idBazkidea, mezua) VALUES (?, ?)";

        try (Connection conn = DBKonexioa.konexioaEgin();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            mapMezuakToPreparedStatement(mezuak, ps);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eguneratuMezuak(Mezuak mezuak) {
        String sql = "UPDATE mezuak SET idBazkidea = ?, mezua = ? WHERE idMezua = ?";

        try (Connection conn = DBKonexioa.konexioaEgin();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            mapMezuakToPreparedStatement(mezuak, ps);
            ps.setInt(3, mezuak.getIdMezua());  // Establecemos el ID para la actualización
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para eliminar un producto de la base de datos
    public boolean ezabatuMezuak(int idMezua) {
        String sql = "DELETE FROM mezuak WHERE idMezua = ?";

        try (Connection conn = DBKonexioa.konexioaEgin();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idMezua);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método auxiliar para mapear un Producto a PreparedStatement
    private void mapMezuakToPreparedStatement(Mezuak mezuak, PreparedStatement ps) throws SQLException {
    	ps.setInt(1, mezuak.getIdBazkidea());
        ps.setString(2, mezuak.getMezua());

    }
}



