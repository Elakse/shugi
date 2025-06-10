package yugioop.modelo.tablero;

import java.util.ArrayList;
import java.util.List;
import yugioop.modelo.carta.CartaMonstruo;

/**
 * Implementación de IZonaTablero para cartas de monstruo.
 * Contiene 5 slots donde se pueden colocar cartas de monstruo.
 */
public class ZonaMonstruos implements IZonaTablero<CartaMonstruo> {
    private ICasillaTablero<CartaMonstruo>[] slots;
    private static final int CANTIDAD_SLOTS_MONSTRUO = 5; 

    /**
     * ZonaMonstruos representa la zona donde se colocan las cartas de monstruo.
     * Tiene 5 slots para colocar cartas de monstruo.
     */
    public ZonaMonstruos() {
        this.slots = new CasillaMonstruo[CANTIDAD_SLOTS_MONSTRUO];
        for (int i = 0; i < CANTIDAD_SLOTS_MONSTRUO; i++) {
            this.slots[i] = new CasillaMonstruo();
        }
    }

    /**
     * Obtiene el monstruo en la posición indicada, o null si está vacío o fuera de rango.
     * @param posicion Posición del slot (0-4).
     * @return La CartaMonstruo en el slot, o null si está vacío o fuera de rango.
     */
    @Override
    public CartaMonstruo obtenerCarta(int posicion) {
        if (posicion >= 0 && posicion < CANTIDAD_SLOTS_MONSTRUO) {
            ICasillaTablero<CartaMonstruo> slot = this.slots[posicion];
            if (!slot.estaLibre()) {
                return slot.getOcupante();
            }
        }
        return null;
    }

    /**
     * Obtiene una lista de los monstruos ocupando los slots de la zona.
     * @return Lista de cartas de monstruo ocupantes.
     */
    @Override
    public List<CartaMonstruo> obtenerOcupantes() { 
        List<CartaMonstruo> ocupantes = new ArrayList<>();
        for (ICasillaTablero<CartaMonstruo> slot : slots) {
            if (!slot.estaLibre()) {
                ocupantes.add(slot.getOcupante());
            }
        }
        return ocupantes;
    }
    
    /**
     * Coloca un monstruo en un slot específico de la zona de monstruos.
     * @param monstruo CartaMonstruo a colocar.
     * @param posicion Posición del slot (0-4).
     * @return true si se colocó exitosamente, false si el slot está ocupado o la posición es inválida.
     */
    @Override
    public boolean colocarCartaEnSlot(CartaMonstruo carta, int posicion) {
        if (posicion >= 0 && posicion < CANTIDAD_SLOTS_MONSTRUO) {
            return this.slots[posicion].colocarCarta(carta);
        }
        System.out.println("Error: Posición inválida para la zona de monstruos.");
        return false;
    }

    /**
     * Obtiene el slot de monstruo en una posición específica.
     * @param posicion Posición del slot (0-4).
     * @return El ICasillaTablero correspondiente al slot, o null si la posición es inválida.
     */
    @Override
    public ICasillaTablero<CartaMonstruo> getSlot(int posicion) {
        if (posicion >= 0 && posicion < CANTIDAD_SLOTS_MONSTRUO) {
            return this.slots[posicion];
        }
        return null;
    }

    /**
     * Implementación del método de la interfaz IZonaTablero.
     * @param posicion Posición del slot (0-4).
     * @return La CartaMonstruo removida, o null si no había monstruo o la posición es inválida.
     */
    @Override
    public CartaMonstruo removerCartaDeSlot(int posicion) {
        if (posicion >= 0 && posicion < CANTIDAD_SLOTS_MONSTRUO && !this.slots[posicion].estaLibre()) {
            return this.slots[posicion].removerOcupante();
        }
        return null;
    }
    
    /**
     * Verifica si hay algún espacio libre en la zona de monstruos.
     * @return true si hay al menos un espacio libre, false si todos están ocupados.
     */
    @Override
    public boolean hayEspacioLibre() {
        for (ICasillaTablero<CartaMonstruo> slot : slots) {
            if (slot.estaLibre()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int obtenerPosicionDeCarta(CartaMonstruo carta){
        for (int i = 0; i < CANTIDAD_SLOTS_MONSTRUO; i++) {
            ICasillaTablero<CartaMonstruo> slot = this.slots[i];
            if (!slot.estaLibre() && slot.getOcupante().equals(carta)) {
                return i;
            }
        }
        return -1; // Retorna -1 si la carta no se encuentra en ningún slot
    }
    
    /**
     * Obtiene la cantidad de slots disponibles en la Zona de Monstruos.
     * @return La cantidad de slots disponibles.
     */
    @Override
    public int getCantidadSlotsLibres() {
        int cantidadSlotsDisponibles = 0;
        for (ICasillaTablero<CartaMonstruo> slot : slots) {
            if (slot.estaLibre()) {
                cantidadSlotsDisponibles++;
            }
        }
        return cantidadSlotsDisponibles;
    }


}