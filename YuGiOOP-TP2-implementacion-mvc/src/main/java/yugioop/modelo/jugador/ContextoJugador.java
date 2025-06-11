package yugioop.modelo.jugador;

import yugioop.modelo.tablero.TableroJugador;
import yugioop.modelo.carta.*;

public class ContextoJugador {
    Jugador jugador;
    TableroJugador tableroJugador;

    public ContextoJugador(String nombre, int tamanioZonas) {
        this.jugador = new Jugador(nombre);
        this.tableroJugador = new TableroJugador(tamanioZonas);
    }

    public Jugador obtenerJugador() {
        return jugador;
    }

    public TableroJugador obtenerTableroJugador() {
        return tableroJugador;
    }

    public void asignarJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public void asignarTableroJugador(TableroJugador tableroJugador) {
        this.tableroJugador = tableroJugador;
    }

    public void robarCartasMazo(int cantidad){
        jugador.robarCartasMazo(cantidad);
    }

    public void robarCartaCementerio(int cantidad){
        jugador.robarCartasCementerio(cantidad);
    }

    public void colocarCartaMonstruo(int indiceMano, int posicionTablero){
        Carta carta = jugador.sacarCartaDeMano(indiceMano);
        tableroJugador.colocarCarta(true, carta, posicionTablero);
    }

    public void colocarCartaMagicaTrampa(int indiceMano, int posicionTablero){
        Carta carta = jugador.sacarCartaDeMano(indiceMano);
        tableroJugador.colocarCarta(false, carta, posicionTablero);
    }

    public CartaMonstruo obtenerCartaMonstruo(int posicion){
        return tableroJugador.obtenerCartaMonstruo(posicion);
    }

    public void destruirMonstruoPorPosicion(int posicion){
        CartaMonstruo carta = tableroJugador.obtenerCartaMonstruo(posicion);
        jugador.enviarAlCementerio(carta);
        tableroJugador.removerMonstruoPorPosicion(posicion);
    }

    public void destruirCartaMonstruo(CartaMonstruo monstruo){
        jugador.enviarAlCementerio(monstruo);
        tableroJugador.removerCartaMonstruo(monstruo);
    }

    public void destruirCartaMagica(CartaMagica magica){
        jugador.enviarAlCementerio(magica);
        tableroJugador.removerCartaMagica(magica);
    }

    public void destruirCartaTrampa(CartaTrampa trampa){
        jugador.enviarAlCementerio(trampa);
        tableroJugador.removerCartaTrampa(trampa);
    }

    public CartaMagica obtenerCartaMagica(int posicion){
        return tableroJugador.obtenerCartaMagica(posicion);
    }

    public void agregarCartaMagicaActiva(CartaMagica cartaMagica){
        tableroJugador.agregarCartaMagicaActiva(cartaMagica);
    }
    
    public void removerCartaMagicaActiva(CartaMagica cartaMagica){
        tableroJugador.removerCartaMagicaActiva(cartaMagica);
    }

    /*public void atacarMonstruo(int posicion, int posicionDefensor){
        Carta monstruo = jugador.sacarCartaDeMano(posicion);
        
    }*/

    /*public void activarCartaMagica(int posicion){
        CartaMagica carta = tableroJugador.obtenerCartaMagica(posicion);
        carta.activar(null)
    }

    public void activarCartaTrampa(){

    }*/

    public void cambiarModoMonstruo(int indiceMonstruo){
        tableroJugador.cambiarModoMonstruo(indiceMonstruo);
    }

    public void inhabilitarCartaMonstruo(int indiceMonstruo){
        tableroJugador.inhabilitarCartaMonstruo(indiceMonstruo);
    }

    public void habilitarCartaMonstruo(int indiceMonstruo){
        tableroJugador.habilitarCartaMonstruo(indiceMonstruo);
    }


    public void reestablecerAtributosMonstruo(int pos){
        CartaMonstruo monstruo = tableroJugador.obtenerCartaMonstruo(pos);
        monstruo.reestablecerAtributos();
    }

    public void descartarCarta(int indice){
        jugador.descartarCarta(indice);
    }

     
}
