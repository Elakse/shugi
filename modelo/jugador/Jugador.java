package yugioop.modelo.jugador;

import java.util.List;

import yugioop.modelo.carta.*;

public class Jugador {
    private String nombre;
    private Integer puntosDeVida = 8000;
    private Mano mano;
    private Mazo mazo;
    private Cementerio cementerio;
    private boolean estaEnVida;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.cementerio = new Cementerio();
        this.mano = new Mano();
        this.mazo = new Mazo();
        this.estaEnVida = true;
    }

    public void agarrarCartaCementerio(){
        Carta carta = this.cementerio.agarrarCarta();
        this.mano.agregarCarta(carta);
    }

    public void descartarCartasExtra(){
        Integer cantidad = this.getCantCartasEnMano();
        Integer cantidadMaxima = mano.getMaxCartasEnMano();
        if (cantidad > cantidadMaxima) {
            this.descartarCartasAleatorias(cantidad - cantidadMaxima);
        }
    }

    public String getNombre(){
        return this.nombre;
    }

    public Integer getPuntosDeVida(){
        return this.puntosDeVida;
    }

    public Mazo getMazo(){
        return this.mazo;
    }

    public Cementerio getCementerio(){
        return this.cementerio;
    }

    public void perderVida(Integer cantidad) {
        if (cantidad < this.puntosDeVida){
            this.puntosDeVida -= cantidad;
        }
        else{
            this.estaEnVida = false;
        }
    }

    public void ganarVida(Integer cantidad) {
        this.puntosDeVida += cantidad;
        if (this.puntosDeVida > 8000){
            this.puntosDeVida = 8000;
        }
    }

    public void enviarAlCementerio(Carta carta){
        this.cementerio.agregarCartaAlCementerio(carta);
    }

    public void setMazo(Mazo mazoNuevo){
        for (Carta carta : mazoNuevo.getCartasEnMazo()){
            this.mazo.sumarCartas(carta);
        }
    }

    public Mano getMano(){
        return this.mano;
    }

    public CartaMonstruo obtenerCartaMonstruoMano(Integer indice){
        return (CartaMonstruo) getMano().obtenerCartaMonstruo(indice);
    }

    public CartaTrampa obtenerCartaTrampaMano(Integer indice){
        return (CartaTrampa) getMano().obtenerCartaTrampa(indice);
    }
    
    public CartaMagica obtenerCartaMagicaMano(Integer indice){
        return (CartaMagica) getMano().obtenerCartaMagica(indice);
    }

    public Integer getCantCartasEnMazo(){
        return this.mazo.getCantCartasEnMazo();
    }

    public void robarCartasMazo(Integer cantidad){

        Carta c;
        if (cantidad <= this.getCantCartasEnMazo()){
            for (Integer i=0; i<cantidad; i++){
                c = this.mazo.tomarCarta(i);
                this.getMano().agregarCarta(c);
            }
        }
        else{
            this.estaEnVida = false;
        }
    }

    public boolean tieneCartaEnMano(Carta carta) {
        return this.mano.tieneCarta(carta);
    }

    public void sacarCartaDeMano(Carta carta) {
        this.mano.quitarCarta(this.getCartas().indexOf(carta));
    }

    public Carta sacarCartaDeMano(Integer indice){
        Carta carta = this.mano.obtenerCarta(indice);
        this.mano.quitarCarta(indice);
        return carta;
    }

    public void descartarCartasAleatorias(Integer cantidad) {
        if (cantidad > this.getCantCartasEnMano()) {
            return;
        }
        for (Integer i = 0; i < cantidad; i++) {
            int indiceAleatorio = (int) (Math.random() * this.getCantCartasEnMano());
            Carta cartaDescartada = this.getMano().quitarCarta(indiceAleatorio);
            this.enviarAlCementerio(cartaDescartada);
        }
    }
    
    public void descartarCarta(Integer indice) {
        Carta cartaDescartada = this.getMano().quitarCarta(indice);
        this.enviarAlCementerio(cartaDescartada);
    }

    public Integer getCantCartasEnMano(){
        return this.mano.getCantCartas();
    }

    public Integer getCantCartasEnCementerio(){
        return this.cementerio.getCantidadCartasEnCementerio();
    }

    public Integer robarCartasCementerio(Integer cantidad){
        Integer faltan = 0;
        if(cantidad > this.getCantCartasEnCementerio()) {
            faltan = cantidad - this.getCantCartasEnCementerio();
            cantidad = this.getCantCartasEnCementerio();
        }
        for (Integer i = 0; i < cantidad; i++) {
            agarrarCartaCementerio();
        }
        return faltan;
    }

    public boolean estaVivo(){
        return this.estaEnVida;
    }

    public void quitarCartaDeMano(Integer i){
        if (this.getMano().getCantCartas() > 0){
            this.getMano().quitarCarta(i);
        }
        else{
            System.out.println("No tiene cartas en tu mano para quitar ... ");
        }
    }

    public List getCartas(){
        return this.mano.getCartas();
    }   

    public List<CartaMonstruo> obtenerMonstruosMano(){
        List<Carta> cartas = mano.getCartas();
        for (int i = 0; i < cartas.size(); i++) {
            if (!cartas.get(i).esMonstruo()) {
                 cartas.set(i, null);
            }
        }
        return (CartaMonstruo) cartas;
    }
    
    public List<Carta> obtenerMagicasTrampasMano(){
        List<Carta> cartas = mano.getCartas();
        for (int i = 0; i < cartas.size(); i++) {
            if (cartas.get(i).esMonstruo()) {
                 cartas.set(i, null);
            }
        }
        return cartas;
    }

}

