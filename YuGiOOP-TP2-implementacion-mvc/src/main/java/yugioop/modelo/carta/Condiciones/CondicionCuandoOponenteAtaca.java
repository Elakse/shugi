package yugioop.modelo.carta.condicion;

import yugioop.modelo.carta.CartaTrampa;
import yugioop.modelo.jugador.Jugador;
import yugioop.modelo.tablero.Tablero;

public class CondicionCuandoOponenteAtaca implements Condicion {
    @Override
    public boolean seCumple(TipoEventoDeJuego tipoEvento, CartaTrampa trampa,
                             Jugador propietarioTrampa, Jugador jugadorAccion, Tablero tablero) {
        return tipoEvento == TipoEventoDeJuego.ATAQUE_OPONENTE;
    }
    @Override
    public String getDescripcionCondicion() { return "Si el oponente ataca."; }
}