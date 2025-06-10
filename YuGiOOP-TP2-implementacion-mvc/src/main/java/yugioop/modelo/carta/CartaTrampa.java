package yugioop.modelo.carta;

import yugioop.modelo.tablero.Tablero;
import yugioop.modelo.carta.trampas.Evento;

public abstract class CartaTrampa extends Carta {
    public CartaTrampa(String nombre) { 
        super(nombre);
    }

    @Override
    public boolean esActivableADiscrecion(){
        return false;
    }
    
    public abstract void revelar();

    /**
     * Determina si la carta trampa debe activarse en el evento dado.
     * @param evento El evento que desencadena la activación.
     * @param tablero El tablero del juego.
     * @return true si la carta trampa debe activarse, false en caso contrario.
     */
    public abstract boolean debeActivarse(Evento evento, Tablero tablero);

    /**
     * Activa la carta trampa en el tablero.
     * @param tablero El tablero del juego.
     */
    public abstract void activar(Tablero tablero);
    

    public void setBocaAbajo(boolean bocaAbajo) {
        this.bocaAbajo = bocaAbajo;
    }
}
