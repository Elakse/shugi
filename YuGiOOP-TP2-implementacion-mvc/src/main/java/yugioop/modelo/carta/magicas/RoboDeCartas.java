package yugioop.modelo.carta.magicas;

import yugioop.modelo.carta.CartaMagica;
import yugioop.modelo.tablero.Tablero;

public class RoboDeCartas extends CartaMagica {

    private int cantidad;

    public RoboDeCartas(String nombre, int cantidad) {
        super(nombre, 1);
        this.cantidad = cantidad;
    }

    @Override
    public boolean activar(Tablero tablero) {
        tablero.jugadorRobaCartasMazo(this.cantidad);
        return true;
    }
}