package yugioop.controlador;

import yugioop.modelo.jugador.Jugador;
import yugioop.modelo.tablero.Tablero;
import yugioop.modelo.turno.FaseTurno;
import yugioop.modelo.turno.TurnoManager;
import yugioop.modelo.carta.CartaMonstruo;
import yugioop.modelo.carta.CartaMagica;
import yugioop.modelo.carta.CartaTrampa;

/**
 * Controlador principal del juego Yu-Gi-Oh.
 * Coordina la interacción entre el tablero y el sistema de turnos.
 */
public class ControladorJuego {
    private Tablero tablero;
    private TurnoManager turnoManager;
    
    /**
     * Constructor del controlador.
     * @param jugador1 Primer jugador.
     * @param jugador2 Segundo jugador.
     */
    public ControladorJuego(Jugador jugador1, Jugador jugador2) {
        this.tablero = new Tablero(jugador1, jugador2);
        boolean jugador1Comienza = decidirJugadorInicial();
        this.turnoManager = new TurnoManager(jugador1, jugador2, jugador1Comienza);
    }
    
    /**
     * Decide aleatoriamente qué jugador comienza el juego.
     * @return true si el jugador 1 comienza, false si el jugador 2 comienza.
     */
    private boolean decidirJugadorInicial() {
        return new java.util.Random().nextBoolean();
    }
    
    /**
     * Obtiene el nombre del jugador que comienza la partida.
     * @return Nombre del jugador que comienza.
     */
    public String getJugadorInicial() {
        return turnoManager.getJugadorActual().getNombre();
    }
    
    /**
     * Inicia el juego.
     */
    public void iniciarJuego() {
        System.out.println("¡Juego iniciado!");
        System.out.println("Es el turno de " + turnoManager.getJugadorActual().getNombre());
    }
    
    /**
     * Avanza a la siguiente fase del turno.
     * @return true si se avanzó correctamente, false si no.
     */
    public boolean avanzarFase() {
        return turnoManager.avanzarFase();
    }
    
    /**
     * El jugador actual decide ir a la fase de batalla.
     * @return true si se pudo ir a batalla, false si no.
     */
    public boolean irAFaseBatalla() {
        return turnoManager.jugadorEligeIrABatalla();
    }
    
    /**
     * El jugador actual decide saltar la fase de batalla.
     * @return true si se pudo saltar la batalla, false si no.
     */
    public boolean saltarFaseBatalla() {
        return turnoManager.jugadorEligeTerminarPrincipal1SinBatalla();
    }
    
    /**
     * Invoca un monstruo desde la mano al campo.
     * @param cartaMonstruo La carta de monstruo a invocar.
     * @param posicionAtaque true para posición de ataque, false para defensa.
     * @param bocaArriba true para boca arriba, false para boca abajo.
     * @return true si se pudo invocar, false si no.
     */
    public boolean invocarMonstruo(CartaMonstruo cartaMonstruo, boolean posicionAtaque, boolean bocaArriba) {
        if (!turnoManager.puedeInvocarMonstruo()) {
            System.out.println("No puedes invocar monstruos en esta fase del turno.");
            return false;
        }
        
        // TODO:
        // tablero.invocarMonstruo(turnoManager.getJugadorActual(), cartaMonstruo, posicionAtaque, bocaArriba);
        
        turnoManager.registrarInvocacionNormal();
        System.out.println(turnoManager.getJugadorActual().getNombre() + " invocó un monstruo.");
        return true;
    }
    
    /**
     * Activa una carta mágica desde la mano.
     * @param cartaMagica La carta mágica a activar.
     * @return true si se pudo activar, false si no.
     */
    public boolean activarCartaMagica(CartaMagica cartaMagica) {
        if (!turnoManager.puedeActivarCartaMagicaOTrampa()) {
            System.out.println("No puedes activar cartas mágicas en esta fase del turno.");
            return false;
        }
        
        // TODO:
        // tablero.activarCartaMagica(turnoManager.getJugadorActual(), cartaMagica);
        
        if (esCartaCampo(cartaMagica)) {
            turnoManager.registrarActivacionCartaCampo();
        }
        
        System.out.println(turnoManager.getJugadorActual().getNombre() + " activó una carta mágica.");
        return true;
    }
    
    /**
     * Coloca una carta de trampa boca abajo.
     * @param cartaTrampa La carta de trampa a colocar.
     * @return true si se pudo colocar, false si no.
     */
    public boolean colocarCartaTrampa(CartaTrampa cartaTrampa) {
        if (!turnoManager.puedeColocarCartas()) {
            System.out.println("No puedes colocar cartas de trampa en esta fase del turno.");
            return false;
        }
        
        // TODO:
        // tablero.colocarCartaTrampa(turnoManager.getJugadorActual(), cartaTrampa);
        
        System.out.println(turnoManager.getJugadorActual().getNombre() + " colocó una carta boca abajo.");
        return true;
    }
    
    /**
     * Alterna la posición de un monstruo en el campo (de ataque a defensa o viceversa).
     * @param cartaMonstruo La carta de monstruo cuya posición se va a alternar.
     * @return true si se pudo alternar la posición, false si no.
     */
    public boolean alternarPosicionMonstruo(CartaMonstruo cartaMonstruo) {
        if (!turnoManager.puedeAlternarPosiciones()) {
            System.out.println("No puedes alternar posiciones en esta fase del turno.");
            return false;
        }
        
        // TODO:
        // tablero.alternarPosicionMonstruo(turnoManager.getJugadorActual(), cartaMonstruo);
        
        System.out.println(turnoManager.getJugadorActual().getNombre() + " alternó la posición de un monstruo.");
        return true;
    }
    
    /**
     * Declara un ataque de un monstruo a otro.
     * @param monstruoAtacante El monstruo que ataca.
     * @param monstruoDefensor El monstruo que defiende.
     * @return true si se pudo atacar, false si no.
     */
    public boolean declararAtaque(CartaMonstruo monstruoAtacante, CartaMonstruo monstruoDefensor) {
        if (turnoManager.getFaseActual() != FaseTurno.BATALLA) {
            System.out.println("No puedes atacar fuera de la fase de batalla.");
            return false;
        }
        
        // TODO:
        // tablero.resolverAtaque(monstruoAtacante, monstruoDefensor);
        
        System.out.println(turnoManager.getJugadorActual().getNombre() + " declaró un ataque.");
        return true;
    }
    
    // Método auxiliar para comprobar si una carta mágica es una carta de campo
    private boolean esCartaCampo(CartaMagica cartaMagica) {
        return false; 
    }
    
    /**
     * Finaliza el turno actual.
     */
    public void finalizarTurno() {
        turnoManager.finalizarTurnoActual();
    }
    
    /**
     * Obtiene la fase actual del turno.
     * @return La fase actual.
     */
    public FaseTurno getFaseActual() {
        return turnoManager.getFaseActual();
    }
    
    /**
     * Obtiene el jugador actual.
     * @return El jugador actual.
     */
    public Jugador getJugadorActual() {
        return turnoManager.getJugadorActual();
    }
    
    /**
     * Obtiene el jugador oponente.
     * @return El jugador oponente.
     */
    public Jugador getJugadorOponente() {
        return turnoManager.getJugadorOponente();
    }
}