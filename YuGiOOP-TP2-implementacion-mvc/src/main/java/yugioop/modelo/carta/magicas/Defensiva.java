package yugioop.modelo.carta.magicas;

import yugioop.modelo.carta.CartaMagica;
import yugioop.modelo.carta.CartaMonstruo;
import yugioop.modelo.tablero.Tablero;

public class Defensiva extends CartaMagica {

    private String mensajePeticion;

    public Defensiva(String nombre, int turnos, String mensajePeticion) {
        super(nombre, turnos);
        this.mensajePeticion = mensajePeticion;
    }

    @Override
    public boolean activar(Tablero tablero) {
        CartaMonstruo monstruo = tablero.pedirCartaMonstruoPorPosicion(mensajePeticion);
        tablero.inhabilitarCartaMonstruo(monstruo);
        if(this.turnosRestantes > 0) {
            this.turnosRestantes--;
            return true;
        }
        else {
            return false;
        }
    }
}