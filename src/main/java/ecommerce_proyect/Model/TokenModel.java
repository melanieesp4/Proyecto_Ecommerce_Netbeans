
package ecommerce_proyect.Model;

/**
 *
 * @author User
 */
public class TokenModel {
    private int tokenId;
    private int tokenUserId;
    private String tokenValor;
    private Object tokenFechaExp;
    
    public TokenModel(){
    }

    public TokenModel(int tokenId, int tokenUserId, String tokenValor, Object tokenFechaExp) {
        this.tokenId = tokenId;
        this.tokenUserId = tokenUserId;
        this.tokenValor = tokenValor;
        this.tokenFechaExp = tokenFechaExp;
    }

    public int getTokenId() {
        return tokenId;
    }

    public void setTokenId(int tokenId) {
        this.tokenId = tokenId;
    }

    public int getTokenUserId() {
        return tokenUserId;
    }

    public void setTokenUserId(int tokenUserId) {
        this.tokenUserId = tokenUserId;
    }

    public String getTokenValor() {
        return tokenValor;
    }

    public void setTokenValor(String tokenValor) {
        this.tokenValor = tokenValor;
    }

    public Object getTokenFechaExp() {
        return tokenFechaExp;
    }

    public void setTokenFechaExp(Object tokenFechaExp) {
        this.tokenFechaExp = tokenFechaExp;
    }
    
    
    
}
