package ecommerce_proyect.DAO;

import ecommerce_proyect.Connection.bd.Connectionbd;
import ecommerce_proyect.Model.CategoriaModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Melanie
 */
public class CategoriaDao {

    Connectionbd bdc = Connectionbd.getInstancia();
    Connection con = bdc.obtenerConexion();

    // Obtener una categoría por ID
    public String obtenerNombreCategoria(int catId) {
        String nombre = "";
        String sql = "SELECT catNombre FROM categorias WHERE catId = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, catId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    nombre = rs.getString("catNombre");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar la categoria " + catId, e);
        }
        return nombre;
    }

    // Listar todas las categorías (útil para menú lateral)
    public List<CategoriaModel> listarCategorias() {
        List<CategoriaModel> categorias = new ArrayList<>();

        String sql = "SELECT catId, catNombre FROM categorias";

        try (PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                CategoriaModel c = new CategoriaModel();
                c.setCatId(rs.getInt("catId"));
                c.setCatNombre(rs.getString("catNombre"));
                categorias.add(c);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar categorías", e);
        }

        return categorias;
    }

    // Obtener el ID de la categoria por su nombre
    public int obtenerIdPorNombre(String nombreCategoria) throws Exception {
        int categoriaId = 0;

        String sql = "SELECT catId FROM categorias WHERE catNombre = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {


            ps.setString(1, nombreCategoria);

            // 2️⃣ Luego ejecutas la consulta
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    categoriaId = rs.getInt("catId");
                }
            }
        }

        return categoriaId;
    }

}
