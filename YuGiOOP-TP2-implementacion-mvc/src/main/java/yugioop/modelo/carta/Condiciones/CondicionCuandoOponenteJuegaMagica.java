package yugioop.modelo.carta.condicion;

import yugioop.modelo.carta.CartaTrampa;
import yugioop.modelo.jugador.Jugador;
import yugioop.modelo.tablero.Tablero;

public class CondicionCuandoOponenteJuegaMagica implements Condicion {
    @Override
    public boolean seCumple(TipoEventoDeJuego tipoEvento, CartaTrampa trampa,
                             Jugador propietarioTrampa, Jugador jugadorAccion, Tablero tablero) {
        return tipoEvento == TipoEventoDeJuego.OPONENTE_JUEGA_MAGICA;
    }

    @Override
    public String getDescripcionCondicion() {
        return "Cuando el oponente juega una carta mágica.";
    }
}