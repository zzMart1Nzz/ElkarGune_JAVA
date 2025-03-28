package metodoak;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import objetuak.Bazkidea;

public class LoginaMetodoa {

    public Bazkidea lortuErabiltzailea(Bazkidea bazkidea) {
        Bazkidea aurkitua = null;
        String sql = "SELECT * FROM bazkidea WHERE erabiltzailea = ? AND pasahitza = ?";

        try (Connection conn = DBKonexioa.konexioaEgin();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, bazkidea.getErabiltzailea());
            pstmt.setString(2, bazkidea.getPasahitza());
            System.out.println("SQL exekutatzea: " + pstmt.toString());

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    aurkitua = new Bazkidea();
                    aurkitua.setErabiltzailea(rs.getString("erabiltzailea"));
                    aurkitua.setPasahitza(rs.getString("pasahitza"));
                    aurkitua.setIzena(rs.getString("izena"));
                    aurkitua.setAdmin(rs.getBoolean("admin"));
                    System.out.println("Erabiltzailea aurkitu da: " + aurkitua.getIzena());
                } else {
                    System.out.println("Erabiltzailea ez da aurkitu.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Errorea erabiltzailea lortzean: " + e.getMessage());
            e.printStackTrace();
        }

        return aurkitua;
    }
}
