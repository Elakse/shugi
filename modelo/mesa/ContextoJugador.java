package yugioop.modelo.jugador;

import yugioop.modelo.tablero.TableroJugador;
import yugioop.modelo.carta.*;
import yugioop.modelo.carta.trampas.*;
import java.util.List;
import java.util.Optional;
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

    public List<Carta> obtenerCartasMano(){
        return jugador.getCartas();
    }

    public List<CartaMonstruo> obtenerMonstruosMano(){
        return jugador.obtenerMonstruosMano();
    }

    public List<Carta> obtenerMagicasTrampasMano(){
        return jugador.obtenerMagicasTrampasMano();
    }

    public List<Carta> obtenerCartasZonaMonstruo(){
        return tableroJugador.obtenerCartasZonaMonstruo();
    }

    public List<Carta> obtenerCartasZonaMagiaTrampa(){
        return tableroJugador.obtenerCartasZonaMagiaTrampa();
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

    public void atacarMonstruo(MesaYugioh mesa, Integer posicion, Integer posObjetivo){
        tableroJugador.obtenerCartaMonstruo(posicion).activar(mesa, posObjetivo);
    }

    public Integer obtenerCantSacrificiosMonstruo(Integer posicion){
        return tableroJugador.obtenerCartaMonstruo(posicion).sacrificiosNecesarios();
    }

    public void activarCartaMagica(MesaYugioh mesa, Integer posicion, Optional<Integer> posObjetivo){
        CartaMagica carta = tableroJugador.obtenerCartaMagica(posicion);
        if(carta.requiereObjetivo()){
            carta.activar(mesa, posObjetivo);
        }
        carta.activar(mesa, java.util.Optional.empty());
    }

    public void activarCartasTrampa(MesaYugioh mesa, Evento evento){
        List<CartaTrampa> trampas = tableroJugador.obtenerCartasTrampa();
        for (CartaTrampa trampa : trampas) {
            trampa.activar(mesa, evento);
        }
    }

    public void activarCartasMagicasActivas(MesaYugioh mesa){
        tableroJugador.obtenerCartasMagicasActivas();
        for (CartaMagica carta : tableroJugador.obtenerCartasMagicasActivas()) {
            carta.activar(mesa, java.util.Optional.empty());
        }
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
