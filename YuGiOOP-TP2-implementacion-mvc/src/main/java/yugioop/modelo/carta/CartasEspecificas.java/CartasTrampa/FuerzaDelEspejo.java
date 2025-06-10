package yugioop.modelo.carta.trampa;

import yugioop.modelo.carta.CartaMonstruo;
import yugioop.modelo.carta.CartaTrampa;
import yugioop.modelo.carta.TipoTrampa;
import yugioop.modelo.carta.condicion.Condicion;
import yugioop.modelo.carta.condicion.CondicionCuandoOponenteAtaca; // implementar
import yugioop.modelo.jugador.Jugador;
import yugioop.modelo.tablero.Tablero;
import java.util.List;


public class FuerzaDelEspejo extends CartaTrampa {
    public FuerzaDelEspejo() {
        super("Fuerza del Espejo", 
              "Cuando un monstruo del adversario ataca, destruye todos los monstruos en Posición de Ataque de tu adversario.", 
              new CondicionCuandoOponenteAtaca(),
              TipoTrampa.NORMAL);
    }

    @Override
    public void activar(Jugador activador, Jugador oponente, Tablero tablero) {
        super.activar(activador, oponente, tablero);
        System.out.println("Fuerza del Espejo activada por " + activador.getNombre() + "!");
    }
}