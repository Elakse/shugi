package yugioop.modelo.juego;

import yugioop.modelo.tablero.Tablero;
import yugioop.modelo.turno.TurnoManager;
import yugioop.modelo.jugador.Jugador;
import yugioop.modelo.jugador.Mazo;


public class Juego {
    private Tablero tablero;
    private Jugador jugador1;
    private Jugador jugador2;
    private TurnoManager turnoManager;
    
    public Juego(String nombreJugador1, String nombreJugador2){
        this.jugador1 = new Jugador(nombreJugador1);
        this.jugador2 = new Jugador(nombreJugador2);
        this.turnoManager = new TurnoManager(jugador1, jugador2, decidirJugadorInicial());
        this.tablero = new Tablero(jugador1, jugador2, turnoManager);
        
    }

    public void terminarJuego(){

    }

    public void asignarMazos(Mazo mazo1, Mazo mazo2){
        this.jugador1.asignarMazo(mazo1);
        this.jugador2.asignarMazo(mazo2);
    }

    public boolean decidirJugadorInicial(){
        return Math.random() < 0.5;
    }

    public Jugador verificarGanador(){
        if(!jugador1.estaVivo()){
            return jugador2;
        } else if(!jugador2.estaVivo()){
            return jugador1;
        }
        return null;
    }

    public void avanzarTurno(){
        this.turnoManager.avanzarTurno();
    }
}
