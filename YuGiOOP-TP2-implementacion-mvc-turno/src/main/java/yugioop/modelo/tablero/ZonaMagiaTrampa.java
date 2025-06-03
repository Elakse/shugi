package yugioop.modelo.tablero;
import yugioop.modelo.carta.Carta;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de IZonaTablero para cartas mágicas y trampas.
 * Contiene 5 slots donde se pueden colocar cartas mágicas o trampas.
 */
public class ZonaMagiaTrampa implements IZonaTablero<Carta> {
    private CasillaMagiaTrampa[] slots;
    private static final int CANTIDAD_SLOTS_MAGIA_TRAMPA = 5; 

    /**
     * ZonaMagiaTrampa representa la zona donde se colocan las cartas mágicas y trampas.
     * Tiene 5 slots para colocar cartas mágicas o trampas.
     */
    public ZonaMagiaTrampa() {
        this.slots = new CasillaMagiaTrampa[CANTIDAD_SLOTS_MAGIA_TRAMPA];
        for (int i = 0; i < CANTIDAD_SLOTS_MAGIA_TRAMPA; i++) {
            this.slots[i] = new CasillaMagiaTrampa();
        }
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
    public boolean colocarCartaEnSlot(Carta carta, int posicion) {
        if (posicion >= 0 && posicion < CANTIDAD_SLOTS_MAGIA_TRAMPA) {
            return this.slots[posicion].colocarCarta(carta);
        }
        System.out.println("Error: Posición inválida para la zona de magia/trampa.");
        return false;
    }

    /**
     * Obtiene el slot de magia/trampa en una posición específica.
     * @param posicion Posición del slot (0-4).
     * @return El CasillaMagiaTrampa correspondiente al slot, o null si la posición es inválida.
     */
    public CasillaMagiaTrampa getSlot(int posicion) {
        if (posicion >= 0 && posicion < CANTIDAD_SLOTS_MAGIA_TRAMPA) {
            return this.slots[posicion];
        }
        return null;
    }

    /**
     * Remueve una carta mágica o trampa de un slot específico de la zona de magia/trampa.
     * @param posicion Posición del slot (0-4).
     * @return La Carta removida, o null si no había carta o la posición es inválida.
     */
    public Carta removerCartaDeSlot(int posicion) {
        if (posicion >= 0 && posicion < CANTIDAD_SLOTS_MAGIA_TRAMPA && !this.slots[posicion].estaLibre()) {
            return this.slots[posicion].removerOcupante();
        }
        return null;
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

    /**
     * Obtiene la cantidad de slots disponibles en la Zona de Magia y Trampas.
     * @return La cantidad de slots disponibles.
     */
    public int getCantidadSlotsLibres() {
        int cantidadSlotsDisponibles = 0;
        for (CasillaMagiaTrampa slot : slots) {
            if (slot.estaLibre()) {
                cantidadSlotsDisponibles++;
            }
        }
        return cantidadSlotsDisponibles;
    }

    /**
     * Obtiene la carta en una posición específica de la zona.
     * @param posicion La posición de la carta a obtener.
     * @return La carta en la posición especificada, o null si la posición es inválida.
     */
    public Carta obtenerCarta(int posicion) {
        if (posicion >= 0 && posicion < CANTIDAD_SLOTS_MAGIA_TRAMPA) {
            return this.slots[posicion].getOcupante();
        }
        return null;
    }
}