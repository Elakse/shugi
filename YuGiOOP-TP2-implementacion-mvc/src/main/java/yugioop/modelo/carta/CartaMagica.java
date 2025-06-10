package yugioop.modelo.carta;

import yugioop.modelo.jugador.Jugador;
import yugioop.modelo.tablero.Tablero;

public class CartaMagica extends Carta {
    protected TipoMagica tipoMagica;
    protected boolean necesitaObjetivoMonstruo;
    protected int turnosDuracionBase; // Para cartas con duración
    protected int turnosRestantes;    // Para cartas con duración

    public CartaMagica(String nombre, String descripcion, TipoMagica tipo) {
        super(nombre, descripcion);
        this.tipoMagica = tipo;
        this.necesitaObjetivoMonstruo = (tipo == TipoMagica.EQUIPO);
        this.turnosDuracionBase = 0; // Por defecto no duran
        this.turnosRestantes = 0;
    }

    public CartaMagica(String nombre, TipoMagica tipo) { // Original
        this(nombre, "Mágica de tipo " + tipo.toString(), tipo);
    }

    @Override
    public void revelar() {
        if (this.isBocaAbajo()) {
            // System.out.println("Mágica " + nombre + " revelada.");
            this.setBocaAbajo(false);
        }
    }

    public void activar(Jugador activador, Jugador oponente, Tablero tablero, CartaMonstruo monstruoObjetivo) {
        // System.out.println(activador.getNombre() + " activa la carta mágica: " + this.nombre);
        if (this.isBocaAbajo()) this.revelar();
        // Lógica específica en subclases
        // Si es de un solo uso, el Tablero/Juego la enviará al cementerio.
    }

    public void activar(Jugador activador, Jugador oponente, Tablero tablero) {
        this.activar(activador, oponente, tablero, null);
    }
    
    public void actualizarFinTurno(Jugador propietarioCampo) {
        if (turnosRestantes > 0) {
            turnosRestantes--;
            // System.out.println(getNombre() + " tiene " + turnosRestantes + " turnos restantes.");
            if (turnosRestantes == 0) {
                // System.out.println(getNombre() + " ha expirado.");
                // //TODO: Lógica de descarte/desequipamiento al expirar.
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

    public TipoMagica getTipoMagica() { return tipoMagica; }
    public boolean necesitaObjetivoMonstruo() { return necesitaObjetivoMonstruo; }
    public boolean esDeJuegoRapido() { return this.tipoMagica == TipoMagica.RAPIDA; }
    public boolean esContinua() { return this.tipoMagica == TipoMagica.CONTINUA || this.tipoMagica == TipoMagica.EQUIPO || this.tipoMagica == TipoMagica.CAMPO; } // Ampliado
    public boolean esDeEquipo() { return this.tipoMagica == TipoMagica.EQUIPO; }
    public boolean esDeTipoCampo() { return this.tipoMagica == TipoMagica.CAMPO; }
}