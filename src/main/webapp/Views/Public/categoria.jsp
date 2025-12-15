<%-- 
    Document   : categoria
    Created on : Dec 01, 2025, 3:20:21 AM
    Author     : MelanieEsp
--%>

<%@ page import="ecommerce_proyect.Model.ProductosModel" %>
<%@ page import="java.util.List" %>

<%@ include file="../Components/header.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    List<ProductosModel> lista =
            (List<ProductosModel>) request.getAttribute("productos");

    String nombreCategoria =
            (String) request.getAttribute("categoria");

    if (nombreCategoria == null) {
        nombreCategoria = "Categoría";
    }

    int paginaActual = (Integer) request.getAttribute("paginaActual");
    int totalPaginas = (Integer) request.getAttribute("totalPaginas");
    int totalProductos = (Integer) request.getAttribute("totalProductos");
    int productosPorPagina = (Integer) request.getAttribute("productosPorPagina");
    int offset = (Integer) request.getAttribute("offset");

    java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
%>


<div class="category-toolbar">

    <div class="category-results-info">
        <span>
            <strong>
                <%= offset + 1%> - <%= Math.min(offset + productosPorPagina, totalProductos)%>
            </strong>
            de más de <strong><%= totalProductos%></strong> resultados


        </span>
    </div>

    <div class="category-sort">
        <label for="ordenar">Ordenar por:</label>

        <select id="ordenar" onchange="ordenarCategoria(this.value)">
            <option value="">Relevancia</option>
            <option value="precio_asc">Precio: menor a mayor</option>
            <option value="precio_desc">Precio: mayor a menor</option>
            <option value="nombre_asc">Nombre: A a Z</option>
            <option value="nombre_desc">Nombre: Z a A</option>
        </select>
    </div>

</div>
<main class="category-page">




    <div class="category-layout">

        <aside class="category-filters">
            <h4 class="filter-title">Ofertas y descuentos</h4>

            <div class="filter-group">
                <label>
                    <input type="checkbox" onchange="filtrarCategoria()">
                    Promo
                </label>

                <label>
                    <input type="checkbox" onchange="filtrarCategoria()">
                    Envío gratis
                </label>
            </div>

            <h4 class="filter-title">Precio</h4>

            <div class="price-filter">
                <span>US $</span>
                <input type="number" placeholder="Mín." id="precioMin">
                <span>-</span>
                <span>US $</span>
                <input type="number" placeholder="Máx." id="precioMax">

                <button onclick="filtrarCategoria()">OK</button>
            </div>

        </aside>
        <section class="category-results">
            <h1 class="category-title">Categoría: <small> <%= nombreCategoria%> </small></h1>  

            <section class="categories-grid">

                <% if (lista == null || lista.isEmpty()) { %>
                <p class="no-products-block">
                    No tenemos productos en esta categoría por ahora.
                </p>
                <% } else {
                    for (ProductosModel prod : lista) {

                        String precioFormateado = df.format(prod.getProdPrecio());
                        String entero = precioFormateado.substring(0, precioFormateado.indexOf("."));
                        String decimal = precioFormateado.substring(precioFormateado.indexOf(".") + 1);
                %>

                <div class="product-card categories-products" onclick="verDetalle(<%= prod.getProdId()%>)">

                    <% if (prod.getProdDescuento() > 0) {%>
                    <span class="product-badge-discount categories">-<%= (int) prod.getProdDescuento()%>%</span>
                    <% }%>

                    <img src="<%= prod.getProdImagen()%>" alt="<%= prod.getProdNombre()%>">

                    <h3 class="product-title"><%= prod.getProdNombre()%></h3>

                    <div class="product-price">
                        <span class="price-currency">US$</span>
                        <span class="price-integer"><%= entero%></span>
                        <span class="price-decimal"><%= decimal%></span>

                    </div>

                    <p class="product-shipping">
                        Envío gratis en pedidos superiores a $30 enviados por Blinker.
                    </p>

                    <button class="product-btn-cart"
                            onclick="event.stopPropagation(); agregarAlCarrito(<%= prod.getProdId()%>);">
                        Agregar al carrito
                    </button>

                </div>

                <%  }
                    }%>

            </section>
        </section>
    </div>
    <div class="pagination">

        <% if (paginaActual > 1) {%>
        <a href="?id=<%= request.getParameter("id")%>&page=<%= paginaActual - 1%>">
            ← Anterior
        </a>
        <% } %>

        <% for (int i = 1; i <= totalPaginas; i++) {%>
        <a href="?id=<%= request.getParameter("id")%>&page=<%= i%>"
           class="<%= (i == paginaActual) ? "active" : ""%>">
            <%= i%>
        </a>
        <% } %>

        <% if (paginaActual < totalPaginas) {%>
        <a href="?id=<%= request.getParameter("id")%>&page=<%= paginaActual + 1%>">
            Siguiente →
        </a>
        <% }%>

    </div>


    <script>
        function ordenarCategoria(valor) {
            const url = new URL(window.location.href);
            url.searchParams.set("orden", valor);
            window.location.href = url.toString();
        }

        function filtrarCategoria() {
            const min = document.getElementById("precioMin")?.value;
            const max = document.getElementById("precioMax")?.value;

            const url = new URL(window.location.href);

            if (min)
                url.searchParams.set("min", min);
            if (max)
                url.searchParams.set("max", max);

            window.location.href = url.toString();
        }
    </script>


</main>

<%@ include file="../Components/footer.jsp" %>
