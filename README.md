# 🛍️ Proyecto Ecommerce en Java (NetBeans + MVC + Supabase)

Este proyecto es una aplicación web de comercio electrónico desarrollada en **Java (JSP/Servlets)** bajo el patrón **MVC (Modelo - Vista - Controlador)**.  
Permite gestionar productos, categorías y usuarios, ofreciendo una interfaz web moderna utilizando **HTML**, **CSS** y **JavaScript**.

---

## 🧠 Arquitectura del proyecto

El sistema sigue el modelo **MVC**, estructurado de la siguiente manera:

- **Model:** Clases que representan las entidades de la base de datos.
- **DAO:** Clases encargadas de realizar operaciones CRUD sobre la base de datos.
- **Controller:** Servlets que manejan la lógica del negocio y las peticiones del usuario.
- **View:** Páginas JSP que presentan la información al usuario.

---

## 🧰 Tecnologías utilizadas

- **Lenguaje:** Java 17+
- **IDE:** Apache NetBeans
- **Base de datos:** PostgreSQL (Supabase)
- **Servidor web:** Apache Tomcat 10+
- **Gestión de dependencias:** Maven
- **Control de versiones:** Git & GitHub
- **Frontend:** HTML, CSS, JavaScript

---

## 🗄️ Base de datos

La base de datos está alojada en **Supabase**, que utiliza **PostgreSQL**.  
También puede administrarse desde **pgAdmin**, conectándose con las mismas credenciales del proyecto.

### Ejemplo de configuración (Supabase)
```
Host: db.xxxxx.supabase.co
Port: 5432
Database: ecommerce_db
User: postgres
Password: ********
```

---

## 🔐 Archivo `.env`

El archivo `.env` se utiliza para guardar de forma segura las credenciales de conexión a la base de datos.  
Debe ubicarse en la raíz del proyecto (junto a `pom.xml`) y contener lo siguiente:

```
DB_URL=jdbc:postgresql://db.xxxxx.supabase.co:5432/ecommerce_db?sslmode=require
DB_USER=postgres
DB_PASSWORD=tu_contraseña_aqui
```

> ⚠️ Importante:
> - No subir el archivo `.env` a GitHub.  
> - Asegúrate de incluir `.env` en tu archivo `.gitignore`.

---

## ▶️ Ejecución del proyecto

1. Asegúrate de que tu base de datos en Supabase esté activa.  
2. Crea el archivo `.env` con las credenciales correctas.  
3. Abre el proyecto en NetBeans.  
4. Configura Tomcat como servidor.  
5. Ejecuta el proyecto (`Run Project` o **Shift + F6**).  
6. Accede en tu navegador a:  
   ```
   http://localhost:8080/Proyecto_Ecommerce_Netbeans/
   ```

---

## 📂 Estructura del proyecto

```
Proyecto_Ecommerce_Netbeans/
│
├── src/main/java/
│   ├── ecommerce_proyect/
│   │   ├── Controller/
│   │   ├── DAO/
│   │   ├── Model/
│   │   └── DatabaseHelper.java
│
├── src/main/webapp/
│   ├── PageComponents/
│   ├── styles/
│   ├── scripts/
│   └── index.jsp
│
├── .env
├── pom.xml
└── README.md
```

---
