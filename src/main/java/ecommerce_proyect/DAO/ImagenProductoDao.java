
package ecommerce_proyect.DAO;

import ecommerce_proyect.Connection.bd.Connectionbd;
import ecommerce_proyect.Model.ImagenProductoModel;
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
public class ImagenProductoDao {
    Connectionbd bdc = Connectionbd.getInstancia();
    Connection con = bdc.obtenerConexion();
    
    // Obtener imágenes por ID de producto
    public List<ImagenProductoModel> obtenerImagenesPorProducto(int productoId) {
        List<ImagenProductoModel> imagenes = new ArrayList<>();
        String sql = "SELECT imgId, imgProductoId, imgUrl FROM imagenes_productos WHERE imgProductoId = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, productoId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ImagenProductoModel img = new ImagenProductoModel();
                    img.setImgId(rs.getInt("imgId"));
                    img.setImgProductoId(rs.getInt("imgProductoId"));
                    img.setImgUrl(rs.getString("imgUrl"));
                    imagenes.add(img);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return imagenes;
    }
}
