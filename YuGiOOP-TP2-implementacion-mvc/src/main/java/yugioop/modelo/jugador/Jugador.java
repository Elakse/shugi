package yugioop.modelo.jugador;

import java.util.List;

import yugioop.modelo.carta.Carta;

public class Jugador {
    private String nombre;
    private int puntosDeVida = 8000;
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
        int cantidad = this.getCantCartasEnMano();
        int cantidadMaxima = mano.getMaxCartasEnMano();
        if (cantidad > cantidadMaxima) {
            this.descartarCartasAleatorias(cantidad - cantidadMaxima);
        }
    }

    public String getNombre(){
        return this.nombre;
    }

    public int getPuntosDeVida(){
        return this.puntosDeVida;
    }

    public Mazo getMazo(){
        return this.mazo;
    }

    public Cementerio getCementerio(){
        return this.cementerio;
    }

    public void perderVida(int cantidad) {
        if (cantidad < this.puntosDeVida){
            this.puntosDeVida -= cantidad;
        }
        else{
            this.estaEnVida = false;
        }
    }

    public void ganarVida(int cantidad) {
        this.puntosDeVida += cantidad;
        if (this.puntosDeVida > 8000){
            this.puntosDeVida = 8000;
        }
    }

    public void enviarAlCementerio(Carta carta){
        this.cementerio.agregarCartaAlCementerio(carta);
    }

    public void setMazo(Mazo mazo){
        this.mazo = mazo;
    }

    public Mano getMano(){
        return this.mano;
    }

    public int getCantCartasEnMazo(){
        return this.mazo.getCantCartasEnMazo();
    }

    public void robarCartasMazo(int cantidad){

        Carta c;
        if (cantidad <= this.getCantCartasEnMazo()){
            for (int i=0; i<cantidad; i++){
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

    public Carta sacarCartaDeMano(int indice){
        Carta carta = this.mano.obtenerCarta(indice);
        this.mano.quitarCarta(indice);
        return carta;
    }

    public void descartarCartasAleatorias(int cantidad) {
        if (cantidad > this.getCantCartasEnMano()) {
            return;
        }
        for (int i = 0; i < cantidad; i++) {
            int indiceAleatorio = (int) (Math.random() * this.getCantCartasEnMano());
            Carta cartaDescartada = this.getMano().quitarCarta(indiceAleatorio);
            this.enviarAlCementerio(cartaDescartada);
        }
    }
    
    public void descartarCarta(int indice) {
        Carta cartaDescartada = this.getMano().quitarCarta(indice);
        this.enviarAlCementerio(cartaDescartada);
    }

    public int getCantCartasEnMano(){
        return this.mano.getCantCartas();
    }

    public int getCantCartasEnCementerio(){
        return this.cementerio.getCantidadCartasEnCementerio();
    }

    public int robarCartasCementerio(int cantidad){
        int faltan = 0;
        if(cantidad > this.getCantCartasEnCementerio()) {
            faltan = cantidad - this.getCantCartasEnCementerio();
            cantidad = this.getCantCartasEnCementerio();
        }
        for (int i = 0; i < cantidad; i++) {
            agarrarCartaCementerio();
        }
        return faltan;
    }

    public boolean estaVivo(){
        return this.estaEnVida;
    }

    public void quitarCartaDeMano(int i){
        if (this.getMano().getCantCartas() > 0){
            this.getMano().quitarCarta(i);
        }
        else{
            System.out.println("No tiene cartas en tu mano para quitar ... ");
        }
    }

    public List<Carta> getCartas(){
        return this.mano.getCartas();
    }   
    
}

