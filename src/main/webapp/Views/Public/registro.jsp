<%-- 
    Document   : registro
    Created on : Dec 27, 2025, 4:13:23 pM
    Author     : User
--%>


<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet"
              href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
        <link rel="stylesheet"
              href="<%= request.getContextPath()%>/css/login.css">

        <title>Registro</title>
    </head>

    <body>

        <div class="container" id="container">

            <!-- FORMULARIO REGISTRO -->
            <div class="form-container sign-in">
                <!-- ❌ NO action, NO servlet -->
                <form id="registerForm">

                    <h1>Registrarse</h1>

                    <div class="social-icons">
                        <!-- Google -->
                        <a href="#" id="googleRegister" class="icon">
                            <i class="fa-brands fa-google-plus-g"></i>
                        </a>
                    </div>

                    <span>o utiliza tu correo electrónico</span>

                    <input type="text"
                           id="nombres"
                           placeholder="Nombres"
                           required>

                    <input type="text"
                           id="apellidos"
                           placeholder="Apellidos"
                           required>

                    <input type="email"
                           id="email"
                           placeholder="Correo electrónico"
                           required>

                    <input type="password"
                           id="password"
                           placeholder="Contraseña"
                           required>

                    <button type="submit">Registrarse</button>

                    <p id="registerMessage" style="margin-top:10px;"></p>

                </form>
            </div>

            <!-- PANEL DERECHO -->
            <div class="diseno-container">
                <div id="particles-js"></div>

                <div class="diseno">
                    <div class="diseno-panel diseno-derecho">
                        <h1>¡Bienvenido! Tu viaje comienza aquí 🚀</h1>
                        <p>¿Ya tienes una cuenta?</p>
                        <button class="hidden" id="btnLogin">
                            Iniciar Sesión
                        </button>
                    </div>
                </div>
            </div>

        </div>

        <!-- SUPABASE -->
        <script src="https://cdn.jsdelivr.net/npm/@supabase/supabase-js@2"></script>
        <script src="<%= request.getContextPath()%>/js/supabase-config.js"></script>

        <!-- REGISTRO EMAIL/PASSWORD -->
        <script>
            document
                    .getElementById("registerForm")
                    .addEventListener("submit", async function (e) {
                        e.preventDefault();

                        const nombres = document.getElementById("nombres").value;
                        const apellidos = document.getElementById("apellidos").value;
                        const email = document.getElementById("email").value;
                        const password = document.getElementById("password").value;

                        const {data, error} = await supabaseClient.auth.signUp({
                            email: email,
                            password: password,
                            options: {
                                data: {
                                    nombres: nombres,
                                    apellidos: apellidos
                                }
                            }
                        });

                        const msg = document.getElementById("registerMessage");

                        if (error) {
                            msg.style.color = "red";
                            msg.innerText = error.message;
                            return;
                        }

                        msg.style.color = "green";
                        msg.innerText = "Registro exitoso. Revisa tu correo.";

                        setTimeout(() => {
                            window.location.href =
                                    "<%= request.getContextPath()%>/Views/Public/login.jsp";
                        }, 2000);
                    });
        </script>

        <!-- REGISTRO CON GOOGLE -->
        <script>
            document
                    .getElementById("googleRegister")
                    .addEventListener("click", async function (e) {
                        e.preventDefault();

                        await supabaseClient.auth.signInWithOAuth({
                            provider: "google",
                            options: {
                                redirectTo: window.location.origin +
                                        "<%= request.getContextPath()%>/Views/Public/login.jsp"
                            }
                        });
                    });
        </script>

        <!-- BOTÓN LOGIN -->
        <script>
            document
                    .getElementById("btnLogin")
                    .addEventListener("click", function () {
                        window.location.href =
                                "<%= request.getContextPath()%>/Views/Public/login.jsp";
                    });
        </script>

    </body>
</html>
