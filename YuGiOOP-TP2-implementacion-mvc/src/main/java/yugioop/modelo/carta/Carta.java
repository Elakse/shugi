package yugioop.modelo.carta;

import java.util.UUID;

public abstract class Carta {
    protected UUID id;
    protected String nombre;
    protected boolean bocaAbajo;

    public Carta(String nombre) {
        this.id = UUID.randomUUID();
        this.nombre = nombre;
        this.bocaAbajo = true; // Por defecto, las cartas se colocan boca arriba
    }

    public abstract boolean esActivableADiscrecion();

    public String getNombre() {
        return nombre;
    }

    public void revelar(){
        this.bocaAbajo = false;
    }

    /**
     * Verifica si la carta está boca abajo.
     * @return true si la carta está boca abajo, false si está boca arriba.
     */
    public boolean estaBocaAbajo() {
        return bocaAbajo;
    }

    public void ponerBocaAbajo() {
        this.bocaAbajo = true;
    }

    public abstract TipoZona getTipoZona();

    public abstract String getTipo();

}