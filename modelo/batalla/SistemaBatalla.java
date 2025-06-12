package yugioop.modelo.batalla;

import yugioop.modelo.jugador.Jugador;
import yugioop.modelo.tablero.ITableroJugador;
import yugioop.modelo.tablero.Tablero;
import yugioop.modelo.carta.CartaMonstruo;

/**
 * Sistema encargado de gestionar los combates entre monstruos.
 */
public class SistemaBatalla {
    private Tablero tablero;

    /**
     * Constructor del sistema de batalla.
     * @param tablero Tablero del juego
     */
    public SistemaBatalla(Tablero tablero) {
        this.tablero = tablero;
    }

    /**
     * Ejecuta un ataque entre monstruos.
     * 
     * @param jugadorAtacante  Jugador que realiza el ataque
     * @param posicionAtacante Posición del monstruo atacante (0-4)
     * @param jugadorDefensor  Jugador que recibe el ataque
     * @param posicionDefensor Posición del monstruo defensor (0-4)
     * @return true si el ataque se ejecutó correctamente
     */
    public boolean ejecutarAtaque(Jugador jugadorAtacante, int posicionAtacante,
            Jugador jugadorDefensor, int posicionDefensor) {
        ITableroJugador tableroAtacante = tablero.obtenerTablero(jugadorAtacante);
        ITableroJugador tableroDefensor = tablero.obtenerTablero(jugadorDefensor);
        if (tableroAtacante == null || tableroDefensor == null)
            return false;

        CartaMonstruo monstruoAtacante = tableroAtacante.getZonaMonstruos().obtenerCarta(posicionAtacante);
        CartaMonstruo monstruoDefensor = tableroDefensor.getZonaMonstruos().obtenerCarta(posicionDefensor);
        if (monstruoAtacante == null || monstruoDefensor == null)
            return false;

        // Determinar modo del monstruo defensor
        if (!monstruoDefensor.estaEnModoAtaque()) {
            return resolverAtaqueContraDefensa(jugadorAtacante, monstruoAtacante, jugadorDefensor, monstruoDefensor);
        } else {
            return resolverAtaqueContraAtaque(jugadorAtacante, monstruoAtacante, jugadorDefensor, monstruoDefensor);
        }
    }

    /**
     * Resuelve un ataque contra un monstruo en modo defensa.
     * 
     * @param jugadorAtacante  Jugador que realiza el ataque
     * @param monstruoAtacante Monstruo que realiza el ataque
     * @param jugadorDefensor  Jugador que recibe el ataque
     * @param monstruoDefensor Monstruo que recibe el ataque
     * @return true si el ataque se ejecutó correctamente
     */
    private boolean resolverAtaqueContraDefensa(Jugador jugadorAtacante, CartaMonstruo monstruoAtacante,
            Jugador jugadorDefensor, CartaMonstruo monstruoDefensor) {
        int atkAtacante = monstruoAtacante.getAtaque();
        int defDefensor = monstruoDefensor.getDefensa();
        if (atkAtacante > defDefensor) {
            jugadorDefensor.enviarAlCementerio(monstruoDefensor);
            System.out.println("El monstruo defensor " + monstruoDefensor.getNombre() +
                    " es destruido. Nadie pierde puntos de vida.");
        } else if (atkAtacante < defDefensor) {
            int danio = defDefensor - atkAtacante;
            jugadorAtacante.perderVida(danio);
            System.out.println(jugadorAtacante.getNombre() + " pierde " + danio +
                    " puntos de vida. Ningún monstruo es destruido.");
        } else {
            System.out.println("No ocurre nada: ATK igual a DEF.");
        }
        return true;
    }

    /**
     * Resuelve un ataque contra un monstruo en modo ataque.
     * 
     * @param jugadorAtacante  Jugador que realiza el ataque
     * @param monstruoAtacante Monstruo que realiza el ataque
     * @param jugadorDefensor  Jugador que recibe el ataque
     * @param monstruoDefensor Monstruo que recibe el ataque
     * @return true si el ataque se ejecutó correctamente
     */
    private boolean resolverAtaqueContraAtaque(Jugador jugadorAtacante, CartaMonstruo monstruoAtacante,
            Jugador jugadorDefensor, CartaMonstruo monstruoDefensor) {
        int atkAtacante = monstruoAtacante.getAtaque();
        int atkDefensor = monstruoDefensor.getAtaque();
        if (atkAtacante > atkDefensor) {
            int danio = atkAtacante - atkDefensor;
            jugadorDefensor.enviarAlCementerio(monstruoDefensor);
            jugadorDefensor.perderVida(danio);
            System.out.println("El monstruo defensor " + monstruoDefensor.getNombre() + " es destruido. " +
                    jugadorDefensor.getNombre() + " pierde " + danio + " puntos de vida.");
        } else if (atkAtacante < atkDefensor) {
            int danio = atkDefensor - atkAtacante;
            jugadorAtacante.enviarAlCementerio(monstruoAtacante);
            jugadorAtacante.perderVida(danio);
            System.out.println("El monstruo atacante " + monstruoAtacante.getNombre() + " es destruido. " +
                    jugadorAtacante.getNombre() + " pierde " + danio + " puntos de vida.");
        } else {
            jugadorAtacante.enviarAlCementerio(monstruoAtacante);
            jugadorDefensor.enviarAlCementerio(monstruoDefensor);
            System.out
                    .println("Ambos monstruos " + monstruoAtacante.getNombre() + " y " + monstruoDefensor.getNombre() +
                            " son destruidos. Nadie pierde puntos de vida.");
        }
        return true;
    }

    /**
     * Ejecuta un ataque directo a los puntos de vida del oponente.
     * 
     * @param jugadorAtacante  Jugador que realiza el ataque
     * @param posicionAtacante Posición del monstruo atacante (0-4)
     * @param jugadorDefensor  Jugador que recibe el ataque directo
     * @return true si el ataque se ejecutó correctamente
     */
    public boolean ejecutarAtaqueDirecto(Jugador jugadorAtacante, int posicionAtacante,
            Jugador jugadorDefensor) {
        var tableroAtacante = tablero.obtenerTablero(jugadorAtacante);
        var tableroDefensor = tablero.obtenerTablero(jugadorDefensor);
        if (tableroAtacante == null || tableroDefensor == null)
            return false;
        var monstruoAtacante = tableroAtacante.getZonaMonstruos().obtenerCarta(posicionAtacante);
        if (monstruoAtacante == null)
            return false;
        // Verificar que el oponente no tenga monstruos en su campo
        boolean campoDefensorVacio = true;
        for (int i = 0; i < tableroDefensor.getZonaMonstruos().getCantidadSlotsLibres(); i++) {
            var casilla = tableroDefensor.getZonaMonstruos().getSlot(i);
            if (casilla != null && !casilla.estaLibre()) {
                campoDefensorVacio = false;
                break;
            }
        }
        if (!campoDefensorVacio) {
            System.out.println("Error: No puedes atacar directamente si tu oponente tiene monstruos en el campo.");
            return false;
        }
        int danio = monstruoAtacante.getAtaque();
        jugadorDefensor.perderVida(danio);
        System.out.println(jugadorAtacante.getNombre() + " atacó directamente a " +
                jugadorDefensor.getNombre() + " con " + monstruoAtacante.getNombre() +
                " causando " + danio + " puntos de daño.");
        return true;
    }
}