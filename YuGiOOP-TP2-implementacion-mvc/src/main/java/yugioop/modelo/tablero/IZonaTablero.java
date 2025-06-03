package yugioop.modelo.tablero;

import yugioop.modelo.carta.Carta;
import java.util.List;

/**
 * Interfaz que define el comportamiento común para todas las zonas del tablero.
 * @param <T> El tipo de carta que puede ocupar esta zona.
 */
public interface IZonaTablero<T extends Carta> {
    /**
     * Coloca una carta en un slot específico de la zona.
     * @param carta La carta a colocar.
     * @param posicion La posición donde colocar la carta.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
    boolean colocarCartaEnSlot(T carta, int posicion);
    
    /**
     * Remueve una carta de un slot específico de la zona.
     * @param posicion La posición de la carta a remover.
     * @return La carta removida, o null si no había carta o la posición es inválida.
     */
    T removerCartaDeSlot(int posicion);
    
    /**
     * Obtiene todas las cartas ocupantes de la zona.
     * @return Una lista con las cartas ocupantes.
     */
    List<T> obtenerOcupantes();
    
    /**
     * Verifica si hay algún espacio libre en la zona.
     * @return true si hay al menos un espacio libre, false en caso contrario.
     */
    boolean hayEspacioLibre();
    
    /**
     * Obtiene una casilla específica de la zona.
     * @param posicion La posición de la casilla a obtener.
     * @return La casilla en la posición especificada, o null si la posición es inválida.
     */
    ICasillaTablero<T> getSlot(int posicion);

    /**
     * Obtiene la cantidad de slots disponibles en la zona.
     * @return La cantidad de slots disponibles.
     */
    int getCantidadSlotsLibres();

    /**
     * Obtiene la carta en una posición específica de la zona.
     * @param posicion La posición de la carta a obtener.
     * @return La carta en la posición especificada, o null si la posición es inválida.
     */
    T obtenerCarta(int posicion);
}
