package yugioop.modelo.tablero;

import yugioop.modelo.carta.Carta;

/**
 * Clase abstracta base para todas las casillas del tablero.
 * Proporciona implementaciones comunes para los métodos de la interfaz ICasillaTablero.
 * @param <T> Tipo de carta que puede ocupar esta casilla.
 */
public abstract class CasillaBase<T extends Carta> implements ICasillaTablero<T> {
    protected T ocupante;
    
    /**
     * Constructor de la CasillaBase.
     * Inicializa la casilla sin ocupante.
     */
    public CasillaBase() {
        this.ocupante = null;
    }
    
    /**
     * Verifica si la casilla está libre.
     * @return true si la casilla está libre, false si ya tiene un ocupante.
     */
    public boolean estaLibre() {
        return ocupante == null;
    }
    
    /**
     * Obtiene el ocupante de la casilla.
     * @return La carta que ocupa la casilla, o null si está libre.
     */
    public T getOcupante() {
        return ocupante;
    }
    
    /**
     * Remueve el ocupante de la casilla.
     * @return La carta removida, o null si la casilla estaba libre.
     */
    public T removerOcupante() {
        T temp = ocupante;
        ocupante = null;
        return temp;
    }
}
