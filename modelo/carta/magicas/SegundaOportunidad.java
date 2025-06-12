package yugioop.modelo.carta.magicas;

import java.util.Optional;

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
    public boolean requiereObjetivo(){
        return false;
    }

    @Override
    public void activar(MesaYugioh mesa, Optional<Integer> objetivo) {
        mesa.jugadorActualRobaCartasCementerio(this.cantidad);
        ContextoJugador contextoActual = mesa.obtenerContextoJugadorActual();
        contextoActual.destruirCartaMagica(this);
    }

}