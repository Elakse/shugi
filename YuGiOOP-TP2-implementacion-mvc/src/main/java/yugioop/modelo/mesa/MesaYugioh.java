package yugioop.modelo.mesa;

import yugioop.modelo.jugador.*;
import yugioop.modelo.tablero.*;
import yugioop.modelo.carta.*;
import yugioop.modelo.turno.TurnoManager;

public class MesaYugioh {
    private ContextoJugador contextoJugador1;
    private ContextoJugador contextoJugador2;
    private TurnoManager turnoManager;

    public MesaYugioh(String nombreJugador1, String nombreJugador2, int tamanioZonas, boolean jugador1Comienza){
        this.contextoJugador1 = new ContextoJugador(nombreJugador1, tamanioZonas);
        this.contextoJugador2 = new ContextoJugador(nombreJugador2, tamanioZonas);
        this.turnoManager = new TurnoManager(contextoJugador1.obtenerJugador(), contextoJugador2.obtenerJugador(), jugador1Comienza);
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

    private TableroJugador obtenerTableroJugadorActual(){
        return obtenerContextoJugadorActual().obtenerTableroJugador();
    }

    private TableroJugador obtenerTableroJugadorOponente(){
        return obtenerContextoJugadorOponente().obtenerTableroJugador();
    }

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

    public void cambiarAtkMontruo(ContextoJugador contexto, int objetivo, int diferencialAtaque){
        contexto.obtenerCartaMonstruo(objetivo).incrementarAtkActual(diferencialAtaque);
    }

    public void reestablecerAtributosMonstruo(ContextoJugador contexto, int objetivo){
        contexto.reestablecerAtributosMonstruo(objetivo);
    }

    public void atacarMonstruo(){

    }

    public void activarCartaMagica(int posicion, int posObjetivo){
        TableroJugador tableroJugadorActual = obtenerTableroJugadorActual();
        CartaMagica carta = tableroJugadorActual.obtenerCartaMagica(posicion);
        carta.activar(this, posObjetivo);
    }


    /*public void activarCartasTrampaActivas(){

    }*/

    public void jugadorActualPierdeVida(int danio){
        obtenerJugadorActual().perderVida(danio);
    }

    public void jugadorActualRobaCartasMazo(int cantidad){
        obtenerJugadorActual().robarCartasMazo(cantidad);
    }

    public void jugadorActualRobaCartasCementerio(int cantidad){
        obtenerJugadorActual().robarCartasCementerio(cantidad);
    }

    public void jugadorActualDescartaACementerio(int indice){
        obtenerJugadorActual().descartarCarta(indice);
    }

    public void jugadorOponentePierdeVida(int danio){
        obtenerJugadorOponente().perderVida(danio);
    }

    public void jugadorOponenteRobaCartasMazo(int cantidad){
        obtenerJugadorOponente().robarCartasMazo(cantidad);
    }

    public void jugadorOponenteDescartaACementerio(int indice){
        obtenerJugadorOponente().descartarCarta(indice);
    }

}
