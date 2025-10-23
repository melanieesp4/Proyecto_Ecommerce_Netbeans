
package ecommerce_proyect.Model;

/**
 *
 * @author User
 */
public class ImagenProductoModel {
    private int imgId;
    private int imgProductoId;
    private String imgUrl;
    
    public ImagenProductoModel() {}

    public ImagenProductoModel(int imgId, int imgProductoId, String imgUrl) {
        this.imgId = imgId;
        this.imgProductoId = imgProductoId;
        this.imgUrl = imgUrl;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public int getImgProductoId() {
        return imgProductoId;
    }

    public void setImgProductoId(int imgProductoId) {
        this.imgProductoId = imgProductoId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
    
    
    
}
