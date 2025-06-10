package yugioop.modelo.carta.trampas;

import yugioop.modelo.carta.CartaTrampa;
import yugioop.modelo.tablero.Tablero;

public class RecuperarVida extends CartaTrampa {
    private final int cantidad;

    public RecuperarVida(String nombre, int cantidad) {
        super(nombre);
        this.cantidad = cantidad;
    }
    @Override
    public void revelar() {
        this.setBocaAbajo(false);
    }

    @Override
    public boolean debeActivarse(Evento evento, Tablero tablero) {
        return evento == Evento.JUGAR_MAGIA;
    }

    @Override
    public void activar(Tablero tablero) {
        tablero.recuperarVida(cantidad);
    }
}
