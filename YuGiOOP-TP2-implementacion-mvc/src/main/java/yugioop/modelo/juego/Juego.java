package yugioop.modelo.juego;

import yugioop.modelo.carta.Carta;
import yugioop.modelo.jugador.Jugador;
import yugioop.modelo.tablero.Tablero;
import yugioop.modelo.turno.TurnoManager;
import yugioop.modelo.batalla.SistemaBatalla;
import yugioop.modelo.carta.condicion.TipoEventoDeJuego; // Asumiendo que lo crearé

import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class Juego {
    private Jugador jugador1;
    private Jugador jugador2;
    private Tablero tablero;
    private TurnoManager turnoManager;
    private SistemaBatalla sistemaBatalla;
    private boolean juegoTerminado;
    private Jugador ganador;

    private static final int PUNTOS_DE_VIDA_INICIALES = 8000;
    private static final int CARTAS_INICIALES_MANO = 5;
    private static final int TAMANIO_MAXIMO_MANO_FIN_TURNO = 6;

    public Juego(String nombreJugador1, String nombreJugador2 /*, List<Carta> mazoJ1, List<Carta> mazoJ2 */) {
        // //TODO: La carga de mazos desde archivo y asignación a Jugador se hará externamente o en una fase previa.
        // // Por ahora, se asume que los Jugadores son creados con sus mazos y LP.
        // // Ejemplo de cómo se podría integrar (requiere que Jugador tenga estos métodos):
        // this.jugador1 = new Jugador(nombreJugador1);
        // this.jugador1.setLp(PUNTOS_DE_VIDA_INICIALES);
        // this.jugador1.setMazo(mazoJ1); // mazoJ1 debería ser una instancia de Mazo
        // this.jugador1.barajarMazo();

        // this.jugador2 = new Jugador(nombreJugador2);
        // this.jugador2.setLp(PUNTOS_DE_VIDA_INICIALES);
        // this.jugador2.setMazo(mazoJ2); // mazoJ2 debería ser una instancia de Mazo
        // this.jugador2.barajarMazo();
        
        // Simplificación basada en el código de Jugador provisto (solo tiene nombre):
        this.jugador1 = new Jugador(nombreJugador1);
        this.jugador2 = new Jugador(nombreJugador2);
        // //TODO: Faltaría inicializar LP, Mazo, Mano, Cementerio en Jugador.
        // // Estos System.out.println son para simular la inicialización que haría la clase Jugador.
        System.out.println(jugador1.getNombre() + " LP: " + PUNTOS_DE_VIDA_INICIALES);
        System.out.println(jugador2.getNombre() + " LP: " + PUNTOS_DE_VIDA_INICIALES);


        this.tablero = new Tablero(jugador1, jugador2);
        this.sistemaBatalla = new SistemaBatalla(this.tablero);

        boolean j1Comienza = new Random().nextBoolean();
        this.turnoManager = new TurnoManager(jugador1, jugador2, j1Comienza);

        this.juegoTerminado = false;
        this.ganador = null;
    }

    public void iniciarPartida() {
        System.out.println("Iniciando YuGiOOP!");
        System.out.println(jugador1.getNombre() + " vs " + jugador2.getNombre());

        // Repartir cartas iniciales
        // //TODO: Implementar en Jugador: robarCartaDelMazo() y agregarCartaALaMano()
        System.out.println("Repartiendo cartas iniciales...");
        for (int i = 0; i < CARTAS_INICIALES_MANO; i++) {
            // if (!jugador1.robarCartaDelMazo()) { /* manejar deckout */ } // Ejemplo
            // if (!jugador2.robarCartaDelMazo()) { /* manejar deckout */ } // Ejemplo
        }
        System.out.println(jugador1.getNombre() + " roba " + CARTAS_INICIALES_MANO + " cartas.");
        System.out.println(jugador2.getNombre() + " roba " + CARTAS_INICIALES_MANO + " cartas.");


        System.out.println("El jugador que comienza es: " + turnoManager.getJugadorActual().getNombre());
        gestionarInicioTurno();
    }

    private void gestionarInicioTurno() {
        if (juegoTerminado) return;
        turnoManager.iniciarTurno(); // Resetea fase a DRAW y flags del TurnoManager
        tablero.iniciarTurno(getJugadorActual()); // Resetea flags del Tablero (ej. invocación normal)
        // //TODO: En Jugador: resetear flags de monstruos (haAtacado, puedeCambiarPosicion)
        // getJugadorActual().resetearEstadoMonstruosInicioTurno();

        System.out.println("--- Turno " + turnoManager.getNumeroTurnoGlobal() + " - Jugador: " + getJugadorActual().getNombre() + " ---");
        gestionarFaseRobo();
    }

    private void gestionarFaseRobo() {
        if (juegoTerminado) return;
        System.out.println("Fase de Robo para " + getJugadorActual().getNombre());
        // //TODO: Implementar en Jugador: robarCartaDelMazo()
        // boolean pudoRobar = getJugadorActual().robarCartaDelMazo();
        boolean pudoRobar = true; // Placeholder
        if (!pudoRobar) {
            finalizarJuego(getOponente(), getJugadorActual().getNombre() + " no pudo robar carta (Deck Out).");
        } else {
            System.out.println(getJugadorActual().getNombre() + " roba 1 carta.");
            // turnoManager.avanzarFase(); // Avanza a STANDBY
        }
    }

    public void avanzarFase() {
        if (juegoTerminado) return;
        boolean cambioDeTurno = !turnoManager.avanzarFase(); // avanzarFase devuelve false si se finalizó el turno
        if (cambioDeTurno) {
             gestionarFinDeTurnoLogica(); // Lógica de fin de turno antes de que el TurnoManager cambie de jugador
             turnoManager.finalizarTurnoActual(); // Cambia de jugador y reinicia la fase a DRAW para el nuevo jugador
             gestionarInicioTurno(); // Prepara el nuevo turno
        } else {
             System.out.println("Nueva fase: " + turnoManager.getFaseActual() + " para " + getJugadorActual().getNombre());
             // //TODO: Lógica específica de inicio de cada fase (Standby, Principal1, etc.)
        }
    }


    private void gestionarFinDeTurnoLogica() {
        if (juegoTerminado) return;
        Jugador jugadorQueTerminaTurno = getJugadorActual();
        System.out.println("Finalizando turno para " + jugadorQueTerminaTurno.getNombre());

        // //TODO: En Jugador: verificarYDescartarExcesoDeMano(TAMANIO_MAXIMO_MANO_FIN_TURNO)
        // jugadorQueTerminaTurno.verificarYDescartarExcesoDeMano(TAMANIO_MAXIMO_MANO_FIN_TURNO);
        System.out.println(jugadorQueTerminaTurno.getNombre() + " verifica el límite de mano (" + TAMANIO_MAXIMO_MANO_FIN_TURNO + " cartas).");


        // //TODO: Resolver efectos que ocurren al final del turno (ej. decremento de contadores de cartas continuas/equipo)
        // // Esto podría involucrar iterar sobre las cartas en el campo del jugador actual y del oponente.
        // tablero.resolverEfectosDeFinDeTurno(jugadorQueTerminaTurno);
    }


    public void verificarCondicionesDeVictoriaGeneral() {
        if (juegoTerminado) return;

        // //TODO: En Jugador: getLp()
        // if (jugador1.getLp() <= 0) {
        //     finalizarJuego(jugador2, jugador1.getNombre() + " se quedó sin Life Points.");
        //     return;
        // }
        // if (jugador2.getLp() <= 0) {
        //     finalizarJuego(jugador1, jugador2.getNombre() + " se quedó sin Life Points.");
        //     return;
        // }
        // La condición de Deck Out se maneja en gestionarFaseRobo.
    }

    public void jugadorSeRinde(Jugador jugadorQueSeRinde) {
        if (juegoTerminado) return;
        if (jugadorQueSeRinde.equals(jugador1)) {
            finalizarJuego(jugador2, jugador1.getNombre() + " se ha rendido.");
        } else if (jugadorQueSeRinde.equals(jugador2)) {
            finalizarJuego(jugador1, jugador2.getNombre() + " se ha rendido.");
        }
    }

    private void finalizarJuego(Jugador jugadorGanador, String motivo) {
        if (this.juegoTerminado) return; // Evitar múltiples finalizaciones
        this.juegoTerminado = true;
        this.ganador = jugadorGanador;
        System.out.println("==========================================");
        System.out.println("¡JUEGO TERMINADO!");
        System.out.println("Ganador: " + (this.ganador != null ? this.ganador.getNombre() : "Empate/Desconocido"));
        System.out.println("Motivo: " + motivo);
        System.out.println("==========================================");
    }

    // Getters
    public Jugador getJugador1() { return jugador1; }
    public Jugador getJugador2() { return jugador2; }
    public Tablero getTablero() { return tablero; }
    public TurnoManager getTurnoManager() { return turnoManager; }
    public SistemaBatalla getSistemaBatalla() { return sistemaBatalla; }
    public Jugador getJugadorActual() { return turnoManager.getJugadorActual(); }
    public Jugador getOponente() { return turnoManager.getJugadorOponente(); }
    public boolean isJuegoTerminado() { return juegoTerminado; }
    public Jugador getGanador() { return ganador; }

    // //TODO: El Tablero o un ControladorEventos debería llamar a este método cuando un evento ocurre.
    // public void notificarEvento(TipoEventoDeJuego tipoEvento, Jugador jugadorAccion) {
    //     if (juegoTerminado) return;
    //     Jugador jugadorRespuesta = (jugadorAccion == jugador1) ? jugador2 : jugador1;
    //     tablero.verificarTrampasActivables(tipoEvento, jugadorRespuesta, jugadorAccion);
    // }
}