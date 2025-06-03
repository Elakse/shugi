package yugioop.modelo.turno;

import yugioop.modelo.jugador.Jugador;

public class TurnoManager {
    private Jugador jugadorActual;
    private Jugador jugadorOponente;
    private FaseTurno faseActual;
    private int numeroTurnoGlobal;
    private Jugador jugadorQueEmpezoPartida;
    private boolean invocacionNormalRealizada;
    private boolean cartaCampoActivadaEsteTurno;

    /**
     * Constructor del TurnoManager.
     * @param jugador1 El primer jugador.
     * @param jugador2 El segundo jugador.
     * @param jugador1Comienza true si el jugador 1 comienza, false si el jugador 2 comienza.
     */
    public TurnoManager(Jugador jugador1, Jugador jugador2, boolean jugador1Comienza) {
        if (jugador1Comienza) {
            this.jugadorActual = jugador1;
            this.jugadorOponente = jugador2;
            this.jugadorQueEmpezoPartida = jugador1;
        } else {
            this.jugadorActual = jugador2;
            this.jugadorOponente = jugador1;
            this.jugadorQueEmpezoPartida = jugador2;
        }
        this.numeroTurnoGlobal = 1;
        iniciarTurno(); // Usa el método existente para evitar duplicación
    }

    public Jugador getJugadorActual() {
        return jugadorActual;
    }

    public Jugador getJugadorOponente() {
        return jugadorOponente;
    }

    public FaseTurno getFaseActual() {
        return faseActual;
    }

    public int getNumeroTurnoGlobal() {
        return numeroTurnoGlobal;
    }

    public boolean seRealizoInvocacionNormal() {
        return invocacionNormalRealizada;
    }

    public boolean seActivoCartaCampo() {
        return cartaCampoActivadaEsteTurno;
    }

    public void registrarInvocacionNormal() {
        this.invocacionNormalRealizada = true;
    }

    public void registrarActivacionCartaCampo() {
        this.cartaCampoActivadaEsteTurno = true;
    }

    /**
     * Muestra un mensaje sobre la fase actual del jugador.
     * @param accion Opcional: descripción de la acción que llevó al cambio de fase.
     */
    private void mostrarCambioFase(String accion) {
        String mensaje = accion != null 
            ? jugadorActual.getNombre() + " " + accion + " y ahora está en fase " + faseActual
            : jugadorActual.getNombre() + " ahora está en fase " + faseActual;
        System.out.println(mensaje);
    }

    /**
     * Avanza a la siguiente fase del turno según el flujo normal.
     * @return true si la fase cambió, false si no.
     */
    public boolean avanzarFase() {
        FaseTurno faseAnterior = faseActual;
        
        switch (faseActual) {
            case DRAW:
                faseActual = FaseTurno.STANDBY;
                break;
            case STANDBY:
                faseActual = FaseTurno.PRINCIPAL1;
                break;
            case PRINCIPAL1:
                if (esPrimerTurnoJugadorInicial()) {
                    faseActual = FaseTurno.CAMBIO; // Salta batalla en primer turno
                } else {
                    faseActual = FaseTurno.BATALLA;
                }
                break;
            case BATALLA:
                faseActual = FaseTurno.CAMBIO;
                break;
            case CAMBIO:
                faseActual = FaseTurno.FINAL;
                break;
            case FINAL:
                finalizarTurnoActual();
                break;
        }
        
        if (faseAnterior != faseActual) {
            mostrarCambioFase(null);
            return true;
        }
        return false;
    }

    /**
     * Verifica si es el primer turno del jugador que inició la partida.
     */
    private boolean esPrimerTurnoJugadorInicial() {
        return numeroTurnoGlobal == 1 && jugadorActual == jugadorQueEmpezoPartida;
    }

    /**
     * Finaliza el turno actual, cambiando al otro jugador y reiniciando la fase.
     */
    public void finalizarTurnoActual() {
        // Cambia de jugador
        Jugador temp = jugadorActual;
        jugadorActual = jugadorOponente;
        jugadorOponente = temp;
        
        // Incrementa el contador de turnos
        numeroTurnoGlobal++;
        System.out.println("Turno finalizado. Ahora es el turno de " + jugadorActual.getNombre());
        
        // Reinicia fase y flags
        iniciarTurno();
    }

    /**
     * Inicia un nuevo turno para el jugador actual.
     * Resetea la fase a DRAW y las banderas de invocación normal y activación de campo.
     */
    public void iniciarTurno() {
        this.faseActual = FaseTurno.DRAW;
        this.invocacionNormalRealizada = false;
        this.cartaCampoActivadaEsteTurno = false;
    }

    /**
     * El jugador elige pasar a la fase de batalla.
     * Solo se puede hacer desde la fase PRINCIPAL1.
     */
    public boolean jugadorEligeIrABatalla() {
        if (this.faseActual != FaseTurno.PRINCIPAL1) {
            System.out.println("No se puede ir a Fase de Batalla desde " + this.faseActual);
            return false;
        }
        
        if (esPrimerTurnoJugadorInicial()) {
            this.faseActual = FaseTurno.CAMBIO;
            mostrarCambioFase("no puede entrar a Fase de Batalla en el primer turno del juego");
        } else {
            this.faseActual = FaseTurno.BATALLA;
            mostrarCambioFase("ha entrado en la fase de batalla");
        }
        return true;
    }

    /**
     * El jugador elige terminar su turno sin realizar una batalla.
     * Solo se puede hacer desde la fase PRINCIPAL1.
     */
    public boolean jugadorEligeTerminarPrincipal1SinBatalla() {
        if (this.faseActual != FaseTurno.PRINCIPAL1) {
            System.out.println("No se puede saltar la batalla desde " + this.faseActual);
            return false;
        }
        
        this.faseActual = FaseTurno.CAMBIO;
        mostrarCambioFase("ha saltado la fase de batalla");
        return true;
    }

    /**
     * Verifica si se puede invocar un monstruo en la fase actual.
     * @return true si estamos en PRINCIPAL1, false en caso contrario
     */
    public boolean puedeInvocarMonstruo() {
        return faseActual == FaseTurno.PRINCIPAL1 && !seRealizoInvocacionNormal();
    }

    /**
     * Verifica si se pueden activar cartas mágicas o de trampa.
     * @return true si estamos en PRINCIPAL1, false en caso contrario
     */
    public boolean puedeActivarCartaMagicaOTrampa() {
        return faseActual == FaseTurno.PRINCIPAL1;
    }

    /**
     * Verifica si se pueden colocar cartas (hechizo/trampa).
     * @return true si estamos en PRINCIPAL1, false en caso contrario
     */
    public boolean puedeColocarCartas() {
        return faseActual == FaseTurno.PRINCIPAL1;
    }

    /**
     * Verifica si se pueden alternar posiciones de las cartas en el campo.
     * @return true si estamos en la fase CAMBIO, false en caso contrario
     */
    public boolean puedeAlternarPosiciones() {
        return faseActual == FaseTurno.CAMBIO;
    }

}