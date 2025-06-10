package yugioop.modelo.carta.magica;

import yugioop.modelo.carta.CartaMagica;
import yugioop.modelo.carta.TipoMagica;
import yugioop.modelo.jugador.Jugador;
import yugioop.modelo.tablero.Tablero;

public class OllaDeLaCodicia extends CartaMagica {
    public OllaDeLaCodicia() {
        super("Olla de la Codicia", "Roba 2 cartas de tu Mazo.", TipoMagica.NORMAL);
    }

    @Override
    public void activar(Jugador activador, Jugador oponente, Tablero tablero) {
        super.activar(activador, oponente, tablero);
        // System.out.println(activador.getNombre() + " roba 2 cartas gracias a Olla de la Codicia.");
        // TODO: activador.robarCartasDelMazo(2);
        // TODO: activador.robarCartasDelMazo(2);
        // Esta carta va al cementerio (manejado por Tablero/Juego tras una activación de Mágica Normal)
    }
}