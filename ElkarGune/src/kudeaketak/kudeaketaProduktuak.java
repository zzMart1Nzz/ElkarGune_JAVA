package kudeaketak;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import metodoak.DBKonexioa;  // Importamos la clase de conexión DBKonexioa
import objetuak.Produktua;

public class kudeaketaProduktuak {

	public List<Produktua> lortuProduktua() {
	    List<Produktua> produktuak = new ArrayList<>();
	    
	    try (Connection con = DBKonexioa.konexioaEgin()) {
	        String sql = "SELECT p.idProduktua, p.idProduktuMota, k.kategoria, p.izena, p.erosketaPrezioa, p.salmentaPrezioa, p.stock, p.idHornitzailea " +
	                     "FROM produktua p " +
	                     "JOIN produktumota k ON p.idProduktuMota = k.idProduktuMota " +
	                     "ORDER BY p.idProduktua ASC";

	        try (PreparedStatement stmt = con.prepareStatement(sql);
	             ResultSet rs = stmt.executeQuery()) {

	            while (rs.next()) {
	                Produktua produktua = new Produktua(
	                    rs.getInt("idProduktua"),
	                    rs.getInt("idProduktuMota"),  // Obtenemos el idProduktuMota desde la consulta
	                    rs.getString("izena"),
	                    rs.getDouble("erosketaPrezioa"),
	                    rs.getDouble("salmentaPrezioa"),
	                    rs.getInt("stock"),
	                    rs.getInt("idHornitzailea"),
	                    rs.getString("kategoria")
	                );
	                produktuak.add(produktua);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return produktuak;
	}

    
    // Método para insertar un producto
    public void sortuProduktua(Produktua produktua) {
        // Conexión a la base de datos usando la clase DBKonexioa
        try (Connection con = DBKonexioa.konexioaEgin()) {  // Usamos DBKonexioa para obtener la conexión
            String sql = "INSERT INTO produktua (idProduktuMota, izena, erosketaPrezioa, salmentaPrezioa, stock, idHornitzailea) " +
                         "VALUES (?, ?, ?, ?, ?, ?)";

            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setInt(1, produktua.getIdProduktuMota());
                stmt.setString(2, produktua.getIzena());
                stmt.setDouble(3, produktua.getErosketaPrezioa());
                stmt.setDouble(4, produktua.getSalmentaPrezioa());
                stmt.setInt(5, produktua.getStock());
                stmt.setInt(6, produktua.getIdHornitzailea());
                
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para obtener un producto por su ID
    public Produktua getProduktuaById(int idProduktua) {
        Produktua produktua = null;
        
        // Conexión a la base de datos usando la clase DBKonexioa
        try (Connection con = DBKonexioa.konexioaEgin()) {
            String sql = "SELECT p.idProduktua, k.kategoria, p.izena, p.erosketaPrezioa, p.salmentaPrezioa, p.stock, p.idHornitzailea " +
                         "FROM produktua p " +
                         "JOIN produktumota k ON p.idProduktuMota = k.idProduktuMota " +
                         "WHERE p.idProduktua = ?";

            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setInt(1, idProduktua);
                
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        // Creamos el producto con la categoría
                        produktua = new Produktua(
                            rs.getInt("idProduktua"),
                            rs.getInt("idProduktuMota"),
                            rs.getString("izena"),
                            rs.getDouble("erosketaPrezioa"),
                            rs.getDouble("salmentaPrezioa"),
                            rs.getInt("stock"),
                            rs.getInt("idHornitzailea"),
                            rs.getString("kategoria")  // Obtenemos la categoría
                        );
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return produktua;
    }

    // Método para actualizar un producto
    public void eguneratuProduktua(Produktua produktua) {
        // Conexión a la base de datos usando la clase DBKonexioa
        try (Connection con = DBKonexioa.konexioaEgin()) {  // Usamos DBKonexioa para obtener la conexión
            String sql = "UPDATE produktua SET idProduktuMota = ?, izena = ?, erosketaPrezioa = ?, salmentaPrezioa = ?, stock = ?, idHornitzailea = ? " +
                         "WHERE idProduktua = ?";

            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setInt(1, produktua.getIdProduktuMota());
                stmt.setString(2, produktua.getIzena());
                stmt.setDouble(3, produktua.getErosketaPrezioa());
                stmt.setDouble(4, produktua.getSalmentaPrezioa());
                stmt.setInt(5, produktua.getStock());
                stmt.setInt(6, produktua.getIdHornitzailea());
                stmt.setInt(7, produktua.getIdProduktua());
                
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para eliminar un producto
    public boolean ezabatuProduktua(int idProduktua) {
        String sql = "DELETE FROM produktua WHERE idProduktua = ?";
        try (Connection conn = DBKonexioa.konexioaEgin();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, idProduktua);
            int rowsAffected = ps.executeUpdate();
            
            return rowsAffected > 0;  // Si se afectaron filas, la eliminación fue exitosa
        } catch (SQLException e) {
            e.printStackTrace();
            return false;  // Si ocurre un error, la eliminación no fue exitosa
        }
    }
}