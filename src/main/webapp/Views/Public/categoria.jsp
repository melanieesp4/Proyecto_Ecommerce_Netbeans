<%-- 
    Document   : categoria
    Created on : Dec 01, 2025, 3:20:21 AM
    Author     : MelanieEsp
--%>

<%@ page import="ecommerce_proyect.Model.ProductosModel" %>
<%@ page import="ecommerce_proyect.DAO.ProductosDao" %>
<%@ page import="ecommerce_proyect.DAO.CategoriaDao" %>
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
%>




<main class="pagina-categoria">

    <h1 class="titulo-categoria">Categoría: <%= nombreCategoria%></h1>

    <div class="grid-categoria">

        <% if (lista == null || lista.isEmpty()) { %>
        <p class="sin-productos">No tenemos productos en esta categoría por ahora.</p>
        <% } else {

            for (ProductosModel prod : lista) {
                String precio = String.format("%.2f", prod.getProdPrecio());
                precio = precio.replace(".", "");

                String entero = precio.substring(0, precio.length() - 2);
                String decimal = precio.substring(precio.length() - 2);
        %>

        <div class="producto-cat" onclick="verDetalle(<%= prod.getProdId()%>)">
            <img src="<%= prod.getProdImagen()%>" alt="<%= prod.getProdNombre()%>">

            <h3 class="titulo"><%= prod.getProdNombre()%></h3>

            <div class="price">

                <span class="currency">US</span>
                <span class="entero"><%= entero%></span>
                <span class="decimal"><%= decimal%></span>

            </div>

            <button class="btn-carrito"
                    onclick="event.stopPropagation(); agregarAlCarrito(<%= prod.getProdId()%>);">
                Agregar al carrito
            </button>
        </div>

        <% }
            }%>

    </div>

</main>

<%@ include file="../Components/footer.jsp" %>
