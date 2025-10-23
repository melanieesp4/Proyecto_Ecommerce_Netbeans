
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
    
   public ProductosModel(){
        
    } 

    public ProductosModel(int prodId, String prodNombre, String prodImagen, String prodDescripcion, double prodPrecio, int prodCantidad, int prodCategoria) {
        this.prodId = prodId;
        this.prodNombre = prodNombre;
        this.prodImagen = prodImagen;
        this.prodDescripcion = prodDescripcion;
        this.prodPrecio = prodPrecio;
        this.prodCantidad = prodCantidad;
        this.prodCategoria = prodCategoria;
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
    
   
}
