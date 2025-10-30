
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <script src="https://kit.fontawesome.com/0e53af926d.js" crossorigin="anonymous"></script>
        <title>Home</title>

    <script>
        // Funci�n para actualizar el contador del carrito mediante AJAX
        function actualizarContadorCarrito() {
            // Realizar una solicitud AJAX al servlet que obtiene el n�mero de �tems en el carrito
            var xhr = new XMLHttpRequest();
            xhr.open("GET", "CarController?act=actualizarcont", true);
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    // Actualizar el contenido del <span> con el contador del carrito
                    document.getElementById("cart-count").textContent = xhr.responseText;
                }
            };
            xhr.send();
        }

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
                    actualizarContadorCarrito();
                    // Aqui se puede redirigir al usuario a una p�gina de confirmaci�n o actualizar la UI aqu� si es necesario
                } else {
                    // Hubo un error en la solicitud
                    console.error('E');
                }
            };

            // Enviar la solicitud con el ID del producto como par�metro
            xhr.send();
        }

        function eliminarDelCarrito(idProducto) {
            // Crear una nueva instancia de XMLHttpRequest
            var xhr = new XMLHttpRequest();

            // Configurar la solicitud AJAX
            xhr.open("GET", "CarController?eliminarProductoId=" + idProducto, true);
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

            // Definir la funci�n de respuesta
            xhr.onload = function () {
                if (xhr.status === 200) {
                    // La solicitud fue exitosa
                    console.log('Producto eliminado del carrito con �xito');

                    // Eliminar el elemento del carrito del DOM
                    var elemento = document.getElementById("item-id-" + idProducto);
                    if (elemento) {
                        elemento.remove();
                    }

                    // Actualizar el contador del carrito
                    actualizarContadorCarrito();
                    actualizarPrecioTotal();
                } else {
                    // Hubo un error en la solicitud
                    console.error('Error al eliminar producto del carrito');
                }
            };

            // Enviar la solicitud con el ID del producto como par�metro
            xhr.send();
        }

        function actualizarEstadoProducto(idProducto, forWishList) {
            // Crear una nueva instancia de XMLHttpRequest
            var xhr = new XMLHttpRequest();

            // Configurar la solicitud AJAX
            xhr.open("GET", "CarController?actualizarProductoId=" + idProducto + "&forwishlist=" + forWishList, true);
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

            // Definir la funci�n de respuesta
            xhr.onload = function () {
                if (xhr.status === 200) {
                    // La solicitud fue exitosa
                    console.log('�xito');

                    // Eliminar el elemento del carrito
                    var elemento = document.getElementById("item-id-" + idProducto);
                    if (elemento) {
                        elemento.remove();
                    }

                    // Actualizar el contador del carrito
                    actualizarContadorCarrito();
                    actualizarPrecioTotal();
                } else {
                    // Hubo un error en la solicitud
                    console.error('Error');
                }
            };

            // Enviar la solicitud con el ID del producto como par�metro
            xhr.send();
        }


        function verDetalle(idProducto) {
            window.location.href = "detalle.jsp?item=" + idProducto;
        }

        // Llamar a la funci�n de actualizaci�n al cargar la p�gina
        window.onload = function () {
            actualizarContadorCarrito();
        };
    </script>
</head>
<body>
    <!--header section-->
    <%
        String txt = "";
        String url = "";
        if (session.getAttribute("userName") == null) {
            txt = "Inicia sesi�n/registrate:";
            url = "login.jsp";
        } else {
            String nom = session.getAttribute("userName").toString();
            txt = "Bienvenido(a), " + nom;
            url = "InformacionUsuario.jsp";
        }
    %>

    <header>
        <nav>
            <div class="logo">
                <img src="img/logo.png" alt="logo"/>
            </div>
            <div class="search-bar">
                <form name="formbusqueda" action="HomeController">
                    <input type="text" placeholder="Buscar Productos..." name="busqueda">
                    <button type="submit" name="btnBuscar" value="Buscar">Buscar</button>
                </form>
            </div>
            <div class="categories">
                <ul>
                    <li><a href="index.jsp">Inicio</a></li>
                    <li class="dropdown">
                        <a href="#">Categor�as</a>
                        <div class="dropdown-content">
                            <a href="ropa.jsp">Ropa</a>
                            <a href="tecnologia.jsp">Tecnolog�a</a>
                            <a href="hogar.jsp">Hogar</a>
                        </div>
                    </li>
                    <li class="dropdown">
                        <button class="dropbtn"><%= txt%></button>
                        <div class="dropdown-content">
                            <a href="<%= url%>">Mi cuenta</a>
                            <% if (session.getAttribute("userName") != null) { %>
                            <a href="LogOutController">Cerrar sesi�n</a>
                            <% } else {%>
                            <a href="<%= url%>">Iniciar sesi�n</a>
                            <% }%>
                        </div>
                    </li>
                </ul>
            </div>
            <!-- Contador del carrito -->
            <a href="comprafinal.jsp" style="text-decoration: none;">
                <div class="cart">
                    <div class="cart-icon">
                        <img src="img/cart.svg" alt="cart-Icon">
                        <span class="cart-count" id="cart-count">0</span>
                    </div>
                    <h4>Carrito</h4>
                </div>
            </a>

        </nav>
    </header>

</body>
</html>
