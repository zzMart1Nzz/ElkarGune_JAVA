package metodoak;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBKonexioa {
    private static final String ERABILTZAILEA = "java";
    private static final String PASAHITZA = "1mg3";
    private static final String DB = "elkargune";
    private static final String URL = "jdbc:mysql://172.16.237.119:3306/" + DB + "?useSSL=false&serverTimezone=UTC";

    private static Connection conn = null;

    // Constructor privado para evitar instanciación
    private DBKonexioa() {}

    // Método para obtener una conexión
    public static Connection konexioaEgin() {
        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(URL, ERABILTZAILEA, PASAHITZA);
                System.out.println("Datu-basearekin konektatuta.");
            }
        } catch (SQLException e) {
            System.out.println("Datu-basearekin konektatzea huts egin du!");
            e.printStackTrace();
        }
        return conn;
    }

    // Método para cerrar la conexión
    public static void itxiKonexioa() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Datu-basearen konexioa itxi da.");
            }
        } catch (SQLException e) {
            System.out.println("Errorea konexioa ixtean.");
            e.printStackTrace();
        }
    }
}
