package yugioop.modelo.carta.trampas;

import yugioop.modelo.carta.CartaTrampa;
import yugioop.modelo.tablero.Tablero;

public class Descartar extends CartaTrampa {
    private final int cantidad;

    public Descartar(String nombre, int cantidad) {
        super(nombre);
        this.cantidad = cantidad;
    }

    @Override
    public void revelar() {
        this.setBocaAbajo(false);
    }
    
    @Override
    public boolean debeActivarse(Evento evento, Tablero tablero) {
        return evento == Evento.ATACAR;
    }

    @Override
    public void activar(Tablero tablero) {
        System.out.println("Activando trampa Descartar");
        tablero.descartarCartasAleatorias(cantidad);
    }
}
