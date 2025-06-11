package yugioop.modelo.carta;

import yugioop.modelo.mesa.MesaYugioh;

public abstract class CartaMagica extends Carta {
    protected int turnos;
    protected int turnosRestantes;

    public CartaMagica(String nombre, int turnos) {
        super(nombre);
        this.turnos = turnos;
        this.turnosRestantes = turnos;
    }

    public abstract void activar(MesaYugioh mesa, int objetivo);

    @Override
    public boolean esActivableADiscrecion(){
        return true; // Las cartas mágicas son activables a discreción del jugador
    } 
}