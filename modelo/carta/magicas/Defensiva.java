package yugioop.modelo.carta.magicas;

import java.util.Optional;

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
    public boolean requiereObjetivo(){
        return true;
    }


    @Override
    public void activar(MesaYugioh mesa, Optional<Integer> objetivo) {
        if (!objetivo.isPresent()) {
            throw new IllegalArgumentException("La carta Defensiva requiere un monstruo objetivo.");
        }
        ContextoJugador contextoActual = mesa.obtenerContextoJugadorActual();
        if(turnosRestantes == turnos){
            contextoActual.inhabilitarCartaMonstruo(objetivo.get());
            contextoActual.agregarCartaMagicaActiva(this);
        }
        if(this.turnosRestantes <= 0) {
            contextoActual.habilitarCartaMonstruo(objetivo.get());
            contextoActual.removerCartaMagicaActiva(this);
            contextoActual.destruirCartaMagica(this);
        }
        this.turnosRestantes--;
    }
}