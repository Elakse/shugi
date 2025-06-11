package yugioop.modelo.jugador;

import yugioop.modelo.tablero.TableroJugador;
import yugioop.modelo.carta.*;
import yugioop.modelo.mesa.MesaYugioh;

public class ContextoJugador {
    Jugador jugador;
    TableroJugador tableroJugador;

    public ContextoJugador(String nombre, Integer tamanioZonas) {
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

    public CartaMonstruo obtenerCartaMonstruoMano(Integer indice){
        return jugador.obtenerCartaMonstruoMano(indice);
    }

    public CartaMagica obtenerCartaMagicaMano(Integer indice){
        return jugador.obtenerCartaMagicaMano(indice);
    }

    public CartaTrampa obtenerCartaTrampaMano(Integer indice){
        return jugador.obtenerCartaTrampaMano(indice);
    }

    public void descartarCartasAleatorias(Integer cantidad){
        jugador.descartarCartasAleatorias(cantidad);
    }

    public void robarCartasMazo(Integer cantidad){
        jugador.robarCartasMazo(cantidad);
    }

    public void robarCartaCementerio(Integer cantidad){
        jugador.robarCartasCementerio(cantidad);
    }

    public void colocarCartaMonstruo(Integer indiceMano, Integer posicionTablero){
        Carta carta = jugador.sacarCartaDeMano(indiceMano);
        tableroJugador.colocarCarta(true, carta, posicionTablero);
    }

    public void colocarCartaMagicaTrampa(Integer indiceMano, Integer posicionTablero){
        Carta carta = jugador.sacarCartaDeMano(indiceMano);
        tableroJugador.colocarCarta(false, carta, posicionTablero);
    }

    public CartaMonstruo obtenerCartaMonstruo(Integer posicion){
        return tableroJugador.obtenerCartaMonstruo(posicion);
    }

    public void destruirMonstruoPorPosicion(Integer posicion){
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

    public CartaMagica obtenerCartaMagica(Integer posicion){
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

    public void activarCartasMagicasActivas(MesaYugioh mesa){
        tableroJugador.obtenerCartasMagicasActivas();
        for (CartaMagica carta : tableroJugador.obtenerCartasMagicasActivas()) {
            carta.activar(mesa, java.util.Optional.empty());
        }
    }

    public void colocarCartaMonstruo(CartaMonstruo monstruo, Integer posicion){
        tableroJugador.colocarCarta(true, monstruo, posicion);
    }

    public void colocarCartaMagica(CartaMagica magica, Integer posicion){
        tableroJugador.colocarCarta(false, magica, posicion);
    }

    public void colocarCartaTrampa(CartaTrampa trampa, Integer posicion){
        tableroJugador.colocarCarta(false, trampa, posicion);
    }

    public void cambiarModoMonstruo(Integer indiceMonstruo){
        tableroJugador.cambiarModoMonstruo(indiceMonstruo);
    }

    public void inhabilitarCartaMonstruo(Integer indiceMonstruo){
        tableroJugador.inhabilitarCartaMonstruo(indiceMonstruo);
    }

    public void habilitarCartaMonstruo(Integer indiceMonstruo){
        tableroJugador.habilitarCartaMonstruo(indiceMonstruo);
    }

    public void reestablecerAtributosMonstruo(Integer pos){
        CartaMonstruo monstruo = tableroJugador.obtenerCartaMonstruo(pos);
        monstruo.reestablecerAtributos();
    }

    public void descartarCarta(Integer indice){
        jugador.descartarCarta(indice);
    }

    public boolean cartaMagicaRequiereObjetivo(Integer posicion){
        return tableroJugador.obtenerCartaMagica(posicion).requiereObjetivo();
    }
     
}
