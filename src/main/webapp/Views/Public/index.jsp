<%@ page import="ecommerce_proyect.Model.ProductosModel" %>
<%@ page import="ecommerce_proyect.DAO.ProductosDao" %>
<%@ page import="java.util.List" %>

<%@include file="../Components/header.jsp" %>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<main>
    <div class="banner">
        <h4 class="compra">Compra Online</h4>
        <h2>Recibe hasta el 50% de descuento</h2>
        <h1>En todos los productos</h1>
        <p>¡No te puedes perder esta oferta!</p>
        <button>COMPRA AHORA</button>
    </div>


    <!-- SECCION DE CATEGORIAS POPULARES -->
    <section class="categorias-destacadas">
        <h2>Categorías populares</h2>

        <div class="categorias-grid">

            <div class="categoria" onclick="verCategoria('Electrónica')">
                <img src="img/categoria-tecnologia.jpg" alt="Tecnología">
                <h4>Tecnología</h4>
            </div>

            <div class="categoria" onclick="verCategoria('Hogar')">
                <img src="img/categoria-hogar.jpg" alt="Hogar">
                <h4>Hogar</h4>
            </div>

            <div class="categoria" onclick="verCategoria('Ropa')">
                <img src="img/categoria-moda.jpg" alt="Moda">
                <h4>Moda</h4>
            </div>

            <div class="categoria" onclick="verCategoria('Juguetes')">
                <img src="img/categoria-juguetes.png" alt="Juguetes">
                <h4>Juguetes</h4>
            </div>

        </div>
    </section>

    <!-- SECCION DE PRODUCTOS DESTACADOS -->
    <section class="productos-destacados">
        <%
            String txtB;
            ProductosDao productoDAO = new ProductosDao();
            List<ProductosModel> productos;

            if (request.getAttribute("resultadosBusqueda") == null
                    || request.getParameter("busqueda") == null
                    || request.getParameter("busqueda").isEmpty()) {

                txtB = "Productos destacados";
                productos = productoDAO.listarDestacados(6);

            } else {
                txtB = "Resultados de búsqueda para: " + request.getParameter("busqueda");
                productos = (List<ProductosModel>) request.getAttribute("resultadosBusqueda");
            }


        %>

        <div class="destacados-header">
            <h1><%= txtB%></h1>

            <span class="page-indicator">
                Página <span id="pagina-actual">1</span>
                de <span id="total-paginas">1</span>
            </span>
        </div>

        <div class="carousel-container">

            <button class="nav-btn left" onclick="moveSlide(-1)">&#10094;</button>
            <div class="carousel-viewport">
                <div class="carousel-track" id="carousel-track">

                    <% for (ProductosModel producto : productos) {%>

                    <div class="producto product-card"
                         onclick="verDetalle(<%= producto.getProdId()%>)">

                        <img src="<%= producto.getProdImagen()%>"
                             alt="<%= producto.getProdNombre()%>">

                        <h3 class="product-title"> <%= producto.getProdNombre()%></h3>

                        <div class="product-rating">&#9733;&#9733;&#9733;&#9733;&#9733;</div>

                        <%
                            java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
                            String precioFormateado = df.format(producto.getProdPrecio());
                            String entero = precioFormateado.substring(0, precioFormateado.indexOf("."));
                            String decimal = precioFormateado.substring(precioFormateado.indexOf(".") + 1);
                        %>

                        <div class="product-price">
                            <span class="price-currency">US$</span>
                            <span class="price-integer"><%= entero%></span>
                            <span class="price-decimal"><%= decimal%></span>
                        </div>

                        <p class="product-shipping">
                            Envío gratis en pedidos superiores a $30 enviados por Blinker.
                        </p>

                        <button class="product-btn-cart"
                                onclick="event.stopPropagation();
                                        agregarAlCarrito(<%= producto.getProdId()%>, event);
                                        actualizarContadorCarrito();">
                            Agregar al carrito
                        </button>

                    </div>

                    <% } %>

                </div>
            </div>

            <button class="nav-btn right" onclick="moveSlide(1)">&#10095;</button>
        </div>
    </section>


    <!-- BENEFICIOS DE COMPRAR AQUI -->
    <section class="beneficios">
        <div class="beneficio">
            <i class="fa-solid fa-truck-fast"></i>
            <h4>Envíos rápidos</h4>
            <p>Entrega en 24-48 horas en todo el país.</p>
        </div>
        <div class="beneficio">
            <i class="fa-solid fa-lock"></i>
            <h4>Pagos seguros</h4>
            <p>Tus transacciones están protegidas con cifrado SSL.</p>
        </div>
        <div class="beneficio">
            <i class="fa-solid fa-rotate-left"></i>
            <h4>Devoluciones fáciles</h4>
            <p>Hasta 30 días para cambios o devoluciones.</p>
        </div>
    </section>

    <%
        int paginaActual = 1;
        int productosPorPagina = 10;

        if (request.getParameter("page") != null) {
            paginaActual = Integer.parseInt(request.getParameter("page"));
        }

        int offset = (paginaActual - 1) * productosPorPagina;

        List<ProductosModel> ofertas
                = productoDAO.listarOfertasPaginadas(productosPorPagina, offset);

        int totalProductos = productoDAO.contarOfertas();
        int totalPaginas
                = (int) Math.ceil((double) totalProductos / productosPorPagina);
    %>

    <!-- SECCIÓN OFERTAS DEL DÍA -->
    <section class="big-sales">
        <h2>Ofertas del día</h2>
        <div class="big-sales-grid">
            <%
                java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");

                for (ProductosModel prod : ofertas) {
                    double descuento = prod.getProdDescuento(); // %
                    double precioOriginal = prod.getProdPrecio();
                    double precioFinal = precioOriginal - (precioOriginal * descuento / 100);
            %>

            <div class="big-sale product-card" onclick="verDetalle(<%= prod.getProdId()%>)">
                <img class="product-image"  src="<%= prod.getProdImagen()%>" alt="<%= prod.getProdNombre()%>">
                <h3 class="product-title"><%= prod.getProdNombre()%></h3>

                <div class="product-rating">
                    ★★★★☆ <span class="opiniones">(15,549)</span>
                </div>

                <span class="product-badge-discount">-<%= (int) descuento%>% </span>
                <div class="product-price">
                    <%
                        String precioFormateado = df.format(precioFinal); // ej: "25.99"
                        String entero = precioFormateado.substring(0, precioFormateado.indexOf("."));
                        String decimal = precioFormateado.substring(precioFormateado.indexOf(".") + 1);
                    %>


                    <span class="price-currency">US$</span>
                    <span class="price-integer"><%= entero%></span>
                    <span class="price-decimal"><%= decimal%></span>

                    <!-- Precio anterior -->
                    <span class="price-old">US$<%= df.format(precioOriginal)%></span>
                </div>

                <button class="product-btn-cart" onclick="event.stopPropagation(); agregarAlCarrito(<%= prod.getProdId()%>);">
                    Agregar al Carrito
                </button>
            </div>

            <% }%>
        </div>
    </section>


    <!-- PAGINACION DE LOS PRODUCTOS DE OFERTA  -->
   <div class="pagination">

    <% if (paginaActual > 1) { %>
        <a class="page-btn"
           href="?page=<%= paginaActual - 1 %>">«</a>
    <% } %>

    <% for (int i = 1; i <= totalPaginas; i++) { %>
        <a class="page-btn <%= (i == paginaActual) ? "active" : "" %>"
           href="?page=<%= i %>">
            <%= i %>
        </a>
    <% } %>

    <% if (paginaActual < totalPaginas) { %>
        <a class="page-btn"
           href="?page=<%= paginaActual + 1 %>">»</a>
    <% } %>

</div>




    <!-- SECCION DE TESTIMONIOS -->
    <section class="testimonios">
        <h2>Lo que dicen nuestros clientes</h2>
        <div class="testimonios-grid">
            <div class="testimonio">
                <p>?Excelente atención y productos de calidad, volveré a comprar.?</p>
                <h4>- María López</h4>
            </div>
            <div class="testimonio">
                <p>?Envío rápido y precios accesibles, 100% recomendado.?</p>
                <h4>- Carlos Pérez</h4>
            </div>
            <div class="testimonio">
                <p>?Gran variedad de productos, encontré justo lo que necesitaba.?</p>
                <h4>- Ana Torres</h4>
            </div>
        </div>
    </section>

    <!-- Script JavaScript para manejar el carrusel de productos -->
    <script>
        let scrollAmount = 0;

        function moveSlide(direction) {
            const track = document.getElementById('carousel-track');
            const slideWidth = track.children[0].offsetWidth + 24;
            scrollAmount += direction * slideWidth * 2;

            track.scrollTo({
                left: scrollAmount,
                behavior: 'smooth'
            });

            if (scrollAmount < 0)
                scrollAmount = 0;
        }
    </script>


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
            window.location.href = "DetalleProducto?id=" + idProducto;
        }

        function verCategoria(nombreCategoria) {
            window.location.href =
                    "Categoria?categoriaNombre=" + encodeURIComponent(nombreCategoria);
        }

    </script>
</main>

<%@include file="../Components/footer.jsp" %>
