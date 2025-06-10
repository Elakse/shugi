package yugioop.modelo.carta.trampa;

import yugioop.modelo.carta.CartaTrampa;
import yugioop.modelo.carta.TipoTrampa;
import yugioop.modelo.carta.condicion.Condicion;
import yugioop.modelo.carta.condicion.CondicionCuandoOponenteJuegaMagica; // implementar
import yugioop.modelo.jugador.Jugador;
import yugioop.modelo.tablero.Tablero;

public class EsperanzaDeEscape extends CartaTrampa {
    public EsperanzaDeEscape() {
        super("Esperanza de Escape", 
              "Roba 3 cartas.", 
              new CondicionCuandoOponenteJuegaMagica(), 
              TipoTrampa.NORMAL);
    }

    @Override
    public void activar(Jugador activador, Jugador oponente, Tablero tablero) {
        super.activar(activador, oponente, tablero);
        // System.out.println(activador.getNombre() + " roba 3 cartas gracias a Esperanza de Escape.");
        // //TODO: activador.robarCartasDelMazo(NUMERO_DE_CARTAS_A_ROBAR);
        // Esta carta va al cementerio (manejado por Tablero/Juego tras activación)
    }
}