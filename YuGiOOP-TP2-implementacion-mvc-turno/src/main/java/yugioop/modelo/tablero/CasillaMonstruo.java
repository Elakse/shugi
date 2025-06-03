package yugioop.modelo.tablero;

import yugioop.modelo.carta.CartaMonstruo;

/**
 * Representa una casilla en la zona de monstruos del tablero.
 * Solo puede contener cartas de tipo CartaMonstruo.
 */
public class CasillaMonstruo extends CasillaBase<CartaMonstruo> {
    
    /**
     * Constructor de la CasillaMonstruo.
     * Inicializa la casilla sin ocupante.
     */
    public CasillaMonstruo() {
        super();
    }

    /**
     * Verifica si la casilla de monstruo está libre.
     * @return true si la casilla está libre, false si ya tiene un ocupante.
     */
    public boolean estaLibre() { 
        return this.ocupante == null;
    }

    /**
     * Intenta colocar una CartaMonstruo en la casilla.
     * @param monstruo La CartaMonstruo a colocar.
     * @return true si se colocó exitosamente, false si la casilla ya está ocupada.
     */
    public boolean colocarCarta(CartaMonstruo monstruo) {
        if (!estaLibre()) {
            System.out.println("Casilla de monstruo ya ocupada.");
            return false;
        }
        
        if (monstruo == null) {
            System.out.println("Error: No se puede colocar un monstruo nulo.");
            return false;
        }
        
        this.ocupante = monstruo;
        System.out.println("Monstruo " + monstruo.getNombre() + " colocado en la zona de monstruos.");
        return true;
    }

    /**
     * Obtiene el ocupante de la casilla.
     * @return La CartaMonstruo que ocupa la casilla, o null si está libre.
     */
    public CartaMonstruo getOcupante() {
        return this.ocupante;
    }

    /**
     * Remueve el ocupante de la casilla.
     * @return La CartaMonstruo que fue removida, o null si no había ocupante.
     */
    public CartaMonstruo removerOcupante() {
        CartaMonstruo temp = this.ocupante;
        this.ocupante = null;
        return temp;
    }
}