package yugioop.modelo.jugador;

import yugioop.modelo.carta.Carta;

import java.util.List;
import java.util.ArrayList;

/**
 * Representa un mazo de cartas del juego
 */
public class Mazo {
    // Atributos
    private List<Carta> cartas;
    private int maxCartasEnMazo;
    private int cantCartasEnMazo;

    // Constructor
    public Mazo(){
        this.cartas = new ArrayList<>();
        this.maxCartasEnMazo = 3;
        this.cantCartasEnMazo = 0;
    }

    public Carta robar(int i){
        Carta c = this.cartas.get(i);
        return c;
    }

    public List<Carta> getCartasEnMazo(){
       return this.cartas;
    }

    public void sumarCartas(Carta c){
        this.cartas.add(c);
        this.cantCartasEnMazo += 1;
    }

    public int getCantCartasEnMazo(){
        return this.cantCartasEnMazo;
    }

    public int getMaxCartasEnMazo(){
        return this.maxCartasEnMazo;
    }

    public Carta tomarCarta(int i){
        Carta carta;

        this.cantCartasEnMazo -= 1;
        carta = this.cartas.get(i);
        this.cartas.remove(i);

        return carta;
    }

    // jugador rendirse.

    public void mostrarMazo(){
        System.out.println("Mazo de " + this.cantCartasEnMazo + " cartas");
        for(Carta c: this.cartas){
            System.out.println("   - " + c.getNombre());
        }
    }
}
