
package ecommerce_proyect.DAO;

import ecommerce_proyect.Connection.bd.Connectionbd;
import ecommerce_proyect.Model.ProductosModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class CarritoDao {
    Connectionbd bdc = Connectionbd.getInstancia();
    Connection con = bdc.obtenerConexion();
    
     // Listar productos en el carrito (activos o wishlist)
    public List<ProductosModel> listarCarrito(int userId, boolean wishlist) {
        List<ProductosModel> productos = new ArrayList<>();
        char estado = 'A';
        if(wishlist){
            estado = 'I';
        }
        String sql = "SELECT p.prodId, p.prodNombre, c.cartCantidad, p.prodImagen, p.prodDescripcion, p.prodPrecio "
                + "FROM carrito c "
                + "INNER JOIN productos p ON c.cartProdId = p.prodId "
                + "WHERE c.cartUserId  = ? AND c.estado = '" + estado + "'";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ProductosModel p = new ProductosModel();
                    p.setProdId(rs.getInt("prodId"));
                    p.setProdNombre(rs.getString("prodNombre"));
                    p.setProdCantidad(rs.getInt("cartCantidad"));
                    p.setProdImagen(rs.getString("prodImagen"));
                    p.setProdDescripcion(rs.getString("prodDescripcion"));
                    p.setProdPrecio(rs.getDouble("prodPrecio"));
                    productos.add(p);
                }
            }
        } catch (SQLException e) {
             throw new RuntimeException("Error al listar el carrito del usuario con ID " + userId, e);
        }
        return productos;
    }
    
    
    //Calcular total de productos en el carrito
    public int CalcularTotalProductos(int userId){
        int total = 0;
        String sql = "SELECT SUM(cartCantidad) AS suma_total " +
        "FROM carrito WHERE cartUserId = ? AND cartEstado = 'A'";
         try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    total = rs.getInt("suma_total");
                }
            }
        } catch (Exception e) {
           throw new RuntimeException("Error al calcular el total de productos del carrito del usuario " + userId, e);
        }
        return total;
    
    }

    
    //Insertar o actualizar producto en el carrito
    public boolean validarInsertarProducto(ProductosModel producto, int userId) {
        String consultaProducto = "SELECT COUNT(*) AS num_rows FROM carrito WHERE cartUserId = ? AND cartProdId = ?";
        String actualizarProducto = "UPDATE carrito SET cartCantidad = cartCantidad + 1 WHERE cartUserId = ? AND cartProdId = ?";
        String insertarProducto = "INSERT INTO carrito (cartUserId, cartProdId, cartNombre, cartDescripcion, cartPrecio, cartCantidad, cartSubtotal) VALUES (?, ?, ?, ?, ?, 1, ?)";

        try {
            boolean productoYaEnCarrito = false;
            // Verificar si el producto ya está en el carrito para el usuario especificado
            try (PreparedStatement ps = con.prepareStatement(consultaProducto)) {
                ps.setInt(1, userId);
                ps.setInt(2, producto.getProdId());
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        int num_rows = rs.getInt("num_rows");
                        if (num_rows > 0) {
                            productoYaEnCarrito = true;
                        }
                    }
                }
            }

            // Si el producto ya está en el carrito, actualizar la cantidad
            if (productoYaEnCarrito) {
                try (PreparedStatement actualizarStmt = con.prepareStatement(actualizarProducto)) {
                    actualizarStmt.setInt(1, 1);
                    actualizarStmt.setInt(2, userId);
                    actualizarStmt.setInt(3, producto.getProdId());
                    actualizarStmt.executeUpdate();
                }
            } else {
                
                // Si el producto no está en el carrito, insertarlo
                try (PreparedStatement insertarStmt = con.prepareStatement(insertarProducto)) {
                    insertarStmt.setInt(1, userId);
                    insertarStmt.setInt(2, producto.getProdId());
                    insertarStmt.setString(3, producto.getProdNombre());
                    insertarStmt.setString(4, producto.getProdDescripcion());
                    insertarStmt.setDouble(5, producto.getProdPrecio());
                    insertarStmt.setDouble(6, producto.getProdPrecio());
                    insertarStmt.executeUpdate();
                }
            }

            return true;
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar o actualizar producto en el carrito", e);
            
        }
    }
    
    
    //Eliminar producto del carrito
    public boolean eliminarProductoDelCarrito(int productoId, int userId) {
        String sql = "DELETE FROM carrito WHERE cartUserId = ? AND cartProdId = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, productoId);
            int filasEliminadas = ps.executeUpdate();
            // Si al menos una fila fue eliminada, significa que el producto fue eliminado correctamente
            return filasEliminadas > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar producto del carrito", e);
        }
    }
    
    // Cambiar estado (activo ↔ wishlist)
    public boolean cambiarEstadoProducto(int productoId, int userId, boolean forWishlist) {
        String estado = forWishlist ? "I" : "A";
        String sql = "UPDATE carrito SET cartEstado = ? WHERE cartUserId = ? AND cartProdId = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, estado);
            ps.setInt(2, userId);
            ps.setInt(3, productoId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("❌ Error al cambiar el estado del producto en el carrito", e);
        }
    }
    
}
