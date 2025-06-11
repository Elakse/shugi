package yugioop.modelo.carta.magicas;

import yugioop.modelo.carta.CartaMagica;
import yugioop.modelo.carta.CartaMonstruo;
import yugioop.modelo.tablero.Tablero;

public class Defensiva extends CartaMagica {


    public Defensiva(String nombre, int turnos) {
        super(nombre, turnos);
        this.turnos = turnos;
        this.turnosRestantes = turnos;
    }

    @Override
    public boolean activar(Tablero tablero) {
        throw new UnsupportedOperationException("Esta carta no se puede activar sin un monstruo.");
    }

    @Override
    public boolean activar(Tablero tablero, CartaMonstruo monstruo) {
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