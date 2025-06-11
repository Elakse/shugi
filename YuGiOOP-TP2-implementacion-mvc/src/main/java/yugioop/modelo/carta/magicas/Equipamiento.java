package yugioop.modelo.carta.magicas;

import yugioop.modelo.carta.CartaMagica;
import yugioop.modelo.jugador.ContextoJugador;
import yugioop.modelo.mesa.MesaYugioh;

public class Equipamiento extends CartaMagica {

    private int diferencialAtaque;
    private int diferencialDefensa;

    public Equipamiento(String nombre, int turnos, int diferencialAtaque, int diferencialDefensa) {
        super(nombre, turnos);
        this.diferencialAtaque = diferencialAtaque;
        this.diferencialDefensa = diferencialDefensa;
    }

    @Override
    public void activar(MesaYugioh mesa, int objetivo) {
        ContextoJugador contextoActual = mesa.obtenerContextoJugadorActual();
        if(turnosRestantes == turnos){
            mesa.cambiarAtkMontruo(contextoActual, objetivo, diferencialAtaque);
            contextoActual.agregarCartaMagicaActiva(this);
        }
        if(this.turnosRestantes <= 0) {
            mesa.reestablecerAtributosMonstruo(contextoActual, objetivo);
            contextoActual.removerCartaMagicaActiva(this);
        }
        this.turnosRestantes--;
    }

    public int getDiferencialAtaque() {
        return diferencialAtaque;
    }

    public int getDiferencialDefensa() {
        return diferencialDefensa;
    }
}