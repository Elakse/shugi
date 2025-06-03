package yugioop.modelo.tablero;

import yugioop.modelo.carta.Carta;
import yugioop.modelo.carta.CartaMonstruo;
import java.util.List;

/**
 * Interfaz que define las operaciones que un tablero de jugador debe soportar.
 */
public interface ITableroJugador {
    /**
     * Intenta colocar un monstruo en una posición específica del tablero.
     * @param cartaM La CartaMonstruo a colocar.
     * @param pos La posición (0-4) en la zona de monstruos.
     * @return true si el monstruo fue colocado exitosamente, false en caso contrario.
     */
    boolean colocarMonstruo(CartaMonstruo cartaM, int pos);
    
    /**
     * Intenta colocar una carta mágica o trampa en una posición específica del tablero.
     * @param cartaMT La Carta (Mágica o Trampa) a colocar.
     * @param pos La posición (0-4) en la zona de magia/trampa.
     * @return true si la carta fue colocada exitosamente, false en caso contrario.
     */
    boolean colocarMagiaTrampa(Carta cartaMT, int pos);
    
    /**
     * Obtiene la zona de monstruos del tablero.
     * @return La IZonaTablero<CartaMonstruo> que representa la zona de monstruos.
     */
    IZonaTablero<CartaMonstruo> getZonaMonstruos();
    
    /**
     * Obtiene la zona de magia y trampas del tablero.
     * @return La IZonaTablero<Carta> que representa la zona de magia y trampas.
     */
    IZonaTablero<Carta> getZonaMagiaTrampa();
    
    /**
     * Remueve un monstruo de una posición específica.
     * @param pos La posición (0-4) del monstruo a remover.
     * @return La CartaMonstruo removida, o null si no había monstruo o la posición es inválida.
     */
    CartaMonstruo removerMonstruo(int pos);
    
    /**
     * Remueve una carta mágica o trampa de una posición específica.
     * @param pos La posición (0-4) de la carta a remover.
     * @return La Carta removida, o null si no había carta o la posición es inválida.
     */
    Carta removerMagiaTrampa(int pos);
    
    /**
     * Verifica si hay espacio libre en la zona de monstruos.
     * @return true si hay al menos un espacio libre, false si todos están ocupados.
     */
    boolean hayEspacioEnZonaMonstruos();
    
    /**
     * Verifica si hay espacio libre en la zona de magia y trampas.
     * @return true si hay al menos un espacio libre, false si todos están ocupados.
     */
    boolean hayEspacioEnZonaMagiaTrampa();
    
    /**
     * Obtiene todos los monstruos actualmente en el tablero.
     * @return Una lista de CartaMonstruo que representa los monstruos en el tablero.
     */
    List<CartaMonstruo> obtenerMonstruosEnCampo();
    
    /**
     * Obtiene todas las cartas mágicas y trampas actualmente en el tablero.
     * @return Una lista de Carta que representa las cartas mágicas y trampas en el tablero.
     */
    List<Carta> obtenerMagiasTrampasEnCampo();
}
