
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
public class ProductosDao {
    Connectionbd bdc = Connectionbd.getInstancia();
    Connection con = bdc.obtenerConexion();
    
    //Listar todos los productos
    public List<ProductosModel> listar() {
        List<ProductosModel> productos = new ArrayList<>();
        String sql = "SELECT prodId, prodNombre, prodImagen, prodDescripcion, prodPrecio, prodCantidad, prodCategoria FROM productos";
        try (PreparedStatement ps = con.prepareStatement(sql); 
                ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                ProductosModel p = new ProductosModel();
                p.setProdId(rs.getInt("prodId"));
                p.setProdNombre(rs.getString("prodNombre"));
                p.setProdImagen(rs.getString("prodImagen"));
                p.setProdDescripcion(rs.getString("prodDescripcion"));
                p.setProdPrecio(rs.getDouble("prodPrecio"));
                p.setProdCantidad(rs.getInt("prodCantidad"));
                p.setProdCategoria(rs.getInt("prodCategoria"));
                productos.add(p);
            }
        } catch (SQLException e) {
           throw new RuntimeException("Error al listar productos", e);

        }
        return productos;
    }
    
    //Obtener productos por ID    
     public ProductosModel obtenerProductoPorId(int productoId) {
        ProductosModel producto = null;
        String sql = "SELECT prodId, prodNombre, prodImagen, prodDescripcion, prodPrecio, prodCantidad, prodCategoria FROM productos WHERE prodId = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, productoId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    producto = new ProductosModel();
                    producto.setProdId(rs.getInt("prodId"));
                    producto.setProdNombre(rs.getString("prodNombre"));
                    producto.setProdImagen(rs.getString("prodImagen"));
                    producto.setProdDescripcion(rs.getString("prodDescripcion"));
                    producto.setProdPrecio(rs.getDouble("prodPrecio"));
                    producto.setProdCantidad(rs.getInt("prodCantidad"));
                    producto.setProdCategoria(rs.getInt("prodCategoria"));
                }
            }
        } catch (SQLException e) {
             throw new RuntimeException("Problemas al cargar el producto con id " + productoId, e);
 
        }
        return producto;
    }
     
     
     //Listar productos por categorias
     public List<ProductosModel> listarPorCategoria(int catId) {
        List<ProductosModel> productos = new ArrayList<>();
        String sql = "SELECT prodId, prodNombre, prodImagen, prodDescripcion, prodPrecio, prodCantidad, prodCategoria FROM productos WHERE prodCategoria = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1,catId); 
                try(ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ProductosModel p = new ProductosModel();
                p.setProdId(rs.getInt(1));
                p.setProdNombre(rs.getString(2));
                p.setProdImagen(rs.getString(3));
                p.setProdDescripcion(rs.getString(4));
                p.setProdPrecio(rs.getDouble(5));
                productos.add(p);
                }
            }
        } catch (SQLException e) {
           e.printStackTrace(); // Manejo de excepciones
        }
        return productos;
    }
     
     
     // Listar productos por búsqueda (nombre)
      public List<ProductosModel> listarPorBusqueda(String busqueda) {
         List<ProductosModel> productos = new ArrayList<>();
        String sql = "SELECT prodId, prodNombre, prodImagen, prodDescripcion, prodPrecio, prodCantidad, prodCategoria FROM productos WHERE prodNombre ILIKE ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + busqueda + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ProductosModel p = new ProductosModel();
                    p.setProdId(rs.getInt("prodId"));
                    p.setProdNombre(rs.getString("prodNombre"));
                    p.setProdImagen(rs.getString("prodImagen"));
                    p.setProdDescripcion(rs.getString("prodDescripcion"));
                    p.setProdPrecio(rs.getDouble("prodPrecio"));
                    p.setProdCantidad(rs.getInt("prodCantidad"));
                    p.setProdCategoria(rs.getInt("prodCategoria"));
                    productos.add(p);
                }
            }
        } catch (SQLException e) {
          throw new RuntimeException("Error al buscar el producto " + busqueda, e);
        }
        return productos;
    }
      
      
      // Listar productos destacados
    public List<ProductosModel> listarDestacados(int limite) {
        List<ProductosModel> productos = new ArrayList<>();
        String sql = "SELECT prodId, prodNombre, prodImagen, prodDescripcion, prodPrecio, prodCantidad, prodCategoria " +
                     "FROM productos WHERE prodDestacado = TRUE ORDER BY prodId DESC LIMIT ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, limite);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ProductosModel p = new ProductosModel();
                    p.setProdId(rs.getInt("prodId"));
                    p.setProdNombre(rs.getString("prodNombre"));
                    p.setProdImagen(rs.getString("prodImagen"));
                    p.setProdDescripcion(rs.getString("prodDescripcion"));
                    p.setProdPrecio(rs.getDouble("prodPrecio"));
                    p.setProdCantidad(rs.getInt("prodCantidad"));
                    p.setProdCategoria(rs.getInt("prodCategoria"));
                    productos.add(p);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar productos destacados", e);
        }
        return productos;
    }

     
    
}
