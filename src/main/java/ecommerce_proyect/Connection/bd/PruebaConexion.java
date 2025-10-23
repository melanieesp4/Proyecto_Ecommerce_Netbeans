

package ecommerce_proyect.Connection.bd;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author User
 */
public class PruebaConexion {
    public static void main(String[] args) {
        // Obtener la instancia de la conexión
        Connectionbd bdc = Connectionbd.getInstancia();
        Connection conn = bdc.obtenerConexion();

        if (conn != null) {
            System.out.println("Conexión establecida con éxito a la base de datos.");

            try {
                // Ejemplo opcional: listar tablas para comprobar conexión
                var stmt = conn.createStatement();
                var rs = stmt.executeQuery("SELECT table_name FROM information_schema.tables WHERE table_schema='public'");
                System.out.println("Tablas en la base de datos:");
                while (rs.next()) {
                    System.out.println("- " + rs.getString("table_name"));
                }
            } catch (SQLException e) {
                System.err.println("Error al ejecutar la prueba: " + e.getMessage());
            } finally {
                // Cerrar la conexión
                bdc.cerrarConexion();
                System.out.println("Conexión cerrada correctamente.");
            }
        } else {
            System.err.println("No se pudo establecer la conexión a la base de datos.");
        }
    }
}
