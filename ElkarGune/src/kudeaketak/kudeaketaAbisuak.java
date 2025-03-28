package kudeaketak;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import metodoak.DBKonexioa;
import objetuak.Abisuak;

public class kudeaketaAbisuak {

    public List<Abisuak> lortuAbisuak() {
        List<Abisuak> lista = new ArrayList<>();
        String sql = "SELECT idAbisuak, mezua, data " +
                     "FROM abisuak ORDER BY idAbisuak ASC";

        try (Connection conn = DBKonexioa.konexioaEgin();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Abisuak a = mapResultSetToAbisuak(rs);
                lista.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    private Abisuak mapResultSetToAbisuak(ResultSet rs) throws SQLException {
        return new Abisuak(
            rs.getInt("idAbisuak"),
            rs.getString("mezua"),
            rs.getTimestamp("data")
        );
    }

    public void sortuAbisua(Abisuak abisuak) {
        String sql = "INSERT INTO abisuak (mezua) VALUES (?)";

        try (Connection conn = DBKonexioa.konexioaEgin();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            mapAbisuakToPreparedStatement(abisuak, ps);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eguneratuAbisua(Abisuak abisuak) {
        String sql = "UPDATE abisuak SET mezua = ? WHERE idAbisuak = ?";

        try (Connection conn = DBKonexioa.konexioaEgin();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            mapAbisuakToPreparedStatement(abisuak, ps);
            ps.setInt(2, abisuak.getIdAbisuak());  // Establecemos el ID para la actualización
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para eliminar un producto de la base de datos
    public boolean ezabatuAbisuak(int idAbisuak) {
        String sql = "DELETE FROM abisuak WHERE idAbisuak = ?";

        try (Connection conn = DBKonexioa.konexioaEgin();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idAbisuak);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método auxiliar para mapear un Producto a PreparedStatement
    private void mapAbisuakToPreparedStatement(Abisuak abisuak, PreparedStatement ps) throws SQLException {
        ps.setString(1, abisuak.getMezua());

    }
}


