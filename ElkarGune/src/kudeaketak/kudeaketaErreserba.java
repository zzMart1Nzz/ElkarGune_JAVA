package kudeaketak;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import metodoak.DBKonexioa;
import objetuak.Erreserba;

public class kudeaketaErreserba {

	public List<Erreserba> lortuErreserba() {
	    List<Erreserba> lista = new ArrayList<>();
	    String sql = "SELECT idErreserba, idBazkidea, mota, data " +
	                 "FROM erreserba " +
	                 "WHERE data >= CURDATE() " +
	                 "ORDER BY idErreserba ASC";

	    try (Connection conn = DBKonexioa.konexioaEgin();
	         PreparedStatement pst = conn.prepareStatement(sql);
	         ResultSet rs = pst.executeQuery()) {

	        while (rs.next()) {
	            Erreserba e = mapResultSetToErreserba(rs);
	            lista.add(e);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return lista;
	}


    private Erreserba mapResultSetToErreserba(ResultSet rs) throws SQLException {
        return new Erreserba(
            rs.getInt("idErreserba"),
            rs.getInt("idBazkidea"),
            rs.getBoolean("mota"),
            rs.getTimestamp("data")
        );
    }

    public void sortuErreserba(Erreserba erreserba) {
        String sql = "INSERT INTO erreserba ( idBazkidea, mota) VALUES ( ?, ?)";

        try (Connection conn = DBKonexioa.konexioaEgin();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            mapErreserbaToPreparedStatement(erreserba, ps);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eguneratuErreserba(Erreserba erreserba) {
        String sql = "UPDATE erreserba SET  idBazkidea = ?, mota = ? WHERE idErreserba = ?";

        try (Connection conn = DBKonexioa.konexioaEgin();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            mapErreserbaToPreparedStatement(erreserba, ps);
            ps.setInt(3, erreserba.getIdErreserba());  // Establecemos el ID para la actualización
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para eliminar un producto de la base de datos
    public boolean ezabatuErreserba(int idErreserba) {
        String sqlBorrarElementos = "DELETE FROM erreserbaelementua WHERE idErreserba = ?";
        String sqlBorrarErreserba = "DELETE FROM erreserba WHERE idErreserba = ?";

        try (Connection conn = DBKonexioa.konexioaEgin()) {
            conn.setAutoCommit(false);  

            try (PreparedStatement psElementos = conn.prepareStatement(sqlBorrarElementos);
                 PreparedStatement psErreserba = conn.prepareStatement(sqlBorrarErreserba)) {

                // Eliminar primero los registros en erreserbaelementua
                psElementos.setInt(1, idErreserba);
                psElementos.executeUpdate();

                // Luego eliminar la reserva
                psErreserba.setInt(1, idErreserba);
                psErreserba.executeUpdate();

                conn.commit();  // Confirmar la transacción
                return true;

            } catch (SQLException e) {
                conn.rollback();  // Deshacer cambios en caso de error
                e.printStackTrace();
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    // Método auxiliar para mapear un Producto a PreparedStatement
    private void mapErreserbaToPreparedStatement(Erreserba erreserba, PreparedStatement ps) throws SQLException {
        ps.setInt(1, erreserba.getIdBazkidea());
        ps.setBoolean(2, erreserba.isMota());

    }
}



