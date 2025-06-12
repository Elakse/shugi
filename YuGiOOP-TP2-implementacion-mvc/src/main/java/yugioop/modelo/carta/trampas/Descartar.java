package yugioop.modelo.carta.trampas;

import yugioop.modelo.carta.CartaTrampa;
import yugioop.modelo.mesa.MesaYugioh;

public class Descartar extends CartaTrampa {
    private final Integer cantidad;

    public Descartar(String nombre, Integer cantidad) {
        super(nombre, Evento.ATACA);
        this.cantidad = cantidad;
    }

    @Override
    public void activar(MesaYugioh mesa, Evento evento) {
        if(this.evento == evento){
            //Explicacion: Los eventos son acciones del jugador actual, no del dueño de la carta.
            mesa.jugadorActualDescartaCartasAleatorias(cantidad);
        }
        mesa.obtenerContextoJugadorOponente().destruirCartaTrampa(this);
    }
}
