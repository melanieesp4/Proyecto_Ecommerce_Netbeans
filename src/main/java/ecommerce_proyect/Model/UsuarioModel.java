
package ecommerce_proyect.Model;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 *
 * @author MelanieAes
 */
public class UsuarioModel {

    private UUID id;               
    private String user_name;
    private String user_lastname;
    private String user_address;
    private String user_phone;
    private String user_role; 
    private LocalDateTime createdAt;

    public UsuarioModel(){}

    public UsuarioModel(UUID id, String user_name, String user_lastname, String user_address, String user_phone, String user_role, LocalDateTime createdAt) {
        this.id = id;
        this.user_name = user_name;
        this.user_lastname = user_lastname;
        this.user_address = user_address;
        this.user_phone = user_phone;
        this.user_role = user_role;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_lastname() {
        return user_lastname;
    }

    public void setUser_lastname(String user_lastname) {
        this.user_lastname = user_lastname;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_role() {
        return user_role;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}