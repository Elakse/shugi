package yugioop.modelo.carta.trampa;

import yugioop.modelo.carta.CartaTrampa;
import yugioop.modelo.carta.TipoTrampa;
import yugioop.modelo.carta.condicion.Condicion;
import yugioop.modelo.carta.condicion.CondicionCuandoOponenteAtaca; // implementar
import yugioop.modelo.jugador.Jugador;
import yugioop.modelo.tablero.Tablero;

public class TrampaPsiquica extends CartaTrampa {
    public TrampaPsiquica() {
        super("Trampa Psíquica", 
              "Tu oponente descarta 2 cartas aleatorias de su mano.", 
              new CondicionCuandoOponenteAtaca(), 
              TipoTrampa.NORMAL);
    }

    @Override
    public void activar(Jugador activador, Jugador oponente, Tablero tablero) {
        super.activar(activador, oponente, tablero);
        System.out.println(oponente.getNombre() + " debe descartar 2 cartas aleatorias de su mano debido a Trampa Psíquica.");
        // TODO: oponente.descartarCartasDeManoAleatorio(2);
        // Esta carta va al cementerio (manejado por Tablero/Juego al activar la trampa)
    }
}