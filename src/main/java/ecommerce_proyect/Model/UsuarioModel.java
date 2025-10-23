
package ecommerce_proyect.Model;

import java.time.LocalDateTime;

/**
 *
 * @author User
 */
public class UsuarioModel {
    private int userId;
    private String userDni;
    private String userNombre;
    private String userApellido;
    private String userDireccion;
    private String userMail;
    private String userPassword;
    private LocalDateTime userFechaCreacion;
    
    public UsuarioModel(){}

    public UsuarioModel(int userId, String userDni, String userNombre, String userApellido, String userDireccion, String userMail, String userPassword, LocalDateTime userFechaCreacion) {
        this.userId = userId;
        this.userDni = userDni;
        this.userNombre = userNombre;
        this.userApellido = userApellido;
        this.userDireccion = userDireccion;
        this.userMail = userMail;
        this.userPassword = userPassword;
        this.userFechaCreacion = userFechaCreacion;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserDni() {
        return userDni;
    }

    public void setUserDni(String userDni) {
        this.userDni = userDni;
    }

    public String getUserNombre() {
        return userNombre;
    }

    public void setUserNombre(String userNombre) {
        this.userNombre = userNombre;
    }

    public String getUserApellido() {
        return userApellido;
    }

    public void setUserApellido(String userApellido) {
        this.userApellido = userApellido;
    }

    public String getUserDireccion() {
        return userDireccion;
    }

    public void setUserDireccion(String userDireccion) {
        this.userDireccion = userDireccion;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public LocalDateTime getUserFechaCreacion() {
        return userFechaCreacion;
    }

    public void setUserFechaCreacion(LocalDateTime userFechaCreacion) {
        this.userFechaCreacion = userFechaCreacion;
    }
    
    
    
}
