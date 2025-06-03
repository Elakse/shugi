package yugioop.modelo.tablero;

import yugioop.modelo.carta.Carta;
import yugioop.modelo.carta.CartaMonstruo;
import java.util.List;

/**
 * Implementación de ITableroJugador que representa el tablero de un jugador.
 * Contiene zonas para monstruos y cartas mágicas/trampas.
 */
public class TableroJugador implements ITableroJugador {
    private ZonaMonstruos zonaMonstruos;
    private ZonaMagiaTrampa zonaMagiaTrampa;

    public TableroJugador() {
        this.zonaMonstruos = new ZonaMonstruos();
        this.zonaMagiaTrampa = new ZonaMagiaTrampa();
    }

    /**
     * Intenta colocar un monstruo en una posición específica de la ZonaMonstruos.
     * @param cartaM La CartaMonstruo a colocar.
     * @param pos La posición (0-4) en la ZonaMonstruos.
     * @return true si el monstruo fue colocado exitosamente, false en caso contrario.
     */
    @Override
    public boolean colocarMonstruo(CartaMonstruo cartaM, int pos) {
        return this.zonaMonstruos.colocarCartaEnSlot(cartaM, pos);
    }

    /**
     * Intenta colocar una Carta (Mágica o Trampa) en una posición específica de la ZonaMagiaTrampa.
     * Las trampas se colocan siempre boca abajo.
     * @param cartaMT La Carta (Mágica o Trampa) a colocar.
     * @param pos La posición (0-4) en la ZonaMagiaTrampa.
     * @return true si la carta fue colocada exitosamente, false en caso contrario.
     */
    @Override
    public boolean colocarMagiaTrampa(Carta cartaMT, int pos) {
        return this.zonaMagiaTrampa.colocarCartaEnSlot(cartaMT, pos);
    }

    /**
     * Obtiene la zona de monstruos del tablero.
     * @return La zona de monstruos como IZonaTablero<CartaMonstruo>.
     */
    @Override
    public IZonaTablero<CartaMonstruo> getZonaMonstruos() { return zonaMonstruos; }
    
    /**
     * Obtiene la zona de magia y trampas del tablero.
     * @return La zona de magia y trampas como IZonaTablero<Carta>.
     */
    @Override
    public IZonaTablero<Carta> getZonaMagiaTrampa() { return zonaMagiaTrampa; }

    /**
     * Remueve un monstruo de una posición específica de la ZonaMonstruos.
     * @param pos La posición (0-4) del monstruo a remover.
     * @return La CartaMonstruo removida, o null si no había monstruo o la posición es inválida.
     */
    @Override
    public CartaMonstruo removerMonstruo(int pos) {
        return zonaMonstruos.removerCartaDeSlot(pos);
    }

    /**
     * Remueve una carta mágica o trampa de una posición específica de la ZonaMagiaTrampa.
     * @param pos La posición (0-4) de la carta a remover.
     * @return La Carta removida, o null si no había carta o la posición es inválida.
     */
    @Override
    public Carta removerMagiaTrampa(int pos) {
        return zonaMagiaTrampa.removerCartaDeSlot(pos);
    }

    /**
     * Verifica si hay algún espacio libre en la Zona de Monstruos.
     * @return true si hay al menos un espacio libre, false si todos están ocupados.
     */
    @Override
    public boolean hayEspacioEnZonaMonstruos() {
        return zonaMonstruos.hayEspacioLibre();
    }

    /**
     * Verifica si hay algún espacio libre en la Zona de Magia y Trampas.
     * @return true si hay al menos un espacio libre, false si todos están ocupados.
     */
    @Override
    public boolean hayEspacioEnZonaMagiaTrampa() {
        return zonaMagiaTrampa.hayEspacioLibre();
    }

    /**
     * Obtiene todos los monstruos actualmente en la zona de monstruos del jugador.
     * @return Una lista de CartaMonstruo.
     */
    @Override
    public List<CartaMonstruo> obtenerMonstruosEnCampo() {
        return zonaMonstruos.obtenerOcupantes();
    }

    /**
     * Obtiene todas las cartas mágicas y trampas actualmente en la zona de magia/trampa del jugador.
     * @return Una lista de Carta.
     */
    @Override
    public List<Carta> obtenerMagiasTrampasEnCampo() {
        return zonaMagiaTrampa.obtenerOcupantes();
    }
}