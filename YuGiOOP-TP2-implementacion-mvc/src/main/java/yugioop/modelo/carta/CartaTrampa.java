package yugioop.modelo.carta;

import yugioop.modelo.jugador.Jugador;
import yugioop.modelo.tablero.Tablero;
import yugioop.modelo.carta.condicion.Condicion;
import yugioop.modelo.carta.condicion.TipoEventoDeJuego;


public class CartaTrampa extends Carta {
    private TipoTrampa tipoTrampa;
    private Condicion condicionActivacion;
    protected int turnosDuracionBase; // Para trampas con duración
    protected int turnosRestantes;    // Para trampas con duración


    public CartaTrampa(String nombre, String descripcionEfecto, Condicion condicion, TipoTrampa tipo) {
        super(nombre, descripcionEfecto);
        this.tipoTrampa = tipo;
        this.condicionActivacion = condicion;
        this.bocaAbajo = true;
        this.turnosDuracionBase = 0;
        this.turnosRestantes = 0;
    }
    
    public CartaTrampa(String nombre) { // Original
        this(nombre, "Efecto de trampa.", null, TipoTrampa.NORMAL);
    }


    @Override
    public void revelar() {
        // Las trampas se revelan cuando se activan.
        if (this.isBocaAbajo()){
            // System.out.println("Trampa " + nombre + " activada y revelada.");
            this.setBocaAbajo(false);
        }
    }

    public void activar(Jugador activador, Jugador oponente, Tablero tablero) {
        this.revelar();
        // System.out.println(activador.getNombre() + " activa la carta trampa: " + this.nombre);
        // Lógica específica en subclases
        // Si es de un solo uso, el Tablero/Juego la enviará al cementerio.
    }
    
    public void actualizarFinTurno(Jugador propietarioCampo) {
        if (turnosRestantes > 0) {
            turnosRestantes--;
            // System.out.println(getNombre() + " tiene " + turnosRestantes + " turnos restantes.");
            if (turnosRestantes == 0) {
                // System.out.println(getNombre() + " ha expirado.");
                // //TODO: Lógica de descarte al expirar.
                // propietarioCampo.enviarAlCementerio(this); // Ejemplo
            }
        }
    }
    
    public boolean haExpirado() {
        return turnosDuracionBase > 0 && turnosRestantes <= 0;
    }

    protected void iniciarDuracion(int turnos) {
        this.turnosDuracionBase = turnos;
        this.turnosRestantes = turnos;
    }

    public TipoTrampa getTipoTrampa() { return tipoTrampa; }
    public Condicion getCondicionActivacion() { return condicionActivacion; }
    
    public boolean verificarCondicion(TipoEventoDeJuego tipoEvento, Jugador propietario, Jugador jugadorAccion, Tablero tablero) {
        if (this.isBocaAbajo() && condicionActivacion != null) {
            return condicionActivacion.seCumple(tipoEvento, this, propietario, jugadorAccion, tablero);
        }
        return false;
    }
}