package yugioop.controlador;

import java.util.List;

import yugioop.modelo.carta.Carta;
import yugioop.modelo.carta.CartaMonstruo;
import yugioop.modelo.juego.Juego;
import yugioop.modelo.jugador.Jugador;
import yugioop.modelo.tablero.Tablero;
import yugioop.utils.CargaDeCartas;
import yugioop.utils.CartaJson;
import yugioop.vista.VistaTablero;
import yugioop.vista.VistaUtils;
import yugioop.modelo.jugador.Mazo;
import yugioop.modelo.turno.FaseTurno;




/**
 * Controlador principal del juego Yu-Gi-Oh.
 * Coordina la interacción entre el tablero y el sistema de turnos.
 */
public class ControladorJuego {
    private Juego juego;
    private Jugador jugador1;
    private Jugador jugador2;
    private Tablero tablero;
    private FaseTurno faseActual;

    public void iniciarJuego(String nombreJ1, String nombreJ2) {
        cargarJugadores(nombreJ1, nombreJ2);
        cargarYAsignarMazos();
        this.juego = new Juego(jugador1, jugador2);
        this.tablero = this.juego.getTablero();
        this.faseActual = this.juego.getFaseActual();
        decidirJugadorInicial();
        repartirCartasIniciales();
        mostrarEstadoInicial();
    }

    private void cargarJugadores(String nombreJ1, String nombreJ2) {
        jugador1 = new Jugador(nombreJ1);
        jugador2 = new Jugador(nombreJ2);
    }

    private void cargarYAsignarMazos() {
        String rutaMazoJ1 = "src/main/java/yugioop/resources/mazo1.json";
        String rutaMazoJ2 = "src/main/java/yugioop/resources/maso2.json";

        List<CartaJson> cartasJson1 = CargaDeCartas.cargarCartas(rutaMazoJ1);
        List<CartaJson> cartasJson2 = CargaDeCartas.cargarCartas(rutaMazoJ2);

        List<Carta> mazo1 = CargaDeCartas.convertirACartas(cartasJson1);
        List<Carta> mazo2 = CargaDeCartas.convertirACartas(cartasJson2);

        for (Carta c : mazo1) {
            jugador1.setMazo(c);
        }
        for (Carta c : mazo2) {
            jugador2.setMazo(c);
        }
    }

    private void decidirJugadorInicial() {
        juego.elegirJugadorInicial();
        VistaUtils.mostrarMensaje("El jugador que inicia es: " + juego.getJugadorActual().getNombre());
    }

    private void repartirCartasIniciales() {
        jugador1.robarCartasMazo(6);
        jugador2.robarCartasMazo(6);
    }

    private void mostrarEstadoInicial() {
        VistaTablero.mostrarEstadoJuego(jugador1, jugador2);
    }

    // TODO: IMPORTANTE - FLUJO DEL JUEGO
    public void bucleJuego() {
        boolean juegoActivo = true;
        while (juegoActivo && !juego.juegoFinalizado()) {
            Jugador actual = juego.getJugadorActual();
            VistaUtils.mostrarMensaje("Turno de: " + actual.getNombre());
            VistaTablero.mostrarCartasEnMano(actual.getMano().getCartas());
    
            VistaUtils.mostrarMensaje("1. Colocar carta en tablero");
            VistaUtils.mostrarMensaje("2. Robar carta del mazo");
            VistaUtils.mostrarMensaje("3. Pasar turno");
            VistaUtils.mostrarMensaje("4. Ver tablero");
            VistaUtils.mostrarMensaje("5. Salir");
    
            String opcion = VistaUtils.leerEntrada("Seleccione una opción: ");
            switch (opcion) {
                case "1":
                    // Aquí deberías pedir el índice y el slot
                    int indiceMano = Integer.parseInt(VistaUtils.leerEntrada("Ingrese el índice de la carta en mano: "));
                    int slot = Integer.parseInt(VistaUtils.leerEntrada("Ingrese el slot: "));
                    colocarCartaEnTablero(indiceMano, slot);
                    break;
                case "2":
                    actual.robarCartasMazo(1);
                    break;
                case "3":
                    juego.pasarTurno();
                    break;
                case "4":
                    VistaTablero.mostrarEstadoJuego(actual, juego.getJugadorOponente());
                    break;
                case "5":
                    juegoActivo = false;
                    break;
                default:
                    VistaUtils.mostrarError("Opción no válida.");
            }
        }
        VistaUtils.mostrarMensaje("Juego finalizado. ¡Gracias por jugar!");
    }

    public boolean colocarCartaEnTablero(int indiceMano, int slot) {
        Jugador jugadorActual = juego.getJugadorActual();
        Carta carta = jugadorActual.getMano().obtenerCarta(indiceMano);
    
        boolean exito = juego.colocarCartaEnTablero(jugadorActual, carta, slot);
        if (!exito) {
            VistaUtils.mostrarError("No se pudo colocar la carta en el slot seleccionado.");
        }
        return exito;
    }

    public void robarCartaJugadorActual() {
        Jugador jugadorActual = juego.getJugadorActual();
        jugadorActual.robarCartasMazo(1);
    }

    public void pasarTurno() {
        juego.pasarTurno();
    }

    public void mostrarEstadoTablero() {
        VistaTablero.mostrarEstadoJuego(jugador1, jugador2);
    }

    public Jugador getJugadorActual() {
        return this.juego.getJugadorActual();
    }

    public Jugador getJugadorOponente() {
        Jugador actual = this.juego.getJugadorActual();
        if (actual.equals(jugador1)) {
            return jugador2;
        } else {
            return jugador1;
        }
    }

    // JUGADOR INCIIAL
    public Jugador getJugadorInicial() {
        return this.juego.elegirJugadorInicial();
    }


    public FaseTurno getFaseActual() {
        return this.juego.getFaseActual();
    }

    public void avanzarFase() {
        this.juego.avanzarFase();
    }

    public boolean irAFaseBatalla() {
        return this.juego.irAFaseBatalla();
    }

    
}