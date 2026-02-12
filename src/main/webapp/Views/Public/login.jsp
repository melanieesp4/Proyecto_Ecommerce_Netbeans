<%-- 
    Document   : login
    Created on : Dec 28, 2025, 2:51:06 pM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet"
              href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
        <link rel="stylesheet" href="<%= request.getContextPath()%>/css/login.css">


        <title>Login</title>
    </head>

    <body>

        <div class="container" id="container">
            <div class="form-container sign-in">
                <form id="loginForm">
                    <h1>Iniciar sesión</h1>

                    <div class="social-icons">
                        <a href="#" id="googleLogin" class="icon">
                            <i class="fa-brands fa-google"></i>
                        </a>
                    </div>

                    <span>o utiliza tu contraseña de correo electrónico</span>

                    <input type="email" id="email" placeholder="Email" required>
                    <input type="password" id="password" placeholder="Contraseña" required>

                    <a href="solicitarToken.jsp">→ ¿Olvidó su contraseña? ←</a>

                    <button type="submit">Iniciar sesión</button>

                    <p id="errorMessage" style="color:red;"></p>
                </form>
            </div>

            <div class="diseno-container">
                <div class="diseno">
                    <div class="diseno-panel diseno-derecho">
                        <h1>¡Bienvenido! Tu viaje comienza aquí 🚀</h1>
                        <p>Regístrate con tus datos personales para utilizar todas las funciones</p>
                        <button class="hidden" id="register">Registrarse</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- Supabase -->
        <script src="https://cdn.jsdelivr.net/npm/@supabase/supabase-js"></script>
        <script src="<%= request.getContextPath()%>/js/supabase-config.js"></script>

        <script>
            // LOGIN EMAIL + PASSWORD
            document.getElementById("loginForm").addEventListener("submit", async (e) => {
                e.preventDefault();

                const email = document.getElementById("email").value;
                const password = document.getElementById("password").value;

                const {error} = await supabaseClient.auth.signInWithPassword({
                    email,
                    password
                });

                if (error) {
                    document.getElementById("errorMessage").innerText =
                            "Email o contraseña incorrectos";
                    return;
                }

                window.location.href = "index.jsp";
            });

            // LOGIN CON GOOGLE
            document.getElementById("googleLogin").addEventListener("click", async (e) => {
                e.preventDefault();

                await supabaseClient.auth.signInWithOAuth({
                    provider: "google",
                    options: {
                        redirectTo: window.location.origin + "/index.jsp"
                    }
                });
            });

            // REDIRECCIÓN A REGISTRO
            document.getElementById("register").addEventListener("click", () => {
                window.location.href = "registro.jsp";
            });
        </script>

    </body>
</html>
