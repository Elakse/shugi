package yugioop.modelo.carta;

public class CartaMagica extends Carta {
        private TipoMagica tipoMagica;

    public CartaMagica(String nombre, TipoMagica tipo) {
        super(nombre);
        this.tipoMagica = tipo;
    }

    @Override public void revelar() { 
        // Lógica para revelar la carta mágica
        System.out.println("Mágica " + nombre + " revelada."); 
    }
    public void activar(Object j, Object g) { 
        // Lógica para activar la carta mágica, dependiendo del juego
        System.out.println(this.nombre + " activada."); 
    }

    public TipoMagica getTipoMagica() { return tipoMagica; }
    
    /**
     * Verifica si esta carta mágica es de juego rápido.
     * @return true si la carta es de tipo RAPIDA, false en caso contrario.
     */
    public boolean esDeJuegoRapido() { 
        return this.tipoMagica == TipoMagica.RAPIDA; 
    }
    
    /**
     * Verifica si esta carta mágica es de tipo continua.
     * @return true si la carta es de tipo CONTINUA, false en caso contrario.
     */
    public boolean esContinua() { 
        return this.tipoMagica == TipoMagica.CONTINUA; 
    }
    
    /**
     * Verifica si esta carta mágica es de tipo equipo.
     * @return true si la carta es de tipo EQUIPO, false en caso contrario.
     */
    public boolean esDeEquipo() { 
        return this.tipoMagica == TipoMagica.EQUIPO; 
    }
}
