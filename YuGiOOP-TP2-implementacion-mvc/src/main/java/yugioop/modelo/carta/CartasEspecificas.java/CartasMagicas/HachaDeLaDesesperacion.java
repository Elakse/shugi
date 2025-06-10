package yugioop.modelo.carta.magica;

import yugioop.modelo.carta.CartaMagica;
import yugioop.modelo.carta.CartaMonstruo;
import yugioop.modelo.carta.TipoMagica;
import yugioop.modelo.jugador.Jugador;
import yugioop.modelo.tablero.Tablero;

public class HachaDeLaDesesperacion extends CartaMagica {
    private static final int AUMENTO_ATK = 1000;
    private static final int TURNOS_DURACION = 2;
    private CartaMonstruo monstruoEquipado;

    public HachaDeLaDesesperacion() {
        super("Hacha de la Desesperación", "El monstruo equipado gana " + AUMENTO_ATK + " ATK. Este efecto dura " + TURNOS_DURACION + " turnos del controlador de esta carta.", TipoMagica.EQUIPO);
        this.necesitaObjetivoMonstruo = true;
    }

    @Override
    public void activar(Jugador activador, Jugador oponente, Tablero tablero, CartaMonstruo monstruoObjetivo) {
        super.activar(activador, oponente, tablero, monstruoObjetivo);
        if (monstruoObjetivo != null) {
            this.monstruoEquipado = monstruoObjetivo;
            this.monstruoEquipado.modificarAtaque(AUMENTO_ATK);
            iniciarDuracion(TURNOS_DURACION); // Inicia contador de turnos
            System.out.println(this.nombre + " equipada a " + monstruoObjetivo.getNombre() + ". ATK aumentado en " + AUMENTO_ATK);
            
        } else {
            System.out.println("Activación de " + this.nombre + " falló: no se especificó un monstruo objetivo válido.");
            // //TODO: Devolver a la mano o manejar error si no se puede activar.
        }
    }
    
    @Override
    public void actualizarFinTurno(Jugador propietarioCampo) {
        if (monstruoEquipado != null) { // Solo si está equipada y es el turno del propietario
            Jugador controladorMonstruoEquipado = null; // //TODO: Determinar quién controla el monstruo equipado
            
            turnosRestantes--;
            System.out.println(getNombre() + " equipada a " + monstruoEquipado.getNombre() + " tiene " + turnosRestantes + " turnos restantes.");
            if (turnosRestantes <= 0) {
                desequiparYEnviarAlCementerio(propietarioCampo);
            }
        }
    }

    public void desequiparYEnviarAlCementerio(Jugador propietarioCampo) {
        if (monstruoEquipado != null) {
            monstruoEquipado.modificarAtaque(-AUMENTO_ATK);
            // System.out.println(this.nombre + " desequipada de " + monstruoEquipado.getNombre() + ". ATK restaurado.");
            this.monstruoEquipado = null;
        }
        // System.out.println(this.nombre + " ha expirado y es enviada al cementerio.");
        // //TODO: propietarioCampo.enviarAlCementerio(this); // El Tablero se encarga de moverla
    }

    public CartaMonstruo getMonstruoEquipado() {
        return monstruoEquipado;
    }
}