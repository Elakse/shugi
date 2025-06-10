package yugioop.modelo.carta.trampas;

import yugioop.modelo.carta.CartaTrampa;
import yugioop.modelo.tablero.Tablero;

public class Contraataque extends CartaTrampa {
     private final int danio;

    public Contraataque(String nombre, int danio) {
        super(nombre);
        this.danio = danio;
    }

    @Override
    public void revelar() {
        this.setBocaAbajo(false);
    }
    
    @Override
    public boolean debeActivarse(Evento evento, Tablero tablero) {
        return evento == Evento.COLOCAR_CARTA;
    }

    @Override
    public void activar(Tablero tablero) {
        tablero.daniarAlAtacante(danio);
    }
}
