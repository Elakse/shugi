package yugioop.modelo.carta.condicion;

import yugioop.modelo.carta.CartaTrampa; // accesible?
import yugioop.modelo.jugador.Jugador; // accesible?
import yugioop.modelo.tablero.Tablero; // accesible?

public interface Condicion {
    boolean seCumple(TipoEventoDeJuego tipoEvento, CartaTrampa trampa,
                     Jugador propietarioTrampa, Jugador jugadorAccion, Tablero tablero);
    String getDescripcionCondicion(); // Para mostrar en pantalla que es lo que activa la trampa
}