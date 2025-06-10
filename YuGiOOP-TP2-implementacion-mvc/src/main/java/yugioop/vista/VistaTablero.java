package yugioop.vista;

import yugioop.modelo.jugador.Jugador;
import yugioop.modelo.carta.Carta;
import java.util.List;

/**
 * Clase encargada de mostrar el tablero y las cartas en la terminal.
 */
public class VistaTablero {
    
    /**
     * Muestra el tablero del jugador actual
     * @param jugador Jugador actual
     */
    public static void mostrarTableroJugador(Jugador jugador) {
        // Mostrar la información del tablero del jugador actual sin cabeceras redundantes
        mostrarZonaCartas();
        
        // Mostrar información de las cartas en mano
        VistaUtils.mostrarMensaje(AsciiArt.AMARILLO + "Cartas en mano: " + jugador.getCantCartasEnMano()+ AsciiArt.RESET);
        // mostrarCartasEnMano(jugador.getCartas());
    }
    
    /**
     * Muestra el tablero del oponente
     * @param oponente Jugador oponente
     */
    public static void mostrarTableroOponente(Jugador oponente) {
        // Mostrar zona de cartas sin cabeceras redundantes
        mostrarZonaCartas();
        
        // Para el oponente normalmente no mostramos detalles de sus cartas en mano
        VistaUtils.mostrarMensaje(AsciiArt.AMARILLO + "Cartas en mano del oponente: " + oponente.getCantCartasEnMano() + AsciiArt.RESET);
    }
    
    /**
     * Muestra una representación visual de las zonas de cartas
     */
    public static void mostrarZonaCartas() {
        VistaUtils.mostrarMensaje("  [M]  [M]  [M]  [M]  [M]  - Zona de monstruos");
        VistaUtils.mostrarMensaje("  [S]  [S]  [S]  [S]  [S]  - Zona de hechizos/trampas");
        VistaUtils.mostrarMensaje("  [C]                 [D]  - C: Campo, D: Cementerio");
    }
    
    /**
     * Muestra las cartas en la mano del jugador
     * @param cartas Lista de cartas en mano
     */
    public static void mostrarCartasEnMano(List<Carta> cartas) {
        if (cartas.isEmpty()) {
            VistaUtils.mostrarMensaje(AsciiArt.ROJO + "No tienes cartas en mano." + AsciiArt.RESET);
            return;
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cartas.size(); i++) {
            Carta carta = cartas.get(i);
            sb.append(i + 1).append(". ").append(carta.getTipo()).append(": ").append(carta.getNombre());
            if (i < cartas.size() - 1) {
                sb.append(", ");
            }
        }
        
        VistaUtils.mostrarMensaje(sb.toString());
    }
    
    /**
     * Muestra el estado actual del juego (puntos de vida, etc.)
     * @param jugadorActual Jugador actual
     * @param oponente Jugador oponente
     */
    public static void mostrarEstadoJuego(Jugador jugadorActual, Jugador oponente) {
        VistaUtils.mostrarMensaje(AsciiArt.getSeparador());
        VistaUtils.mostrarMensaje(AsciiArt.VERDE + jugadorActual.getNombre() + 
                                 ": " + jugadorActual.getPuntosDeVida() + " LP" + AsciiArt.RESET);
        VistaUtils.mostrarMensaje(AsciiArt.ROJO + oponente.getNombre() + 
                                 ": " + oponente.getPuntosDeVida() + " LP" + AsciiArt.RESET);
        VistaUtils.mostrarMensaje(AsciiArt.getSeparador());
    }
}
