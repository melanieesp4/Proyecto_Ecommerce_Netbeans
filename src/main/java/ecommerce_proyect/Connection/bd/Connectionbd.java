/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ecommerce_proyect.Connection.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class Connectionbd {
    private static Connectionbd instancia;
    private Connection conexion;
    
    private Connectionbd() {
    String usuario = System.getenv("DB_USER");
    String contra = System.getenv("DB_PASSWORD");
    String bd = System.getenv("DB_NAME");
    String ip = System.getenv("DB_HOST");
    String puerto = System.getenv("DB_PORT");

    String url = "jdbc:postgresql://" + ip + ":" + puerto + "/" + bd + "?sslmode=require";

    try {
        // 🔹 Forzar carga del driver
        Class.forName("org.postgresql.Driver");
        conexion = DriverManager.getConnection(url, usuario, contra);

        // 🔹 Verificación adicional
        //if (conexion != null && !conexion.isClosed()) {
          //  JOptionPane.showMessageDialog(null, "✅ Conexión establecida correctamente con la base de datos en Supabase.");
            //System.out.println("✅ Conexión exitosa a la base de datos: " + bd);
        //} else {
        //    JOptionPane.showMessageDialog(null, "⚠️ Conexión nula o cerrada. Verifica credenciales o servidor.");
        //}

    } catch (ClassNotFoundException e) {
        JOptionPane.showMessageDialog(null, "No se encontró el driver JDBC de PostgreSQL.\n" + e.getMessage());
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "No se pudo conectar a la base de datos.\nError: " + e.getMessage());
    }
}

    public static Connectionbd getInstancia() {
        if (instancia == null) {
            instancia = new Connectionbd();
        }
        return instancia;
    }

    public Connection getConexion() {
        return conexion;
    }

    public Connection obtenerConexion() {
        return conexion;
    }

    public void cerrarConexion() {
        try {
            if (conexion != null) {
                conexion.close();
                // JOptionPane.showMessageDialog(null, "¡Conexión a la base de datos cerrada!");
            }
        } catch (SQLException e) {
            // JOptionPane.showMessageDialog(null, "Error al cerrar la conexión: " + e.toString());
        }
    }

    public java.sql.Statement createStatement() throws SQLException {
        return this.conexion.createStatement();
    }
    
}
