
package yugioop.modelo.jugador;

import yugioop.modelo.carta.Carta;

import java.util.List;
import java.util.ArrayList;

public class Cementerio {
    private List<Carta> cartasEnCementerio;

    // Constructor
    public Cementerio(){
        this.cartasEnCementerio = new ArrayList<>();
    }

    public void agregarCartaAlCementerio(Carta carta){
        this.cartasEnCementerio.add(carta);
    }

    public void vaciar(){
        this.cartasEnCementerio.clear();
    }

    public Carta agarrarCarta(){
        Carta carta;
        int cantidad = this.cartasEnCementerio.size();

        carta = this.cartasEnCementerio.get(cantidad - 1);
        this.cartasEnCementerio.remove(cantidad - 1);

        return carta;
    }

    public int getCantidadCartasEnCementerio() {
        return this.cartasEnCementerio.size();
    }

    public List<Carta> getCartasEnCementerio() {
        return this.cartasEnCementerio;
    }


}
