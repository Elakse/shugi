package yugioop.modelo.carta.trampas;

import yugioop.modelo.carta.CartaTrampa;
import yugioop.modelo.mesa.MesaYugioh;

public class Contraataque extends CartaTrampa {
    private Integer danio;

    public Contraataque(String nombre, Integer danio) {
        super(nombre, Evento.COLOCA_CARTA);
        this.danio = danio;
    }

    @Override
    public void activar(MesaYugioh mesa, Evento evento) {
        if(this.evento == evento){
            mesa.jugadorActualPierdeVida(danio);
        }
    }
}
