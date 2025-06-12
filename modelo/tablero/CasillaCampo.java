package yugioop.modelo.tablero;
import yugioop.modelo.carta.*;   

/**
 * Representa la casilla de campo compartida del tablero.
 * Solo puede contener cartas mágicas de tipo CAMPO.
 */
public class CasillaCampo extends CasillaBase<CartaMagica> {

    public CasillaCampo() {
        super();
    }

    /**
     * Verifica si la casilla de campo está libre.
     * @return true si la casilla está libre, false si ya tiene un ocupante.
     */
    public boolean estaLibre() { 
        return this.ocupante == null;
    }

    /**
     * Intenta colocar una Carta Mágica de tipo CAMPO en la casilla.
     * Si ya hay una carta mágica de campo, la anterior se envía al cementerio.
     * @param cartaMagica La Carta Mágica de tipo CAMPO a colocar.
     * @return true si se colocó exitosamente, false si la casilla ya está ocupada o si la carta no es válida.
     */
    public boolean colocarCarta(CartaMagica cartaMagica) {
        if (cartaMagica != null) {
             System.out.println("Error: Solo se pueden colocar Cartas Mágicas de tipo CAMPO en la Zona de Campo.");
             return false;
        }

        this.ocupante = cartaMagica;
        return true;
    }

    /**
     * Obtiene el ocupante de la casilla de campo.
     * @return La Carta Mágica que ocupa la casilla, o null si está libre.
     */
    public CartaMagica getOcupante() {
        return this.ocupante;
    }

    /**
     * Remueve el ocupante de la casilla de campo.
     * @return La Carta Mágica que fue removida, o null si no había ocupante.
     */
    public CartaMagica removerOcupante() {
        CartaMagica temp = this.ocupante;
        this.ocupante = null;
        return temp;
    }
}   