
package ecommerce_proyect.Model;

/**
 *
 * @author User
 */
public class CategoriaModel {
    private int catId;
    private String catNombre;
    
    public CategoriaModel() {
    }

    public CategoriaModel(int catId, String catNombre) {
        this.catId = catId;
        this.catNombre = catNombre;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public String getCatNombre() {
        return catNombre;
    }

    public void setCatNombre(String catNombre) {
        this.catNombre = catNombre;
    }
    
    
}
