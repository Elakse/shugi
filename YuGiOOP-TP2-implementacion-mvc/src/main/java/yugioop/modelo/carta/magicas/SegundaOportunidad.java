package yugioop.modelo.carta.magicas;

import yugioop.modelo.carta.CartaMagica;
import yugioop.modelo.jugador.ContextoJugador;
import yugioop.modelo.mesa.MesaYugioh;

public class SegundaOportunidad extends CartaMagica {

    private int cantidad;


    public SegundaOportunidad(String nombre, int cantidad) {
        super(nombre, 1);
        this.cantidad = cantidad;
    }

    @Override
    public void activar(MesaYugioh mesa, int objetivo) {
        mesa.jugadorActualRobaCartasCementerio(this.cantidad);
        ContextoJugador contextoActual = mesa.obtenerContextoJugadorActual();
        contextoActual.destruirCartaMagica(this);
    }

}