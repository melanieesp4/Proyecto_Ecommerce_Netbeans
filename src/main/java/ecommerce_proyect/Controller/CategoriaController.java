package ecommerce_proyect.Controller;

import ecommerce_proyect.DAO.CategoriaDao;
import ecommerce_proyect.DAO.ProductosDao;
import ecommerce_proyect.Model.ProductosModel;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author User
 */
@WebServlet(name = "CategoriaController", urlPatterns = {"/Categoria"})
public class CategoriaController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String categoriaNombre = request.getParameter("categoriaNombre");

        if (categoriaNombre == null || categoriaNombre.isEmpty()) {
            response.sendRedirect("HomeController");
            return;
        }
        categoriaNombre = categoriaNombre.trim();

        try {
            CategoriaDao categoriaDAO = new CategoriaDao();
            ProductosDao productoDAO = new ProductosDao();

            int categoriaId = categoriaDAO.obtenerIdPorNombre(categoriaNombre);


            int paginaActual = 1;
            int productosPorPagina = 9;

            if (request.getParameter("page") != null) {
                paginaActual = Integer.parseInt(request.getParameter("page"));
            }

            int offset = (paginaActual - 1) * productosPorPagina;


            List<ProductosModel> productos
                    = productoDAO.listarPorCategoriaPaginado(
                            categoriaId,
                            productosPorPagina,
                            offset
                    );


            int totalProductos = productoDAO.contarPorCategoria(categoriaId);

            int totalPaginas = (int) Math.ceil(
                    (double) totalProductos / productosPorPagina
            );


            request.setAttribute("productos", productos);
            request.setAttribute("categoria", categoriaNombre);

            request.setAttribute("paginaActual", paginaActual);
            request.setAttribute("totalPaginas", totalPaginas);
            request.setAttribute("totalProductos", totalProductos);
            request.setAttribute("productosPorPagina", productosPorPagina);
            request.setAttribute("offset", offset);

            request.getRequestDispatcher("/Views/Public/categoria.jsp")
                    .forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("HomeController");
        }
    }
}
