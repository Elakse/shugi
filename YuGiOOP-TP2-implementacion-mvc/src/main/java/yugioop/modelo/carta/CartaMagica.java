package yugioop.modelo.carta;

import yugioop.modelo.tablero.Tablero;

public abstract class CartaMagica extends Carta {
    protected int turnos;
    protected int turnosRestantes;
    protected String mensajePeticion;

    public CartaMagica(String nombre, int turnos) {
        super(nombre);
        this.turnos = turnos;
        this.turnosRestantes = turnos;
        this.mensajePeticion = null;
    }

    public abstract boolean activar(Tablero tablero);

    public abstract boolean activar(Tablero tablero, CartaMonstruo monstruo);

    @Override
    public boolean esActivableADiscrecion(){
        return true; // Las cartas mágicas son activables a discreción del jugador
    } 

    public TipoZona getTipoZona() { return TipoZona.MAGICA; } // para monstruos

    public String getTipo(){
        return "Mágica";
    }
}