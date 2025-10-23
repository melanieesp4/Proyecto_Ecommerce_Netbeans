
package ecommerce_proyect.Model;

/**
 *
 * @author User
 */
public class ProductosModel {
    int prodId;
    String prodNombre;
    String prodImagen;
    String prodDescripcion;
    double prodPrecio;
    int prodCantidad;
    int prodCategoria;
    private double prodDescuento;
    private boolean esOferta;
    
   public ProductosModel(){
        
    } 

    public ProductosModel(int prodId, String prodNombre, String prodImagen, String prodDescripcion, double prodPrecio, int prodCantidad, int prodCategoria, double prodDescuento, boolean esOferta) {
        this.prodId = prodId;
        this.prodNombre = prodNombre;
        this.prodImagen = prodImagen;
        this.prodDescripcion = prodDescripcion;
        this.prodPrecio = prodPrecio;
        this.prodCantidad = prodCantidad;
        this.prodCategoria = prodCategoria;
        this.prodDescuento = prodDescuento;
        this.esOferta = esOferta;
    }

    public int getProdId() {
        return prodId;
    }

    public void setProdId(int prodId) {
        this.prodId = prodId;
    }

    public String getProdNombre() {
        return prodNombre;
    }

    public void setProdNombre(String prodNombre) {
        this.prodNombre = prodNombre;
    }

    public String getProdImagen() {
        return prodImagen;
    }

    public void setProdImagen(String prodImagen) {
        this.prodImagen = prodImagen;
    }

    public String getProdDescripcion() {
        return prodDescripcion;
    }

    public void setProdDescripcion(String prodDescripcion) {
        this.prodDescripcion = prodDescripcion;
    }

    public double getProdPrecio() {
        return prodPrecio;
    }

    public void setProdPrecio(double prodPrecio) {
        this.prodPrecio = prodPrecio;
    }

    public int getProdCantidad() {
        return prodCantidad;
    }

    public void setProdCantidad(int prodCantidad) {
        this.prodCantidad = prodCantidad;
    }

    public int getProdCategoria() {
        return prodCategoria;
    }

    public void setProdCategoria(int prodCategoria) {
        this.prodCategoria = prodCategoria;
    }

    public double getProdDescuento() {
        return prodDescuento;
    }

    public void setProdDescuento(double prodDescuento) {
        this.prodDescuento = prodDescuento;
    }

    public boolean isEsOferta() {
        return esOferta;
    }

    public void setEsOferta(boolean esOferta) {
        this.esOferta = esOferta;
    }

}