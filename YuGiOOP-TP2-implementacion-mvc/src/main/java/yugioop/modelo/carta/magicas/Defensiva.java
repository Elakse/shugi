package yugioop.modelo.carta.magicas;

import yugioop.modelo.carta.CartaMagica;
import yugioop.modelo.jugador.ContextoJugador;
import yugioop.modelo.mesa.MesaYugioh;

public class Defensiva extends CartaMagica {


    public Defensiva(String nombre, int turnos) {
        super(nombre, turnos);
        this.turnos = turnos;
        this.turnosRestantes = turnos;
    }

    @Override
    public void activar(MesaYugioh mesa, int objetivo) {

        ContextoJugador contextoActual = mesa.obtenerContextoJugadorActual();
        if(turnosRestantes == turnos){
            contextoActual.inhabilitarCartaMonstruo(objetivo);
            contextoActual.agregarCartaMagicaActiva(this);
        }
        if(this.turnosRestantes <= 0) {
            contextoActual.habilitarCartaMonstruo(objetivo);
            contextoActual.removerCartaMagicaActiva(this);
            contextoActual.destruirCartaMagica(this);
        }
        this.turnosRestantes--;
    }
}