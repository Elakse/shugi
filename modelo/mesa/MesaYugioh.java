package yugioop.modelo.mesa;

import java.util.List;
import java.util.Optional;
import yugioop.modelo.jugador.*;
import yugioop.modelo.carta.*;
import yugioop.modelo.carta.trampas.*;
import yugioop.modelo.turno.*;

public class MesaYugioh {
    private ContextoJugador contextoJugador1;
    private ContextoJugador contextoJugador2;
    private TurnoManager turnoManager;

    public MesaYugioh(String nombreJugador1, String nombreJugador2, Integer tamanioZonas, boolean jugador1Comienza){
        this.contextoJugador1 = new ContextoJugador(nombreJugador1, tamanioZonas);
        this.contextoJugador2 = new ContextoJugador(nombreJugador2, tamanioZonas);
        this.turnoManager = new TurnoManager(contextoJugador1.obtenerJugador(), contextoJugador2.obtenerJugador(), jugador1Comienza);
    }

    public void iniciarTurno(){
        turnoManager.iniciarTurno(turnoManager.getJugadorActual());
    }

    public void finalizarTurno(){
        turnoManager.avanzarTurno();
    }

    public boolean esPrimerTurno(){
        return turnoManager.getNumeroTurnoGlobal() == 1;
    }

    public FaseTurno obtenerFaseActual(){
        return turnoManager.getFaseActual();
    }

    public Integer obtenerCantSacrificiosMonstruo(Integer posicion){
        return obtenerContextoJugadorActual().obtenerCantSacrificiosMonstruo(posicion);
    }

    public String obtenerNombreJugadorActual(){
        return obtenerJugadorActual().getNombre();
    }

    public String obtenerNombreJugadorOponente(){
        return obtenerJugadorOponente().getNombre();
    }

    public boolean jugadorActualEstaVivo(Jugador jugador){
        return obtenerJugadorActual().estaVivo();
    }

    public boolean jugadorOponenteEstaVivo(Jugador jugador){
        return obtenerJugadorOponente().estaVivo();
    }

    public Integer obtenerVidaJugadorActual(){
        return obtenerJugadorActual().getPuntosDeVida();
    }

    public Integer obtenerVidaJugadorOponente(){
        return obtenerJugadorOponente().getPuntosDeVida();
    }

    public Integer obtenerCantCartasManoJugadorActual(){
        return obtenerJugadorActual().getCantCartasEnMano();
    }

    public Integer obtenerCantCartasManoJugadorOponente(){
        return obtenerJugadorOponente().getCantCartasEnMano();
    }

    public void asignarMazos(Mazo mazo1, Mazo mazo2){
        asignarMazoJugadorActual(mazo1);
        asignarMazoJugadorOponente(mazo2);
    }

    private void asignarMazoJugadorActual(Mazo mazo){
        obtenerJugadorActual().setMazo(mazo);
    }

    private void asignarMazoJugadorOponente(Mazo mazo){
        obtenerJugadorOponente().setMazo(mazo);
    }

    private Jugador obtenerJugadorActual(){
        return turnoManager.getJugadorActual();
    }

    private Jugador obtenerJugadorOponente(){
        return turnoManager.getJugadorOponente();
    }

    /*private TableroJugador obtenerTableroJugadorActual(){
        return obtenerContextoJugadorActual().obtenerTableroJugador();
    }*/

    /*private TableroJugador obtenerTableroJugadorOponente(){
        return obtenerContextoJugadorOponente().obtenerTableroJugador();
    }*/

    public ContextoJugador obtenerContextoJugadorActual(){
        Jugador jugadorActual = obtenerJugadorActual();
        if(contextoJugador1.obtenerJugador().equals(jugadorActual)){
            return contextoJugador1;
        } else {
            return contextoJugador2;
        }
    }

    public ContextoJugador obtenerContextoJugadorOponente(){
        Jugador jugadorOponente = obtenerJugadorOponente();
        if(contextoJugador1.obtenerJugador().equals(jugadorOponente)){
            return contextoJugador1;
        } else {
            return contextoJugador2;
        }
    }

    public List<String> obtenerNombresCartasMano(){
        //
    }

    public void cambiarAtkMontruo(ContextoJugador contexto, Integer objetivo, Integer diferencialAtaque){
        contexto.obtenerCartaMonstruo(objetivo).incrementarAtkActual(diferencialAtaque);
    }

    public void jugadorActualColocaMonstruo(Integer indice, Integer posicion){
        CartaMonstruo monstruo = obtenerContextoJugadorActual().obtenerCartaMonstruoMano(indice);
        obtenerContextoJugadorActual().colocarCartaMonstruo(monstruo, posicion);
    }

    public void jugadorActualColocaMagica(Integer indice, Integer posicion){
        CartaMagica magica = obtenerContextoJugadorActual().obtenerCartaMagicaMano(indice);
        obtenerContextoJugadorActual().colocarCartaMagica(magica, posicion);
    }

    public void jugadorActualColocaTrampa(Integer indice, Integer posicion){
        CartaTrampa trampa = obtenerContextoJugadorActual().obtenerCartaTrampaMano(indice);
        obtenerContextoJugadorActual().colocarCartaTrampa(trampa, posicion);
    }

    public void reestablecerAtributosMonstruo(ContextoJugador contexto, Integer objetivo){
        contexto.reestablecerAtributosMonstruo(objetivo);
    }

    public void atacarMonstruo(Integer posicion, Integer posObjetivo){
        obtenerContextoJugadorActual().atacarMonstruo(this, posicion, posObjetivo);
    }

    public void procesarCartasMagicasActivasJugadorActual(){
        obtenerContextoJugadorActual().activarCartasMagicasActivas(this);
    }

    public void activarCartaMagica(Integer posicion, Optional<Integer> posObjetivo) {
        obtenerContextoJugadorActual().activarCartaMagica(this, posicion, posObjetivo);
    }

    public boolean cartaMagicaRequiereObjetivo(Integer posicion){
        return obtenerContextoJugadorActual().cartaMagicaRequiereObjetivo(posicion);
    }

    public void activarCartasTrampa(Evento evento){
        contextoJugador1.activarCartasTrampa(this, evento);
        contextoJugador2.activarCartasTrampa(this, evento);
    }

    public void jugadorActualPierdeVida(Integer danio){
        obtenerJugadorActual().perderVida(danio);
    }

    public void jugadorActualRobaCartasMazo(Integer cantidad){
        obtenerJugadorActual().robarCartasMazo(cantidad);
    }

    public void jugadorActualRobaCartasCementerio(Integer cantidad){
        obtenerJugadorActual().robarCartasCementerio(cantidad);
    }

    public void jugadorActualDescartaACementerio(Integer indice){
        obtenerJugadorActual().descartarCarta(indice);
    }

    public void jugadorOponentePierdeVida(Integer danio){
        obtenerJugadorOponente().perderVida(danio);
    }

    public void jugadorActualGanaVida(Integer vida){
        obtenerJugadorActual().ganarVida(vida);
    }

    public void jugadorOponenteGanaVida(Integer vida){
        obtenerJugadorOponente().ganarVida(vida);
    }

    public void jugadorOponenteRobaCartasMazo(Integer cantidad){
        obtenerJugadorOponente().robarCartasMazo(cantidad);
    }

    public void jugadorOponenteDescartaACementerio(Integer indice){
        obtenerJugadorOponente().descartarCarta(indice);
    }

    public void jugadorActualDescartaCartasAleatorias(int cantidad) {
        obtenerContextoJugadorActual().descartarCartasAleatorias(cantidad);
    }

    public void jugadorOponenteDescartaCartasAleatorias(int cantidad) {
        obtenerContextoJugadorOponente().descartarCartasAleatorias(cantidad);
    }

    public void jugadorActualActivarCartasMagicasActivas(){
        obtenerContextoJugadorActual().activarCartasMagicasActivas(this);
    }
    
}
