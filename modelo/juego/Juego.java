package yugioop.modelo.juego;

import java.util.List;

import yugioop.modelo.carta.Carta;
import yugioop.modelo.carta.CartaMonstruo;
import yugioop.modelo.jugador.Jugador;
import yugioop.modelo.tablero.Tablero;
import yugioop.modelo.turno.FaseTurno;
import yugioop.modelo.turno.TurnoManager;

/**
 * Clase central que representa el estado de un juego de Yu-Gi-Oh.
 * Mantiene referencias a los jugadores, el tablero y el gestor de turnos.
 */
public class Juego {
    private Jugador jugador1;
    private Jugador jugador2;
    private Jugador jugadorActual;
    private Tablero tablero;
    private TurnoManager turnoManager;

    public Juego(Jugador jugador1, Jugador jugador2) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.tablero = new Tablero(jugador1, jugador2);
        this.jugadorActual = jugador1; // Por defecto, luego se puede cambiar
        this.turnoManager = new TurnoManager(jugador1, jugador2, true);
    } // Inicia el juego: pone LP en 8000 y reparte 5 cartas a cada jugador

    public void iniciarJuego() {
        jugador1.robarCartasMazo(5);
        jugador2.robarCartasMazo(5);
    }

    // Asigna el mazo inicial al jugador
    public void distribuirMazo(Jugador jugador, List<Carta> mazo) {
        for (Carta c : mazo) {
            jugador.setMazo(c);
        }
    }

    // Elige aleatoriamente quién comienza y lo setea como jugadorActual
    public Jugador elegirJugadorInicial() {
        if (Math.random() < 0.5) {
            return jugador1;
        } else {
            return jugador2;
        }
    }

    // Devuelve el estado textual del tablero (puedes mejorar esto)
    public String mostrarTablero() {
        return tablero.toString(); // O puedes armar un string con el estado de ambos jugadores
    }

    // Hace que el jugador actual robe una carta
    public void robarCartaJugadorActual() {
        jugadorActual.robarCartasMazo(1);
    }

    // Coloca una carta en el tablero (zona de monstruos o magia/trampa)
    public boolean colocarCartaEnTablero(Jugador jugador, Carta carta, int slot) {
        switch (carta.getTipoZona()) {
            case MONSTRUO:
                return tablero.obtenerTablero(jugador).getZonaMonstruos().colocarCartaEnSlot((CartaMonstruo) carta,
                        slot);
            case MAGICA:
                return tablero.obtenerTablero(jugador).getZonaMagiaTrampa().colocarCartaEnSlot(carta, slot);
            case TRAMPA:
                return tablero.obtenerTablero(jugador).getZonaMagiaTrampa().colocarCartaEnSlot(carta, slot);
        }
        return false;
    }

    public Jugador getJugadorActual() {
        return jugadorActual;
    }

    public int getNumeroTurno() {
        return turnoManager.getNumeroTurnoGlobal();
    }

    // Avanza el turno al otro jugador
    public void pasarTurno() {
        if (jugadorActual == jugador1) {
            jugadorActual = jugador2;
        } else {
            jugadorActual = jugador1;
        }
    }

    public Tablero getTablero() {
        return tablero;
    }

    public FaseTurno getFaseActual() {
        return this.turnoManager.getFaseActual();
    }

    public void avanzarFase() {
        this.turnoManager.avanzarFase();
    }

    public boolean irAFaseBatalla() {
        if (turnoManager.getFaseActual() == FaseTurno.PRINCIPAL1) {
            turnoManager.setFaseActual(FaseTurno.BATALLA);
            return true;
        }
        return false;
    }

    // TODO: REVISAR CONDICION DE FINALIZACION
    public boolean juegoFinalizado() {
        return this.jugadorActual.getPuntosDeVida() <= 0 || this.jugador2.getPuntosDeVida() <= 0;
    }

    public Jugador getJugadorOponente() {
        if (jugadorActual == jugador1) {
            return jugador2;
        } else {
            return jugador1;
        }
    }
}
