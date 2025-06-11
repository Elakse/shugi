package yugioop.modelo.carta;

import yugioop.modelo.carta.trampas.Evento;
import yugioop.modelo.mesa.MesaYugioh;

public abstract class CartaTrampa extends Carta {
    protected Evento evento;

    public CartaTrampa(String nombre, Evento evento) { 
        super(nombre, false);
        this.evento = evento;
    }

    @Override
    public boolean esActivableADiscrecion(){
        return false;
    }

    @Override
    public boolean esMonstruo(){
        return esMonstruo;
    }

    /**
     * Activa la carta trampa en el tablero.
     * @param tablero El tablero del juego.
     */
    public abstract void activar(MesaYugioh mesa, Evento evento);
    
}
