package yugioop.modelo.carta.trampas;

import yugioop.modelo.carta.CartaTrampa;
import yugioop.modelo.tablero.Tablero;

public class RobarCartas extends CartaTrampa {
    private final int cantidad;

    public RobarCartas(String nombre, int cantidad) {
        super(nombre);
        this.cantidad = cantidad;
    }

    @Override
    public void revelar() {
        System.out.println("Trampa " + this.getNombre() + " revelada.");
        this.setBocaAbajo(false);
    }

    @Override
    public boolean debeActivarse(Evento evento, Tablero tablero) {
        return evento == Evento.JUGAR_MAGIA;
    }

    @Override
    public void activar(Tablero tablero) {
        tablero.robarCartaTrampa(this, cantidad);
    }
}
