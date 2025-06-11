package yugioop.modelo.tablero;

import yugioop.modelo.carta.Carta;
import yugioop.modelo.carta.CartaMagica;
import yugioop.modelo.carta.CartaMonstruo;
import yugioop.modelo.carta.CartaTrampa;

import java.util.List;

/**
 * Interfaz que define las operaciones que un tablero de jugador debe soportar.
 */
public interface ITableroJugador {
    
    void colocarCarta(boolean esMonstruo, Carta carta, int pos);
    
    /**
     * Obtiene la zona de monstruos del tablero.
     * @return La IZonaTablero<CartaMonstruo> que representa la zona de monstruos.
     */
    IZonaTablero<CartaMonstruo> getZonaMonstruos();
    
    void cambiarModoMonstruo(int posicion);

    /**
     * Obtiene la zona de magia y trampas del tablero.
     * @return La IZonaTablero<Carta> que representa la zona de magia y trampas.
     */
    IZonaTablero<Carta> getZonaMagiaTrampa();

    int obtenerPosicionMonstruo(CartaMonstruo monstruo);

    List<CartaMagica> obtenerCartasMagicasActivas();

    void agregarCartaMagicaActiva(CartaMagica cartaMagica);

    void removerCartaMagicaActiva(CartaMagica cartaMagica);
    
    /**
     * Remueve un monstruo de una posición específica.
     * @param pos La posición (0-4) del monstruo a remover.
     * @return La CartaMonstruo removida, o null si no había monstruo o la posición es inválida.
     */
    void removerMonstruoPorPosicion(int pos);

    void removerMagicaPorPosicion(int pos);

    void removerTrampaPorPosicion(int pos);

    void removerCartaMonstruo(CartaMonstruo monstruo);

    void removerCartaMagica(CartaMagica cartaMagica);

    void removerCartaTrampa(CartaTrampa cartaTrampa);

    CartaMonstruo obtenerCartaMonstruo(int pos);

    CartaMagica obtenerCartaMagica(int pos);

    CartaTrampa obtenerCartaTrampa(int pos);

    void inhabilitarCartaMonstruo(int pos);

    void habilitarCartaMonstruo(int pos);

    

    /**
     * Obtiene la cantidad de monstruos ocupantes en la zona de monstruos.
     * @return La cantidad de monstruos ocupantes.
     */
    int getCantMonstrosOcupantes();
    
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
}
