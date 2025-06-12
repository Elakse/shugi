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
    
    void colocarCarta(boolean esMonstruo, Carta carta, Integer pos);
    
    /**
     * Obtiene la zona de monstruos del tablero.
     * @return La IZonaTablero<CartaMonstruo> que representa la zona de monstruos.
     */
    IZonaTablero<CartaMonstruo> getZonaMonstruos();
    
    void cambiarModoMonstruo(Integer posicion);

    /**
     * Obtiene la zona de magia y trampas del tablero.
     * @return La IZonaTablero<Carta> que representa la zona de magia y trampas.
     */
    IZonaTablero<Carta> getZonaMagiaTrampa();

    List<CartaTrampa> obtenerCartasTrampa();

    Integer obtenerPosicionMonstruo(CartaMonstruo monstruo);

    List<CartaMagica> obtenerCartasMagicasActivas();

    void agregarCartaMagicaActiva(CartaMagica cartaMagica);

    void removerCartaMagicaActiva(CartaMagica cartaMagica);
    
    /**
     * Remueve un monstruo de una posición específica.
     * @param pos La posición (0-4) del monstruo a remover.
     * @return La CartaMonstruo removida, o null si no había monstruo o la posición es inválida.
     */
    void removerMonstruoPorPosicion(Integer pos);

    void removerMagicaPorPosicion(Integer pos);

    void removerTrampaPorPosicion(Integer pos);

    void removerCartaMonstruo(CartaMonstruo monstruo);

    void removerCartaMagica(CartaMagica cartaMagica);

    void removerCartaTrampa(CartaTrampa cartaTrampa);

    CartaMonstruo obtenerCartaMonstruo(Integer pos);

    CartaMagica obtenerCartaMagica(Integer pos);

    CartaTrampa obtenerCartaTrampa(Integer pos);

    void inhabilitarCartaMonstruo(Integer pos);

    void habilitarCartaMonstruo(Integer pos);

    

    /**
     * Obtiene la cantidad de monstruos ocupantes en la zona de monstruos.
     * @return La cantidad de monstruos ocupantes.
     */
    Integer getCantMonstrosOcupantes();
    
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
