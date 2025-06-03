package yugioop.modelo.carta;

public class CartaTrampa extends Carta {
    private TipoTrampa tipoTrampa;
    
    public CartaTrampa(String nombre) { 
        super(nombre);
        this.tipoTrampa = TipoTrampa.NORMAL; // Por defecto, las trampas son normales
    }
    
    @Override 
    public void revelar() { 
        System.out.println("Trampa " + nombre + " revelada.");
        this.setBocaAbajo(false);
    }
    
    public void activar(Object j, Object g) { 
        // Lógica para activar la trampa, dependiendo del tipo y del juego
        System.out.println(this.nombre + " activada.");
    }
    
    /**
     * Obtiene el tipo de esta carta trampa.
     * @return El tipo de la trampa (NORMAL, CONTINUA, CONTRAEFECTO).
     */
    public TipoTrampa getTipoTrampa() { 
        return tipoTrampa; 
    }
    
    /**
     * Verifica si esta trampa es de tipo continua.
     * @return true si la trampa es continua, false en caso contrario.
     */
    public boolean esContinua() { 
        return this.tipoTrampa == TipoTrampa.CONTINUA; 
    }
    
    /**
     * Verifica si esta trampa es de contraefecto.
     * @return true si la trampa es de contraefecto, false en caso contrario.
     */
    public boolean esDeContraefecto() { 
        return this.tipoTrampa == TipoTrampa.CONTRAEFECTO; 
    }
}
