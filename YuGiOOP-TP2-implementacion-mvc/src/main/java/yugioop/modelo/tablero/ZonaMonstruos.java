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
    private Integer tamanio; 

    /**
     * ZonaMonstruos representa la zona donde se colocan las cartas de monstruo.
     * Tiene 5 slots para colocar cartas de monstruo.
     */
    public ZonaMonstruos(Integer tamanioZona) {
        this.tamanio = tamanioZona;
        this.slots = new CasillaMonstruo[tamanioZona];
        for (Integer i = 0; i < tamanioZona; i++) {
            this.slots[i] = new CasillaMonstruo();
        }
    }

    /**
     * Obtiene el monstruo en la posición indicada, o null si está vacío o fuera de rango.
     * @param posicion Posición del slot (0-4).
     * @return La CartaMonstruo en el slot, o null si está vacío o fuera de rango.
     */
    @Override
    public CartaMonstruo obtenerCarta(Integer posicion) {
        if (posicion >= 0 && posicion < tamanio) {
            ICasillaTablero<CartaMonstruo> slot = this.slots[posicion];
            if (!slot.estaLibre()) {
                return slot.getOcupante();
            }
        }
        return null;
    }

    public void removerCartaMonstruo(CartaMonstruo monstruo){
        for (Integer i = 0; i < tamanio; i++) {
            if (!slots[i].estaLibre() && slots[i].getOcupante() == monstruo) {
                slots[i].removerOcupante();
                return;
            }
        }
        throw new IllegalStateException("El monstruo a remover no se encuentra en la zona.");
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
    public void colocarCartaEnSlot(CartaMonstruo carta, Integer posicion) {
        if (posicion >= 0 && posicion < tamanio) {
            this.slots[posicion].colocarCarta(carta);
        }
        throw new IndexOutOfBoundsException("Posición inválida para la zona de monstruos: " + posicion);
    }

    public void cambiarModoMonstruo(Integer posicionMostruo){
        if (posicionMostruo >= 0 && posicionMostruo < tamanio) {
            ICasillaTablero<CartaMonstruo> slot = this.slots[posicionMostruo];
            if (!slot.estaLibre()) {
                slot.getOcupante().cambiarModo();
            }
        } else {
            throw new IndexOutOfBoundsException("Posición de monstruo inválida: " + posicionMostruo);
        }
    }

    /**
     * Obtiene el slot de monstruo en una posición específica.
     * @param posicion Posición del slot (0-4).
     * @return El ICasillaTablero correspondiente al slot, o null si la posición es inválida.
     */
    @Override
    public ICasillaTablero<CartaMonstruo> getSlot(Integer posicion) {
        if (posicion >= 0 && posicion < tamanio) {
            return this.slots[posicion];
        }
        return null;
    }

    @Override
    public void removerCartaPorPosicion(Integer posicion) {
        if (posicion < 0 || posicion >= tamanio) {
            throw new IndexOutOfBoundsException("Posición inválida para remover carta de la zona de monstruos: " + posicion);
        }
        if (this.slots[posicion].estaLibre()) {
            throw new IllegalStateException("No hay carta para remover en el slot: " + posicion);
        }
        this.slots[posicion].removerOcupante();
    }

    @Override
    public Integer getCantCartasOcupantes(){
        Integer cantidadOcupantes = 0;
        for (ICasillaTablero<CartaMonstruo> slot : slots) {
            if (!slot.estaLibre()) {
                cantidadOcupantes++;
            }
        }
        return cantidadOcupantes;
    }
    
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
    public Integer obtenerPosicionDeCarta(CartaMonstruo carta){
        for (Integer i = 0; i < tamanio; i++) {
            ICasillaTablero<CartaMonstruo> slot = this.slots[i];
            if (!slot.estaLibre() && slot.getOcupante().equals(carta)) {
                return i;
            }
        }
        return -1; // Retorna -1 si la carta no se encuentra en ningún slot
    }
    
    @Override
    public Integer getCantidadSlotsLibres() {
        Integer cantidadSlotsDisponibles = 0;
        for (ICasillaTablero<CartaMonstruo> slot : slots) {
            if (slot.estaLibre()) {
                cantidadSlotsDisponibles++;
            }
        }
        return cantidadSlotsDisponibles;
    }


}