package kudeaketak;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import metodoak.DBKonexioa;
import objetuak.Bazkidea;

public class kudeaketaBazkidea {

    public List<Bazkidea> lortuBazkidea() {
        List<Bazkidea> lista = new ArrayList<>();
        String sql = "SELECT idBazkidea, nan, izena, abizenak, erabiltzailea, pasahitza, " +
                "telefonoZkia, postaElektronikoa, kontuKorronteZkia, admin, inskripzioData " +
                "FROM bazkidea ORDER BY idBazkidea ASC";


        try (Connection conn = DBKonexioa.konexioaEgin();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Bazkidea b = mapResultSetToBazkidea(rs);
                lista.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    private Bazkidea mapResultSetToBazkidea(ResultSet rs) throws SQLException {
        return new Bazkidea(
            rs.getInt("idBazkidea"),
            rs.getString("nan"),
            rs.getString("izena"),
            rs.getString("abizenak"),
            rs.getString("erabiltzailea"),
            rs.getString("pasahitza"),
            rs.getString("telefonoZkia"),
            rs.getString("postaElektronikoa"),
            rs.getString("kontuKorronteZkia"),
            rs.getBoolean("admin"),
            rs.getTimestamp("inskripzioData")
        );
    }

    public void sortuBazkidea(Bazkidea bazkidea) {
        String sql = "INSERT INTO bazkidea (nan, izena, abizenak, erabiltzailea, pasahitza, telefonoZkia, postaElektronikoa, kontuKorronteZkia, admin) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBKonexioa.konexioaEgin();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            mapBazkideaToPreparedStatement(bazkidea, ps);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eguneratuBazkidea(Bazkidea bazkidea) {
    	String sql = "UPDATE bazkidea SET nan = ?, izena = ?, abizenak = ?, erabiltzailea = ?, " +
                "pasahitza = ?, telefonoZkia = ?, postaElektronikoa = ?, kontuKorronteZkia = ?, admin = ? " +
                "WHERE idBazkidea = ?";


        try (Connection conn = DBKonexioa.konexioaEgin();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            mapBazkideaToPreparedStatement(bazkidea, ps);
            ps.setInt(10, bazkidea.getIdBazkidea());  // Establecemos el ID para la actualización
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para eliminar un producto de la base de datos
    public boolean ezabatuBazkidea(int idBazkidea) {
        String sql = "DELETE FROM bazkidea WHERE idBazkidea = ?";

        try (Connection conn = DBKonexioa.konexioaEgin();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idBazkidea);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método auxiliar para mapear un Producto a PreparedStatement
    private void mapBazkideaToPreparedStatement(Bazkidea bazkidea, PreparedStatement ps) throws SQLException {
        ps.setString(1, bazkidea.getNan());
        ps.setString(2, bazkidea.getIzena());
        ps.setString(3, bazkidea.getAbizena());
        ps.setString(4, bazkidea.getErabiltzailea());
        ps.setString(5, bazkidea.getPasahitza());
        ps.setString(6, bazkidea.getTelefonoZkia());
        ps.setString(7, bazkidea.getPostaElektronikoa());
        ps.setString(8, bazkidea.getKontuKorronteZkia());
        ps.setBoolean(9, bazkidea.isAdmin());

    }
}


