package yugioop.modelo.tablero;

public interface ICasillaTablero<T> {
    /**
     * Verifica si la casilla está libre.
     * @return true si la casilla está libre, false si ya tiene un ocupante.
     */
    boolean estaLibre();
    
    /**
     * Coloca una carta en la casilla.
     * @param carta La carta a colocar.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
    boolean colocarCarta(T carta);
    
    /**
     * Obtiene el ocupante de la casilla.
     * @return La carta que ocupa la casilla, o null si está libre.
     */
    T getOcupante();
    
    /**
     * Remueve el ocupante de la casilla.
     * @return La carta removida, o null si la casilla estaba libre.
     */
    T removerOcupante();
}
