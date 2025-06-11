package yugioop.modelo.jugador;

import yugioop.modelo.carta.*;

import java.util.List;
import java.util.ArrayList;

public class Mano {
    private List<Carta> cartas;
    private int maxCartasEnMano;
    private int cantCartasEnMano;

    // Constructor
    public Mano(){
        this.cartas = new ArrayList<>();
        this.cantCartasEnMano = 0;
        this.maxCartasEnMano = 6;
    }

    public int getMaxCartasEnMano() {
        return this.maxCartasEnMano;
    }

    public void sumarMano(){
        this.cantCartasEnMano += 1;
    }

    public void restarMano(int i){
        this.cantCartasEnMano -= 1;
        this.cartas.remove(i);
    }

    public int getCantCartas(){
        return this.cantCartasEnMano;
    }

    public void agregarCarta(Carta c){
        this.cartas.add(c);
        this.sumarMano();
    }

    public boolean tieneCarta(Carta c){
        return this.cartas.contains(c);
    }
    
    public void mostrarMano(){
        System.out.println();
        System.out.println("Mano de " + this.cantCartasEnMano + " cartas");
        for(Carta c: this.cartas){
            System.out.println("   - " + c.getNombre());
        }
        System.out.println();
    }

    public Carta quitarCarta(int indice){
        Carta carta = this.cartas.remove(indice);
        this.cantCartasEnMano --;
        return carta;
    }

    public Carta obtenerCartaMonstruo(int indice){
        Carta carta = this.cartas.get(indice);
        if (carta.esMonstruo()){
            return carta;
        } else {
            throw new IllegalArgumentException("La carta en el indice " + indice + " no es una carta monstruo");
        }
    }

    public Carta obtenerCartaMagica(int indice){
        Carta carta = this.cartas.get(indice);
        if (!carta.esMonstruo() && carta.esActivableADiscrecion()){
            return carta;
        } else {
            throw new IllegalArgumentException("La carta en el indice " + indice + " no es una carta mágica");
        }
    }

    public Carta obtenerCarta(int indice){
        return this.cartas.get(indice);
    }

    public Carta obtenerCartaTrampa(int indice){
        Carta carta = this.cartas.get(indice);
        if (!carta.esMonstruo() && !carta.esActivableADiscrecion()){
            return carta;
        } else {
            throw new IllegalArgumentException("La carta en el indice " + indice + " no es una carta trampa");
        }
    }

    public List<Carta> getCartas(){
        return this.cartas;
    }
}
