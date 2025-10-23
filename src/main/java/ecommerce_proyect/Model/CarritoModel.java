
package ecommerce_proyect.Model;


public class CarritoModel {
    int cartItem;
    int cartUserId;
    int cartProdId;
    String cartNombre;
    String cartDescripcion;
    double cartPrecio;
    int cartCantidad;
    double cartSubtotal;
    
    public CarritoModel(){}

    public CarritoModel(int cartItem, int cartUserId, int cartProdId, String cartNombre, String cartDescripcion, double cartPrecio, int cartCantidad, double cartSubtotal) {
        this.cartItem = cartItem;
        this.cartUserId = cartUserId;
        this.cartProdId = cartProdId;
        this.cartNombre = cartNombre;
        this.cartDescripcion = cartDescripcion;
        this.cartPrecio = cartPrecio;
        this.cartCantidad = cartCantidad;
        this.cartSubtotal = cartSubtotal;
    }

    public int getCartItem() {
        return cartItem;
    }

    public void setCartItem(int cartItem) {
        this.cartItem = cartItem;
    }

    public int getCartUserId() {
        return cartUserId;
    }

    public void setCartUserId(int cartUserId) {
        this.cartUserId = cartUserId;
    }

    public int getCartProdId() {
        return cartProdId;
    }

    public void setCartProdId(int cartProdId) {
        this.cartProdId = cartProdId;
    }

    public String getCartNombre() {
        return cartNombre;
    }

    public void setCartNombre(String cartNombre) {
        this.cartNombre = cartNombre;
    }

    public String getCartDescripcion() {
        return cartDescripcion;
    }

    public void setCartDescripcion(String cartDescripcion) {
        this.cartDescripcion = cartDescripcion;
    }

    public double getCartPrecio() {
        return cartPrecio;
    }

    public void setCartPrecio(double cartPrecio) {
        this.cartPrecio = cartPrecio;
    }

    public int getCartCantidad() {
        return cartCantidad;
    }

    public void setCartCantidad(int cartCantidad) {
        this.cartCantidad = cartCantidad;
    }

    public double getCartSubtotal() {
        return cartSubtotal;
    }

    public void setCartSubtotal(double cartSubtotal) {
        this.cartSubtotal = cartSubtotal;
    }
    
    
}
