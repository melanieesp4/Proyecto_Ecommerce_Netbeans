<%@ page import="ecommerce_proyect.Model.ProductosModel" %>
<%@ page import="ecommerce_proyect.DAO.ProductosDao" %>
<%@ page import="java.util.List" %>

<%@include file="PageComponents/header.jsp" %>

<main>
    <div class="banner">
        <h4 class="compra">Compra Online</h4>
        <h2>Recibe de hasta el 50% de descuento</h2>
        <h1>En todos los productos</h1>
        <p>¡No te puedes perder esta oferta!</p>
        <button>COMPRA AHORA</button>
    </div>

    <section class="productos-destacados">
        <%
            String txtB;
            ProductosDao productoDAO  = new ProductosDao();
            List<ProductosModel> productos;
            if (request.getAttribute("resultadosBusqueda") == null || request.getParameter("busqueda") == null || request.getParameter("busqueda").isEmpty()) {
                txtB = "Productos destacados";
                productos = productoDAO.listar();
            } else {
                txtB = "Resultados de búsqueda para: " + request.getParameter("busqueda");
                productos = (List<ProductosModel>) request.getAttribute("resultadosBusqueda");
            }
        %>

        <h1><%= txtB %></h1>
        <div class="sections">
            <% for (ProductosModel producto : productos) { %>
            <div class="producto" onclick="verDetalle(<%= producto.getProdId() %>)">
                <img id="imgproducto" src="<%= producto.getProdImagen() %>" alt="<%= producto.getProdNombre() %>">
                <h3><%= producto.getProdNombre() %></h3>
                <br>
                <h4 class="price"><%= "$" + producto.getProdPrecio() %></h4>
                <!-- Botón para agregar al carrito -->
                <button onclick="agregarAlCarrito(<%= producto.getProdId() %>, event); actualizarContadorCarrito();"><i class="fa-solid fa-cart-shopping buy-icon"></i></button>
            </div>
            <% } %>
        </div>
    </section>
    <!-- Script JavaScript para manejar el evento onclick del botón -->
    <script>
        function agregarAlCarrito(idProducto, event) {

            event.stopPropagation();

            // Crear una nueva instancia de XMLHttpRequest
            var xhr = new XMLHttpRequest();

            // Configurar la solicitud AJAX
            xhr.open("GET", "HomeController?productoId=" + idProducto, true);
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

            // Definir la función de respuesta
            xhr.onload = function () {
                if (xhr.status === 200) {
                    // La solicitud fue exitosa
                    console.log('s');
                    // Aqui se puede redirigir al usuario a una página de confirmación o actualizar la UI aquí si es necesario
                } else {
                    // Hubo un error en la solicitud
                    console.error('E');
                }
            };

            // Enviar la solicitud con el ID del producto como parámetro
            xhr.send();
        }

        function verDetalle(idProducto) {
            window.location.href = "detalle.jsp?item=" + idProducto;
        }
    </script>
</main>

<%@include file="PageComponents/footer.jsp" %>
