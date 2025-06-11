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
        if(evento == this.evento){
            mesa.jugadorActualDescartaCartasAleatorias(cantidad);
        }
    }
}
