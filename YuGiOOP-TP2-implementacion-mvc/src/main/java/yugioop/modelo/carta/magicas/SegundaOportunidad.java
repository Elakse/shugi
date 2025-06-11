package yugioop.modelo.carta.magicas;

import yugioop.modelo.carta.CartaMagica;
import yugioop.modelo.carta.CartaMonstruo;
import yugioop.modelo.tablero.Tablero;

public class SegundaOportunidad extends CartaMagica {

    private int cantidad;


    public SegundaOportunidad(String nombre, int cantidad) {
        super(nombre, 1);
        this.cantidad = cantidad;
    }

    @Override
    public boolean activar(Tablero tablero) {
        tablero.jugadorRobaCartasCementerio(this.cantidad);
        return true;
    }

    @Override
    public boolean activar(Tablero tablero, CartaMonstruo monstruo){
        throw new UnsupportedOperationException("Esta carta no se puede activar con un monstruo.");
    }
}