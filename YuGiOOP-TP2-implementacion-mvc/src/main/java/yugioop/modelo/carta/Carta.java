package yugioop.modelo.carta;

import java.util.UUID;

public abstract class Carta {
    protected UUID id;
    protected String nombre;
    protected boolean bocaAbajo;
    protected boolean esMonstruo;

    public Carta(String nombre, boolean esMonstruo) {
        this.id = UUID.randomUUID();
        this.nombre = nombre;
        this.esMonstruo = esMonstruo;
        this.bocaAbajo = true; // Por defecto, las cartas se colocan boca arriba
    }

    public abstract boolean esActivableADiscrecion();

    public abstract boolean esMonstruo();

    public String getNombre() {
        return nombre;
    }

    public boolean estaBocaAbajo() {
        return bocaAbajo;
    }

    public void ponerBocaAbajo() {
        this.bocaAbajo = true;
    }
    
    public void revelar(){
        this.bocaAbajo = false;
    }

}