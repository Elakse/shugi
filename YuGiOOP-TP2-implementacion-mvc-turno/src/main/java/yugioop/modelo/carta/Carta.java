package yugioop.modelo.carta;

import java.util.UUID;

public abstract class Carta {
    protected UUID id;
    protected String nombre;
    protected boolean bocaAbajo;
    protected int turnoColocacion;

    public Carta(String nombre) {
        this.id = UUID.randomUUID();
        this.nombre = nombre;
        this.bocaAbajo = true; // Por defecto, las cartas se colocan boca abajo
        this.turnoColocacion = -1; // Se actualizará cuando se coloque en el tablero
    }

    public String getNombre() { return nombre; }
    public abstract void revelar();

    /**
     * Verifica si la carta está boca abajo.
     * @return true si la carta está boca abajo, false si está boca arriba.
     */
    public boolean isBocaAbajo() { return bocaAbajo; }
    
    /**
     * Establece si la carta está boca abajo o boca arriba.
     * @param bocaAbajo true para colocar la carta boca abajo, false para boca arriba.
     */
    public void setBocaAbajo(boolean bocaAbajo) { this.bocaAbajo = bocaAbajo; }
    
    /**
     * Verifica si la carta es de tipo mágica de campo.
     * @return true si la carta es de tipo mágica de campo, false en caso contrario.
     */
    public boolean esDeTipoCampo() {
        if (this instanceof CartaMagica) {
            return ((CartaMagica) this).getTipoMagica() == TipoMagica.CAMPO;
        }
        return false;
    }

    protected UUID id;

    protected String nombre;

    protected boolean bocaAbajo;

    protected int turnoColocacion;

    public Carta(String nombre) {

        this.id = UUID.randomUUID();

        this.nombre = nombre;

        this.bocaAbajo = true; // Por defecto, las cartas se colocan boca abajo

        this.turnoColocacion = -1; // Se actualizará cuando se coloque en el tablero

    }

    public String getNombre() {
        return nombre;
    }

    public abstract void revelar();

    /**
     * 
     * 
     * Verifica si la carta está boca abajo.
     * 
     * 
     * @return true si la carta está boca abajo, false si está boca arriba.
     * 
     * 
     */

    public boolean isBocaAbajo() {
        return bocaAbajo;
    }

    /**
     * 
     * 
     * Establece si la carta está boca abajo o boca arriba.
     * 
     * 
     * @param bocaAbajo true para colocar la carta boca abajo, false para boca
     *                  arriba.
     * 
     * 
     */

    public void setBocaAbajo(boolean bocaAbajo) {
        this.bocaAbajo = bocaAbajo;
    }

    /**
     * 
     * 
     * Verifica si la carta es de tipo mágica de campo.
     * 
     * 
     * @return true si la carta es de tipo mágica de campo, false en caso contrario.
     * 
     * 
     */

    public boolean esDeTipoCampo() {

        if (this instanceof CartaMagica) {

            return ((CartaMagica) this).getTipoMagica() == TipoMagica.CAMPO;

        }

        return false;

    }

}