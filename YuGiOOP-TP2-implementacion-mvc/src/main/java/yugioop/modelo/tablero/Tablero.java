package yugioop.modelo.tablero;

//import yugioop.modelo.carta.Carta;
import yugioop.modelo.carta.CartaMagica;
import yugioop.modelo.carta.CartaMonstruo;
import yugioop.modelo.carta.CartaTrampa;
import yugioop.modelo.jugador.Jugador;
import yugioop.modelo.turno.TurnoManager;
//import yugioop.modelo.carta.trampas.Evento;
//import yugioop.modelo.tablero.*;
import yugioop.modelo.carta.magicas.*;

//import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Representa el tablero completo del juego, que contiene los tableros de ambos
 * jugadores
 * y la casilla de campo compartida.
 */
public class Tablero {
    private ITableroJugador tableroJugador1;
    private Jugador propietarioJugador1;
    private ITableroJugador tableroJugador2;
    private Jugador propietarioJugador2;
    private TurnoManager turnoManager;

    public Tablero(Jugador jugador1, Jugador jugador2, TurnoManager turnoManager) {
        this.propietarioJugador1 = jugador1;
        this.tableroJugador1 = new TableroJugador();
        this.propietarioJugador2 = jugador2;
        this.tableroJugador2 = new TableroJugador();
        this.turnoManager = turnoManager;
    }

    /**
     * Obtiene el tablero correspondiente al jugador especificado.
     * 
     * @param jugador El jugador cuyo tablero se desea obtener.
     * @return El ITableroJugador correspondiente al jugador, o null si el jugador
     *         no es reconocido.
     */
    public ITableroJugador obtenerTablero(Jugador jugador) {
        if (jugador == this.propietarioJugador1) {
            return tableroJugador1;
        } else if (jugador == this.propietarioJugador2) {
            return tableroJugador2;
        }
        System.out.println(
                "Error: Jugador no reconocido en este tablero: " + (jugador != null ? jugador.getNombre() : "null"));
        return null;
    }

    public CartaMagica pedirCartaMagicaPorPosicion(String mensaje) {
        // esto va a ir mas arriba despues cuando se haga el controlador
        // aca se llamaria al metodo del controlador que pide un numero al usuario
        Scanner scanner = new Scanner(System.in);
        int posicion;
        CartaMagica cartaSeleccionada;

        // TODA ESTA LOGICA TIENE QUE SER GENERALIZADA, NO HARDCODEADA COMO AHORA
        do {
            do {
                System.out.println("Ingrese una posición (1-5) para seleccionar una carta mágica:");
                while (!scanner.hasNextInt()) {
                    System.out.println("Por favor, ingrese un número válido (1-5):");
                    scanner.next();
                }
                posicion = scanner.nextInt();
            } while (posicion < 1 || posicion > 5);

            ITableroJugador tableroJugadorActual = obtenerTableroJugadorActual();
            cartaSeleccionada = tableroJugadorActual.obtenerCartaMagica(posicion - 1);
            if (cartaSeleccionada.esActivableADiscrecion()) {
                return cartaSeleccionada;
            }
        } while (true);
    }

    public CartaMonstruo pedirCartaMonstruoPorPosicion(String mensaje) {
        //TODO esto de pedir por terminal va a ir mas arriba despues cuando se haga el
        // controlador
        // se llamaria al metodo que pide un numero al usuario
        Scanner scanner = new Scanner(System.in);
        int posicion;
        do {
            System.out.println("Ingrese una posición (1-5) para seleccionar un monstruo:");
            while (!scanner.hasNextInt()) {
                // aca lo mismo
                System.out.println("Por favor, ingrese un número válido (1-5):");
                scanner.next();
            }
            // aca lo mismo
            posicion = scanner.nextInt();
        } while (posicion < 1 || posicion > 5);

        ITableroJugador tableroJugadorActual = obtenerTablero(obtenerJugadorActual());
        if (tableroJugadorActual != null) {
            return tableroJugadorActual.obtenerCartaMonstruo(posicion - 1);
        }

        return null;
    }

    public void equiparCartaMagica(Equipamiento cartaMagica, CartaMonstruo monstruo) {
        monstruo.equiparCartaMagica(cartaMagica);
        System.out.println("Carta mágica " + cartaMagica.getNombre() + " equipada a " + monstruo.getNombre());
    }

    public void inhabilitarCartaMonstruo(CartaMonstruo monstruo) {
        monstruo.inhabilitar();
        System.out.println("Carta monstruo " + monstruo.getNombre() + " inhabilitada.");
    }

    public void activarCartasMagicasActivas() {
        ITableroJugador tableroJugador = obtenerTableroJugadorActual();
        List<CartaMagica> cartasMagicasActivas = tableroJugador.obtenerCartasMagicasActivas();
        for (CartaMagica carta : cartasMagicasActivas) {
            activarCartaMagica(carta);
        }
    }

    public void activarCartaMagica(CartaMagica carta) {
        if (carta != null && carta.esActivableADiscrecion()) {
            boolean sigueActiva = carta.activar(this);
            if (!sigueActiva) {
                obtenerJugadorActual().enviarAlCementerio(carta);
                System.out.println("Carta " + carta.getNombre() + " enviada al cementerio.");
            }
            System.out.println("Carta " + carta.getNombre() + " activada.");
        } else if (carta == null) {
            System.out.println("La carta no existe.");
        } else {
            System.out.println("La carta " + carta.getNombre() + " no es activable en este momento.");
        }
    }

    public CartaMonstruo obtenerMonstruoPorPosicion(int posicion) {
        ITableroJugador tableroJugadorActual = obtenerTableroJugadorActual();
        return tableroJugadorActual.obtenerCartaMonstruo(posicion - 1);
    }

    public void activarCartaMonstruo(int posicion) {
        CartaMonstruo monstruo = obtenerMonstruoPorPosicion(posicion);
        monstruo.activar(this);
    }

    public void herirJugador(Jugador jugador, int puntos) {
        jugador.perderVida(puntos);
        System.out.println(jugador.getNombre() + " ha recibido " + puntos + " puntos de daño.");
    }

    public void activarCartaTrampa(CartaTrampa carta) {
    }

    public void jugadorRobaCartasMazo(int cantidad){
        Jugador jugadorActual = obtenerJugadorActual();
        //falta la logica del lado de Jugador para robar cartas del mazo
        //ahi habria que verificar si el mazo tiene suficientes cartas (Se roban cartas "de arriba del mazo")
        //si el metodo devuelve *ALGO* habria que matar al Jugador 
        jugadorActual.robarCartasMazo(cantidad);
        System.out.println(jugadorActual.getNombre() + " ha robado " + cantidad + " cartas del mazo.");
    }

    public int jugadorRobaCartasCementerio(int cantidad){
        Jugador jugadorActual = obtenerJugadorActual();
        //falta la logica del lado de Jugador para robar cartas del cementerio
        //ahi habria que usar una funcion privada seleccionarCartasCementerio(int cantidad) internamente
        //para pedir por terminal la lista de cartas a robar del cementerio
        //el metodo devuelve la cantidad de cartas que no se pudieron robar
        int faltan = jugadorActual.robarCartasCementerio(cantidad);
        System.out.println(jugadorActual.getNombre() + " ha robado " + (cantidad - faltan) + " cartas del mazo.");
        if (faltan > 0) {
            System.out.println("No se pudieron robar " + faltan + " cartas del cementerio porque no hay suficientes cartas.");
        }
        return faltan;
    }

    public Jugador obtenerJugadorActual() {
        return turnoManager.getJugadorActual();
    }

    public ITableroJugador obtenerTableroJugadorActual() {
        Jugador jugadorActual = obtenerJugadorActual();
        return obtenerTablero(jugadorActual);
    }

    public void destruirMonstruo(CartaMonstruo monstruo) {
        Jugador jugadorActual = obtenerJugadorActual();
        ITableroJugador tableroJugadorActual = obtenerTablero(jugadorActual);

        jugadorActual.enviarAlCementerio(monstruo);
        int posicion = tableroJugadorActual.obtenerPosicionMonstruo(monstruo);
        tableroJugadorActual.removerMonstruo(posicion);

        System.out.println("Monstruo " + monstruo.getNombre() + " destruido y enviado al cementerio.");
    }

    // TRAMPA

    public void robarCartaTrampa(CartaTrampa trampa, int cantidad) {
        Jugador duenioCarta = this.obtenerJugadorOponente();
        duenioCarta.robarCartasMazo(cantidad);
        duenioCarta.enviarAlCementerio(trampa);
    }

    public Jugador obtenerJugadorOponente() {
        return turnoManager.getJugadorOponente();
    }

    public void descartarCartasAleatoriasOponente(int cantidad) {
        Jugador oponente = obtenerJugadorOponente();
        oponente.descartarCartasAleatorias(cantidad);
    }

    public void recuperarVida(int cantidad) {
        Jugador duenioCarta = turnoManager.getJugadorOponente();
        duenioCarta.ganarVida(cantidad);
    }

    public void daniarAlAtacante(int cantidad) {
        Jugador atacante = turnoManager.getJugadorActual();
        atacante.perderVida(cantidad);
    }
}
