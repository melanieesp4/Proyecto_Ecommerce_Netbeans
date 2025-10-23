
package ecommerce_proyect.Controller;

import ecommerce_proyect.DAO.ProductosDao;
import ecommerce_proyect.DAO.CarritoDao;
import ecommerce_proyect.Model.ProductosModel;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author User
 */
@WebServlet(name = "HomeController", urlPatterns = {"/HomeController"})
public class HomeController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        
        /* TODO output your page here. You may use following sample code. */
        ProductosDao productoDAO = new ProductosDao();
        CarritoDao carritoDAO = new CarritoDao();

        // ---------------------------
        // Manejo de búsqueda
        // ---------------------------
        String busqueda = request.getParameter("busqueda");
        if (busqueda != null && !busqueda.isEmpty()) {
            List<ProductosModel> resultadosBusqueda = productoDAO.listarPorBusqueda(busqueda);
            request.setAttribute("resultadosBusqueda", resultadosBusqueda);

            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
            return; 
            }
         
         // ---------------------------
        // Manejo de agregar producto al carrito
        // ---------------------------
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("userId") != null) {

            String productoIdStr = request.getParameter("productoId");
            if (productoIdStr != null && !productoIdStr.isEmpty()) {
                int userId = Integer.parseInt(session.getAttribute("userId").toString());
                int productoId = Integer.parseInt(productoIdStr);

                // Obtener el producto por ID
                ProductosModel producto = productoDAO.obtenerProductoPorId(productoId);
                if (producto == null) {
                    request.setAttribute("mensajeError", "Producto no encontrado");
                } else {
                    // Insertar o actualizar en carrito
                    boolean insertado = carritoDAO.validarInsertarProducto(producto, userId);
                    if (insertado) {
                        request.setAttribute("mensajeExito", "Producto agregado correctamente al carrito");
                    } else {
                        request.setAttribute("mensajeError", "Error al agregar producto al carrito");
                    }
                }

                // Redirigir a la página principal con mensaje
                RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
                return;
            }
        }
        
        // ---------------------------
        // Mostrar productos destacados en el home
        // ---------------------------
        try {
            List<ProductosModel> productosDestacados = productoDAO.listarDestacados(6); 
            request.setAttribute("productosDestacados", productosDestacados);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensajeError", "Error al cargar productos destacados.");
        }
        
         // ---------------------------
        // Si no hay búsqueda ni productoId, solo mostrar la página principal
        // ---------------------------
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
