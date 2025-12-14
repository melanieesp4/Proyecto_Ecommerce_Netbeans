package ecommerce_proyect.Controller;

import ecommerce_proyect.DAO.ImagenProductoDao;
import ecommerce_proyect.DAO.ProductosDao;
import ecommerce_proyect.Model.ImagenProductoModel;
import ecommerce_proyect.Model.ProductosModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/DetalleProducto")
public class DetalleProductoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");

        if (id == null) {
            response.sendRedirect("HomeController");
            return;
        }

        int idProducto;
        try {
            idProducto = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            response.sendRedirect("HomeController");
            return;
        }

        ProductosDao productoDAO = new ProductosDao();
        ImagenProductoDao imagenDAO = new ImagenProductoDao();

        ProductosModel producto = productoDAO.obtenerProductoPorId(idProducto);

        if (producto == null) {
            response.sendRedirect("HomeController");
            return;
        }

        List<ImagenProductoModel> imagenes =
                imagenDAO.obtenerImagenesPorProducto(idProducto);

        List<ProductosModel> similares =
                productoDAO.obtenerProductosInteresantes(
                        producto.getProdCategoria(),
                        producto.getProdId()
                );

        request.setAttribute("producto", producto);
        request.setAttribute("imagenes", imagenes);
        request.setAttribute("similares", similares);

        request.getRequestDispatcher("/Views/Public/detalleProducto.jsp")
                .forward(request, response);
    }
}
