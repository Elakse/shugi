package yugioop.modelo.turno;

import yugioop.modelo.jugador.Jugador;

public class TurnoManager {
    private Jugador jugadorActual;
    private Jugador jugadorOponente;
    private FaseTurno faseActual;
    private int NumTurno;
    private Jugador jugadorInicial;
    private boolean invocacionNormalRealizada;
    private boolean cartaCampoActivadaEsteTurno;

    public TurnoManager(Jugador jugador1, Jugador jugador2, boolean jugador1Comienza) {
        if (jugador1Comienza) {
            this.jugadorActual = jugador1;
            this.jugadorOponente = jugador2;
            this.jugadorInicial = jugador1;
        } else {
            this.jugadorActual = jugador2;
            this.jugadorOponente = jugador1;
            this.jugadorInicial = jugador2;
        }
        this.NumTurno = 1;
        iniciarTurno(jugadorActual); 
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
        return NumTurno;
    }

    public boolean seRealizoInvocacionNormal() {
        return invocacionNormalRealizada;
    }

    public boolean seActivoCartaCampo() {
        return cartaCampoActivadaEsteTurno;
    }

    public void setJugadorActual(Jugador jugadorActual) {
        this.jugadorActual = jugadorActual;
    }

    public void setJugadorOponente(Jugador jugadorOponente) {
        this.jugadorOponente = jugadorOponente;
    }
    /**
     * Registra que el jugador actual ha realizado una invocación normal este turno.
     * Solo se permite una invocación normal por turno 
     */
    public void registrarInvocacionNormal() {
        this.invocacionNormalRealizada = true;
    }

    /**
     * Registra que el jugador actual ha activado una carta de campo este turno.
     * Solo se permite activar una carta de campo por turno.
     */
    public void registrarActivacionCartaCampo() {
        this.cartaCampoActivadaEsteTurno = true;
    }

    /**
     * Avanza a la siguiente fase del turno según el flujo normal de Yu-Gi-Oh:
     * DRAW → STANDBY → PRINCIPAL1 → CAMBIO → FINAL → (siguiente jugador)
     * Cuando se llega a FINAL, se pasa el turno al otro jugador y se reinician banderas.
     * @return true si la fase cambió, false si no cambió.
     */
    public boolean avanzarFase() {
        FaseTurno faseAnterior = faseActual;
        
        switch (faseActual) {
            case FINAL:
                finalizarTurnoActual();
                break;
            case PRINCIPAL1:
                if (puedeElegirBatalla()) {
                    faseActual = FaseTurno.BATALLA;
                } else {
                    faseActual = FaseTurno.CAMBIO;
                }
                break;
            default:
                faseActual = faseActual.siguiente();
                break;
            }
            return faseAnterior != faseActual;
    }

    /**
     * Finaliza el turno actual, cambiando al otro jugador y reiniciando la fase.
     * Reinicia las banderas de acciones por turno.
     */
    public void finalizarTurnoActual() {
        Jugador temp = jugadorActual;
        jugadorActual = jugadorOponente;
        jugadorOponente = temp;
        
        NumTurno++;
        iniciarTurno(jugadorActual);
    }

    /**
     * Inicia un nuevo turno para el jugador actual.
     * Resetea la fase a DRAW y las banderas de invocación normal y activación de campo.
     * @param jugador El jugador para el que se inicia el turno.
     */ 
    public void iniciarTurno(Jugador jugador) {
        this.faseActual = FaseTurno.DRAW;
        this.invocacionNormalRealizada = false;
        this.cartaCampoActivadaEsteTurno = false;
    }
    
    /**
     * Verifica si se puede invocar un monstruo en la fase actual.
     * Solo puede hacerse en fase PRINCIPAL1 y una sola vez por turno.
     * @return true si es fase PRINCIPAL1 y no se ha invocado un monstruo este turno.
     */
    public boolean puedeInvocarMonstruo() {
        return faseActual == FaseTurno.PRINCIPAL1 && !seRealizoInvocacionNormal();
    }

    /**
     * Verifica si se pueden activar cartas mágicas o de trampa.
     * Solo pueden activarse en fase PRINCIPAL1 (aunque algunas trampas 
     * podrían activarse en otras fases, pero eso se maneja con la lógica de la carta).
     * @return true si estamos en PRINCIPAL1, false en caso contrario.
     */
    public boolean puedeActivarCartaMagicaOTrampa() {
        return faseActual == FaseTurno.PRINCIPAL1;
    }

    /**
     * Verifica si se pueden colocar cartas bocabajo (hechizo/trampa) en el campo.
     * Solo se permite en fase PRINCIPAL1.
     * @return true si estamos en PRINCIPAL1, false en caso contrario.
     */
    public boolean puedeColocarCartas() {
        return faseActual == FaseTurno.PRINCIPAL1;
    }

    /**
     * Verifica si se pueden alternar posiciones de los monstruos en el campo (ataque/defensa).
     * Solo se permite en fase CAMBIO.
     * @return true si estamos en la fase CAMBIO, false en caso contrario.
     */
    public boolean puedeAlternarPosiciones() {
        return faseActual == FaseTurno.CAMBIO;
    }

    /**
     * Verifica si el jugador actual puede elegir ir a batalla.
     * En el primer turno, el jugador que comienza no puede ir a batalla.
     * @return true si el jugador puede ir a batalla, false en caso contrario.
     */
    public boolean puedeElegirBatalla() {
        return !(NumTurno == 1 && jugadorActual == jugadorInicial);
    }

    /**
     * Verifica si un monstruo puede atacar en la fase actual.
     * Solo pueden atacar los monstruos en posición de ataque y en fase de BATALLA.
     * @param estaEnModoAtaque true si el monstruo está en modo ataque
     * @param yaAtacoEsteTurno true si el monstruo ya atacó en este turno
     * @return true si el monstruo puede atacar, false en caso contrario
     */
    public boolean puedeAtacarMonstruo(boolean estaEnModoAtaque, boolean yaAtacoEsteTurno) {
        return estaEnModoAtaque && faseActual == FaseTurno.BATALLA && !yaAtacoEsteTurno;
    }

    /**
     * El jugador puede saltar la fase BATALLA e ir a CAMBIO.
     * Solo puede hacerse en la fase PRINCIPAL1 y si no se invoco monstruo este turno.
     */
    public void saltarBatalla() {
        if (faseActual == FaseTurno.PRINCIPAL1) {
            faseActual = FaseTurno.CAMBIO;
        }
    }

    public void avanzarTurno() {
        finalizarTurnoActual();
    }

    public void setFaseActual(FaseTurno faseActual) {
        this.faseActual = faseActual;
    }
}