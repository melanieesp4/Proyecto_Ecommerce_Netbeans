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
        try (PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

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

    // Listar productos por categorías por ID
    public List<ProductosModel> listarPorCategoria(int categoriaId) {
        List<ProductosModel> productos = new ArrayList<>();
        String sql = "SELECT prodId, prodNombre, prodImagen, prodDescripcion, prodPrecio, prodCantidad, prodCategoria "
                + "FROM productos WHERE prodCategoria = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, categoriaId);

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
            e.printStackTrace();
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
        String sql = "SELECT prodId, prodNombre, prodImagen, prodDescripcion, prodPrecio, prodCantidad, prodCategoria "
                + "FROM productos WHERE prodDestacado = TRUE ORDER BY prodId DESC LIMIT ?";
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

    // Mostrar productos en ofertas
    public List<ProductosModel> listarOfertas() {
        List<ProductosModel> lista = new ArrayList<>();
        String sql = "SELECT * FROM productos WHERE es_oferta = TRUE ORDER BY prodDescuento DESC";

        try (
                PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ProductosModel prod = new ProductosModel();
                prod.setProdId(rs.getInt("prodId"));
                prod.setProdNombre(rs.getString("prodNombre"));
                prod.setProdImagen(rs.getString("prodImagen"));
                prod.setProdDescripcion(rs.getString("prodDescripcion"));
                prod.setProdPrecio(rs.getDouble("prodPrecio"));
                prod.setProdDescuento(rs.getDouble("prodDescuento"));
                prod.setEsOferta(rs.getBoolean("es_oferta"));
                lista.add(prod);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar ofertas: " + e.getMessage());
        }
        System.out.println("Ofertas encontradas: " + lista.size());

        return lista;
    }

    // Obtener productos que pueden interesar al usuario  
    public List<ProductosModel> obtenerProductosInteresantes(int categoriaId, int productoIdActual) {
        List<ProductosModel> lista = new ArrayList<>();
        String sql = "SELECT * FROM productos WHERE prodcategoria = ? ORDER BY RANDOM() LIMIT 6";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, categoriaId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    if (rs.getInt("prodid") != productoIdActual) { // opcional, para excluir el producto actual
                        ProductosModel prod = new ProductosModel();
                        prod.setProdId(rs.getInt("prodid"));
                        prod.setProdNombre(rs.getString("prodnombre"));
                        prod.setProdImagen(rs.getString("prodimagen"));
                        prod.setProdDescripcion(rs.getString("proddescripcion"));
                        prod.setProdPrecio(rs.getDouble("prodprecio"));
                        prod.setProdDescuento(rs.getDouble("proddescuento"));
                        prod.setProdCategoria(rs.getInt("prodcategoria"));
                        lista.add(prod);
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener productos interesantes: " + e.getMessage());
        }

        return lista;
    }

    //Metodo para paginacion
    public int contarProductos(String whereClause, Object... params) {
        int total = 0;

        String sql = "SELECT COUNT(*) FROM productos " + whereClause;

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    total = rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al contar productos", e);
        }

        return total;
    }

    public List<ProductosModel> listarProductosPaginados(
            String whereClause,
            int limit,
            int offset,
            Object... params) {

        List<ProductosModel> productos = new ArrayList<>();

        String sql = "SELECT prodId, prodNombre, prodImagen, prodPrecio, prodDescuento "
                + "FROM productos "
                + (whereClause != null ? whereClause : "")
                + " ORDER BY prodId DESC "
                + " LIMIT ? OFFSET ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            int index = 1;

            if (params != null) {
                for (Object param : params) {
                    ps.setObject(index++, param);
                }
            }

            ps.setInt(index++, limit);
            ps.setInt(index, offset);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ProductosModel p = new ProductosModel();
                    p.setProdId(rs.getInt("prodId"));
                    p.setProdNombre(rs.getString("prodNombre"));
                    p.setProdImagen(rs.getString("prodImagen"));
                    p.setProdPrecio(rs.getDouble("prodPrecio"));
                    p.setProdDescuento(rs.getDouble("prodDescuento"));
                    productos.add(p);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al listar productos paginados", e);
        }

        return productos;
    }

    // ofertas paginadas
    public List<ProductosModel> listarOfertasPaginadas(int limit, int offset) {
        return listarProductosPaginados(
                "WHERE prodDescuento > 0",
                limit,
                offset
        );
    }

//Contar ofertas
    public int contarOfertas() {
        return contarProductos("WHERE prodDescuento > 0");
    }

    //Categoria paginada
    public List<ProductosModel> listarPorCategoriaPaginado(
            int categoriaId,
            int limit,
            int offset) {

        return listarProductosPaginados(
                "WHERE prodCategoria = ?",
                limit,
                offset,
                categoriaId
        );
    }

    //Contar los productos por categoria 
    public int contarPorCategoria(int categoriaId) {
        return contarProductos(
                "WHERE prodCategoria = ?",
                categoriaId
        );
    }
    
    

}
