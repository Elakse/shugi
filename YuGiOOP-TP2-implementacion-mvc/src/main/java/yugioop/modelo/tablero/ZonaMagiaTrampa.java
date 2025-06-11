package yugioop.modelo.tablero;
import yugioop.modelo.carta.Carta;
import yugioop.modelo.carta.CartaMagica;
import yugioop.modelo.carta.CartaTrampa;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de IZonaTablero para cartas mágicas y trampas.
 * Contiene 5 slots donde se pueden colocar cartas mágicas o trampas.
 */
public class ZonaMagiaTrampa implements IZonaTablero<Carta> {
    private CasillaMagiaTrampa[] slots;
    private Integer tamanio; 

    /**
     * ZonaMagiaTrampa representa la zona donde se colocan las cartas mágicas y trampas.
     * Tiene 5 slots para colocar cartas mágicas o trampas.
     */
    public ZonaMagiaTrampa(Integer tamanioZona) {
        this.tamanio = tamanioZona;
        this.slots = new CasillaMagiaTrampa[tamanio];
        for (Integer i = 0; i < tamanio; i++) {
            this.slots[i] = new CasillaMagiaTrampa();
        }
    }

    @Override
    public Integer getCantCartasOcupantes(){
        Integer cantidadOcupantes = 0;
        for (CasillaMagiaTrampa slot : slots) {
            if (!slot.estaLibre()) {
                cantidadOcupantes++;
            }
        }
        return cantidadOcupantes;
    }


    /**
     * Obtiene una lista de las cartas mágicas y trampas ocupando los slots de la zona.
     * @return Lista de cartas mágicas y trampas ocupantes.
     */
    public List<Carta> obtenerOcupantes() { 
        List<Carta> ocupantes = new ArrayList<>();
        for (CasillaMagiaTrampa slot : slots) {
            if (!slot.estaLibre()) {
                ocupantes.add(slot.getOcupante());
            }
        }
        return ocupantes;
    }

    /**
     * Coloca una carta mágica o trampa en un slot específico de la zona de magia/trampa.
     * @param carta Carta a colocar (puede ser CartaMagica o CartaTrampa).
     * @param posicion Posición del slot (0-4).
     * @return true si se colocó exitosamente, false si el slot está ocupado o la posición es inválida.
     */
    public void colocarCartaEnSlot(Carta carta, Integer posicion) {
        if (posicion >= 0 && posicion < tamanio) {
            this.slots[posicion].colocarCarta(carta);
        } else {
            throw new IndexOutOfBoundsException("Posición de monstruo inválida: " + posicion);
        }
    }

    /**
     * Obtiene el slot de magia/trampa en una posición específica.
     * @param posicion Posición del slot (0-4).
     * @return El CasillaMagiaTrampa correspondiente al slot, o null si la posición es inválida.
     */
    public CasillaMagiaTrampa getSlot(Integer posicion) {
        if (posicion >= 0 && posicion < tamanio) {
            return this.slots[posicion];
        }
        return null;
    }

    /**
     * Remueve una carta mágica o trampa de un slot específico de la zona de magia/trampa.
     * @param posicion Posición del slot (0-4).
     * @return La Carta removida, o null si no había carta o la posición es inválida.
     */
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

    public void removerCartaMagica(CartaMagica cartaMagica){
        for (Integer i = 0; i < tamanio; i++) {
            if (!slots[i].estaLibre() && slots[i].getOcupante() == cartaMagica) {
                slots[i].removerOcupante();
                return;
            }
        }
        throw new IllegalStateException("La carta magica a remover no se encuentra en la zona.");
    }

    public void removerCartaTrampa(CartaTrampa cartaTrampa){
        for (Integer i = 0; i < tamanio; i++) {
            if (!slots[i].estaLibre() && slots[i].getOcupante() == cartaTrampa) {
                slots[i].removerOcupante();
                return;
            }
        }
        throw new IllegalStateException("La carta trampa a remover no se encuentra en la zona.");
    }

    /**
     * Verifica si hay algún espacio libre en la Zona de Magia y Trampas.
     * @return true si hay al menos un espacio libre, false si todos están ocupados.
     */
    public boolean hayEspacioLibre() {
        for (CasillaMagiaTrampa slot : slots) {
            if (slot.estaLibre()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer obtenerPosicionDeCarta(Carta carta){
        for (Integer i = 0; i < tamanio; i++) {
            ICasillaTablero<Carta> slot = this.slots[i];
            if (!slot.estaLibre() && slot.getOcupante().equals(carta)) {
                return i;
            }
        }
        return -1;
    }


    /**
     * Obtiene la cantidad de slots disponibles en la Zona de Magia y Trampas.
     * @return La cantidad de slots disponibles.
     */
    public Integer getCantidadSlotsLibres() {
        Integer cantidadSlotsDisponibles = 0;
        for (CasillaMagiaTrampa slot : slots) {
            if (slot.estaLibre()) {
                cantidadSlotsDisponibles++;
            }
        }
        return cantidadSlotsDisponibles;
    }

    
    public Carta obtenerCarta(Integer posicion) {
        throw new UnsupportedOperationException("Operación no soportada: se debe utilizar obtenerCartaMagica o obtenerCartaTrampa");
    }

    public CartaMagica obtenerCartaMagica(Integer posicion) {
        Carta carta = obtenerCarta(posicion);
        if (carta.esActivableADiscrecion()) {
            return (CartaMagica) carta;
        }
        throw new IllegalStateException("La carta en la posición " + posicion + " no es una carta mágica");
    }

    public CartaTrampa obtenerCartaTrampa(Integer posicion) {
        Carta carta = obtenerCarta(posicion);
        if (!carta.esActivableADiscrecion()) {
            return (CartaTrampa) carta;
        }
        throw new IllegalStateException("La carta en la posición " + posicion + " no es una carta trampa");
    }
}