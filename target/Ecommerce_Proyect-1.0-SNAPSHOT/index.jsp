<%@ page import="ecommerce_proyect.Model.ProductosModel" %>
<%@ page import="ecommerce_proyect.DAO.ProductosDao" %>
<%@ page import="java.util.List" %>

<%@include file="PageComponents/header.jsp" %>

<main>
    <div class="banner">
        <h4 class="compra">Compra Online</h4>
        <h2>Recibe de hasta el 50% de descuento</h2>
        <h1>En todos los productos</h1>
        <p>�No te puedes perder esta oferta!</p>
        <button>COMPRA AHORA</button>
    </div>


    <!-- SECCION DE CATEGORIAS POPULARES -->   
    <section class="categorias-destacadas">
        <h2>Categor�as populares</h2>
        <div class="categorias-grid">
            <div class="categoria" onclick="window.location.href = 'categoria.jsp?cat=tecnologia'">
                <img src="img/categoria-tecnologia.jpg" alt="Tecnolog�a">
                <h4>Tecnolog�a</h4>
            </div>
            <div class="categoria" onclick="window.location.href = 'categoria.jsp?cat=hogar'">
                <img src="img/categoria-hogar.jpg" alt="Hogar">
                <h4>Hogar</h4>
            </div>
            <div class="categoria" onclick="window.location.href = 'categoria.jsp?cat=moda'">
                <img src="img/categoria-moda.jpg" alt="Moda">
                <h4>Moda</h4>
            </div>
            <div class="categoria" onclick="window.location.href = 'categoria.jsp?cat=deportes'">
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
            if (request.getAttribute("resultadosBusqueda") == null || request.getParameter("busqueda") == null || request.getParameter("busqueda").isEmpty()) {
                txtB = "Productos destacados";
                productos = productoDAO.listar();
            } else {
                txtB = "Resultados de b�squeda para: " + request.getParameter("busqueda");
                productos = (List<ProductosModel>) request.getAttribute("resultadosBusqueda");
            }
        %>

        <h1><%= txtB%></h1>
        <div class="sections">
            <% for (ProductosModel producto : productos) {%>
            <div class="producto" onclick="verDetalle(<%= producto.getProdId()%>)">
                <img id="imgproducto" src="<%= producto.getProdImagen()%>" alt="<%= producto.getProdNombre()%>">
                <h3><%= producto.getProdNombre()%></h3>
                <h4 class="price"><%= "$" + producto.getProdPrecio()%></h4>
                <div class="stars">&#9733;&#9733;&#9733;&#9733;&#9733;</div>
                <div class="buy-container">
                    <button onclick="agregarAlCarrito(<%= producto.getProdId()%>, event); actualizarContadorCarrito();">
                        <i class="fa-solid fa-cart-shopping buy-icon"></i>
                    </button>
                </div>
            </div>
            <% } %>
        </div>
    </section>

    <!-- BENEFICIOS DE COMPRAR AQUI -->
    <section class="beneficios">
        <div class="beneficio">
            <i class="fa-solid fa-truck-fast"></i>
            <h4>Env�os r�pidos</h4>
            <p>Entrega en 24-48 horas en todo el pa�s.</p>
        </div>
        <div class="beneficio">
            <i class="fa-solid fa-lock"></i>
            <h4>Pagos seguros</h4>
            <p>Tus transacciones est�n protegidas con cifrado SSL.</p>
        </div>
        <div class="beneficio">
            <i class="fa-solid fa-rotate-left"></i>
            <h4>Devoluciones f�ciles</h4>
            <p>Hasta 30 d�as para cambios o devoluciones.</p>
        </div>
    </section>


    <!--OFERTAS DEL DIA -->
    <section class="ofertas">
        <h2>Ofertas del d�a</h2>
        <div class="ofertas-grid">
            <%
                List<ProductosModel> ofertas = productoDAO.listarOfertas();
                for (ProductosModel prod : ofertas) {
            %>
            <div class="oferta" onclick="verDetalle(<%= prod.getProdId()%>)">
                <img src="<%= prod.getProdImagen()%>" alt="<%= prod.getProdNombre()%>">
                <h3><%= prod.getProdNombre()%></h3>
                <p class="precio-anterior">$<%= prod.getProdPrecio() + 30%></p>
                <h4 class="precio-descuento">$<%= prod.getProdPrecio()%></h4>
                <span class="descuento">-30%</span>
            </div>
            <% }%>
        </div>
    </section>



    <!-- SECCION DE TESTIMONIOS -->
    <section class="testimonios">
        <h2>Lo que dicen nuestros clientes</h2>
        <div class="testimonios-grid">
            <div class="testimonio">
                <p>?Excelente atenci�n y productos de calidad, volver� a comprar.?</p>
                <h4>- Mar�a L�pez</h4>
            </div>
            <div class="testimonio">
                <p>?Env�o r�pido y precios accesibles, 100% recomendado.?</p>
                <h4>- Carlos P�rez</h4>
            </div>
            <div class="testimonio">
                <p>?Gran variedad de productos, encontr� justo lo que necesitaba.?</p>
                <h4>- Ana Torres</h4>
            </div>
        </div>
    </section>


    <!-- Script JavaScript para manejar el evento onclick del bot�n -->
    <script>
        function agregarAlCarrito(idProducto, event) {

            event.stopPropagation();

            // Crear una nueva instancia de XMLHttpRequest
            var xhr = new XMLHttpRequest();

            // Configurar la solicitud AJAX
            xhr.open("GET", "HomeController?productoId=" + idProducto, true);
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

            // Definir la funci�n de respuesta
            xhr.onload = function () {
                if (xhr.status === 200) {
                    // La solicitud fue exitosa
                    console.log('s');
                    // Aqui se puede redirigir al usuario a una p�gina de confirmaci�n o actualizar la UI aqu� si es necesario
                } else {
                    // Hubo un error en la solicitud
                    console.error('E');
                }
            };

            // Enviar la solicitud con el ID del producto como par�metro
            xhr.send();
        }

        function verDetalle(idProducto) {
            window.location.href = "detalle.jsp?item=" + idProducto;
        }
    </script>
</main>

<%@include file="PageComponents/footer.jsp" %>
