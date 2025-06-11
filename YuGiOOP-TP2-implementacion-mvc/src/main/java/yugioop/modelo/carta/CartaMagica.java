package yugioop.modelo.carta;

import java.util.Optional;

import yugioop.modelo.mesa.MesaYugioh;

public abstract class CartaMagica extends Carta {
    protected Integer turnos;
    protected Integer turnosRestantes;

    public CartaMagica(String nombre, Integer turnos) {
        super(nombre, false);
        this.turnos = turnos;
        this.turnosRestantes = turnos;
    }

    @Override
    public boolean esMonstruo(){
        return esMonstruo;
    }

    public abstract boolean requiereObjetivo();

    public abstract void activar(MesaYugioh mesa, Optional<Integer> objetivo);

    @Override
    public boolean esActivableADiscrecion(){
        return true; // Las cartas mágicas son activables a discreción del jugador
    } 
}