<%@ page import="java.util.List" %>
<%@ page import="ecommerce_proyect.Model.ImagenProductoModel" %>
<%@ page import="ecommerce_proyect.DAO.ImagenProductoDao" %>
<%@ page import="ecommerce_proyect.Model.ProductosModel" %>
<%@ page import="ecommerce_proyect.DAO.ProductosDao" %>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Detalles del producto</title>
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/detalles-producto.css">
    </head>

    <body>
        <%@ include file="PageComponents/header.jsp" %>

        <div class="product-detail-container">
            <div class="gallery-section">
                <div class="thumbnail-list">
                    <%
                        int productId = Integer.parseInt(request.getParameter("item"));
                        ProductosDao productoDAO = new ProductosDao();
                        ImagenProductoDao imgProDAO = new ImagenProductoDao();
                        List<ImagenProductoModel> productosImg = imgProDAO.obtenerImagenesPorProducto(productId);
                        for (ImagenProductoModel img : productosImg) {
                    %>
                    <img onclick="document.getElementById('main-image').style.backgroundImage = 'url(<%= img.getImgUrl()%>)'" 
                         src="<%= img.getImgUrl()%>" class="thumbnail">
                    <% }%>
                </div>

                <div id="main-image" class="main-image"
                     style="background-image: url(<%= productoDAO.obtenerProductoPorId(productId).getProdImagen()%>);">
                </div>
            </div>

            <%
                ProductosModel producto = productoDAO.obtenerProductoPorId(productId);
            %>
            <div class="product-info">
                <h1><%= producto.getProdNombre()%></h1>
                <p class="description"><%= producto.getProdDescripcion()%></p>

                <div class="price-section">
                    <h2><%= "$" + producto.getProdPrecio()%></h2>
                    <p class="shipping">Envío gratis disponible</p>
                </div>

                <button class="add-to-cart" onclick="agregarAlCarrito(<%= productId%>)">
                    <i class="fa-solid fa-cart-plus"></i> Añadir al carrito
                </button>

                <div class="icons">
                    <img src="img/1562687-code-computer-creative-html-process-technology-web-development_107058.svg" alt="">
                    <img src="img/technology_no_wifi_wireless_internet_icon_192894.svg" alt="">
                    <img src="img/technology_safety_internet_privacy_protection_secure_security_icon_231794.svg" alt="">
                </div>
            </div>
        </div>

        <!--Artiulos que te podrian interesar  -->
        <section class="related-products">
            <h2>Artículos que te podrian interesar</h2>
            <div class="slider">
                <%
                    List<ProductosModel> similares = productoDAO.obtenerProductosInteresantes(producto.getProdCategoria(), producto.getProdId());
                    for (ProductosModel sim : similares) {
                %>
                <div class="similar-item" onclick="verDetalle(<%= sim.getProdId()%>)">
                    <img src="<%= sim.getProdImagen()%>" alt="<%= sim.getProdNombre()%>">
                    <h3><%= sim.getProdNombre()%></h3>
                    <p class="price">$<%= sim.getProdPrecio()%></p>
                </div>
                <% }%>
            </div>
        </section>

        <!-- ? Comentarios -->
        <section class="comments-section">
            <h2>Opiniones de clientes</h2>
            <div class="comment">
                <p class="stars">?????</p>
                <p><strong>Juan P.</strong> - Muy buen producto, llegó antes de lo esperado.</p>
            </div>
            <div class="comment">
                <p class="stars">?????</p>
                <p><strong>Laura G.</strong> - Cumple con lo prometido, aunque la caja llegó un poco dañada.</p>
            </div>
            <div class="comment">
                <p class="stars">?????</p>
                <p><strong>Mario R.</strong> - Excelente calidad y funcionamiento perfecto. Lo recomiendo.</p>
            </div>
        </section>

        <%@ include file="PageComponents/footer.jsp" %>

        <script>
            function agregarAlCarrito(idProducto) {
                var xhr = new XMLHttpRequest();
                xhr.open("GET", "HomeController?productoId=" + idProducto, true);
                xhr.onload = function () {
                    if (xhr.status === 200) {
                        alert("Producto agregado al carrito");
                    }
                };
                xhr.send();
            }

            function verDetalle(id) {
                window.location.href = "detalle.jsp?item=" + id;
            }
        </script>
    </body>
</html>
