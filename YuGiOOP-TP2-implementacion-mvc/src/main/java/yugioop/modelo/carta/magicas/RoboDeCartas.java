package yugioop.modelo.carta.magicas;

import yugioop.modelo.carta.CartaMagica;
import yugioop.modelo.jugador.ContextoJugador;
import yugioop.modelo.mesa.MesaYugioh;

public class RoboDeCartas extends CartaMagica {

    private int cantidad;

    public RoboDeCartas(String nombre, int cantidad) {
        super(nombre, 1);
        this.cantidad = cantidad;
    }

    @Override
    public void activar(MesaYugioh mesa, int objetivo) {
        mesa.jugadorActualRobaCartasMazo(this.cantidad);
        ContextoJugador contextoActual = mesa.obtenerContextoJugadorActual();
        contextoActual.destruirCartaMagica(this);
    }

}