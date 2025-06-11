package yugioop.modelo.carta.trampas;

import yugioop.modelo.carta.CartaTrampa;
import yugioop.modelo.mesa.MesaYugioh;

public class RecuperarVida extends CartaTrampa {
    private final Integer cantidad;

    public RecuperarVida(String nombre, Integer cantidad) {
        super(nombre, Evento.JUEGA_MAGIA);
        this.cantidad = cantidad;
    }

    @Override
    public void activar(MesaYugioh mesa, Evento evento) {
        if(this.evento == evento){
            mesa.jugadorActualGanaVida(cantidad);
        }
    }
}
