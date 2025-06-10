package yugioop.modelo.carta.magicas;
import yugioop.modelo.carta.CartaMagica;
import yugioop.modelo.carta.CartaMonstruo;
import yugioop.modelo.tablero.Tablero;

public class Equipamiento extends CartaMagica {

    private int diferencialAtaque;
    private int diferencialDefensa;
    

    public Equipamiento(String nombre, int turnos, int diferencialAtaque, int diferencialDefensa, String mensajePeticion) {
        super(nombre, turnos);
        this.diferencialAtaque = diferencialAtaque;
        this.diferencialDefensa = diferencialDefensa;
        this.mensajePeticion = mensajePeticion;
    }

    @Override
    public boolean activar(Tablero tablero) {
        CartaMonstruo monstruo = null;
        if(turnosRestantes == turnos){
            monstruo = tablero.pedirCartaMonstruoPorPosicion(this.mensajePeticion);
            tablero.equiparCartaMagica(this, monstruo);
        }
        if(this.turnosRestantes > 0) {
            this.turnosRestantes--;
            return true;
        }
        else {
            if (monstruo != null) {
                monstruo.desequiparCartaMagica();
            }
            return false;
        }
    }

    public int getDiferencialAtaque() {
        return diferencialAtaque;
    }

    public int getDiferencialDefensa() {
        return diferencialDefensa;
    }
}