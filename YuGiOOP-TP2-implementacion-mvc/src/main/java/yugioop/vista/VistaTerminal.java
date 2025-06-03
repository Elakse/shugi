package yugioop.vista;

import org.jline.reader.*;
import org.jline.reader.impl.completer.*;
import org.jline.terminal.*;

import yugioop.controlador.ControladorJuego;
import yugioop.modelo.carta.*;
import yugioop.modelo.jugador.Jugador;
import yugioop.modelo.turno.FaseTurno;
import java.io.IOException;
import java.util.*;

/**
 * Vista del juego Yu-Gi-OOP usando JLine para una experiencia de terminal mejorada.
 */
public class VistaTerminal {
    private ControladorJuego controlador;
    private Terminal terminal;
    private LineReader lineReader;
    private boolean juegoActivo = true;
    
    // Referencia a los colores desde AsciiArt para compatibilidad con código existente
    private static final String RESET = AsciiArt.RESET;
    private static final String NEGRO = AsciiArt.NEGRO;
    private static final String ROJO = AsciiArt.ROJO;
    private static final String VERDE = AsciiArt.VERDE;
    private static final String AMARILLO = AsciiArt.AMARILLO;
    private static final String AZUL = AsciiArt.AZUL;
    private static final String MAGENTA = AsciiArt.MAGENTA;
    private static final String CYAN = AsciiArt.CYAN;
    private static final String BLANCO = AsciiArt.BLANCO;
    
    // Constantes adicionales que pueden moverse a AsciiArt en el futuro
    private static final String FONDO_NEGRO = "\u001B[40m";
    private static final String FONDO_ROJO = "\u001B[41m";
    private static final String FONDO_VERDE = "\u001B[42m";
    private static final String FONDO_AMARILLO = "\u001B[43m";
    private static final String FONDO_AZUL = "\u001B[44m";
    private static final String FONDO_MAGENTA = "\u001B[45m";
    private static final String FONDO_CYAN = "\u001B[46m";
    private static final String FONDO_BLANCO = "\u001B[47m";
    private static final String NEGRITA = "\u001B[1m";
    private static final String SUBRAYADO = "\u001B[4m";

    /**
     * Constructor de la vista terminal
     */
    public VistaTerminal() {
        try {
            // Inicializa el terminal y el lector de líneas
            terminal = TerminalBuilder.builder()
                    .system(true)
                    .build();
            
            // Configurar autocompletadores para comandos comunes
            List<Completer> completers = new ArrayList<>();
            completers.add(new StringsCompleter("invocar", "activar", "colocar", "atacar", "cambiar", "avanzar", "salir"));
            
            lineReader = LineReaderBuilder.builder()
                    .terminal(terminal)
                    .completer(new AggregateCompleter(completers))
                    .build();
            
        } catch (IOException e) {
            System.err.println("Error inicializando terminal: " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Inicia el juego solicitando nombres de jugadores.
     */
    public void iniciar() {
        limpiarPantalla();
        mostrarTitulo();
        
        String nombreJugador1 = leerEntrada("Ingrese el nombre del Jugador 1: ");
        String nombreJugador2 = leerEntrada("Ingrese el nombre del Jugador 2: ");
        
        // Creamos los jugadores con mazos de prueba
        Jugador jugador1 = new Jugador(nombreJugador1);
        Jugador jugador2 = new Jugador(nombreJugador2);
        
        // Creamos mazos de prueba
        crearMazoPrueba(jugador1);
        crearMazoPrueba(jugador2);
        
        // Inicializamos el controlador con los jugadores
        controlador = new ControladorJuego(jugador1, jugador2);
        
        // Limpiamos la pantalla para mostrar solo el mensaje de quién comienza
        limpiarPantalla();
        
        // Mostramos quien comienza (decidido por el controlador) y esperamos
        System.out.println(FONDO_AZUL + BLANCO + NEGRITA + "=========================================" + RESET);
        System.out.println(FONDO_AZUL + BLANCO + NEGRITA + "              YU-GI-OOP                 " + RESET);
        System.out.println(FONDO_AZUL + BLANCO + NEGRITA + "=========================================" + RESET);
        System.out.println();
        VistaUtils.mostrarMensaje(AMARILLO + NEGRITA + "Se ha decidido que comienza: " + 
                          controlador.getJugadorInicial() + RESET);
        System.out.println();
        VistaUtils.mostrarMensaje(AMARILLO + "Presiona ENTER para continuar..." + RESET);
        VistaUtils.pausar();
        
        // Iniciamos el juego
        controlador.iniciarJuego();
        
        // Bucle principal del juego
        loopJuego();
    }
    
    private void limpiarPantalla() {
        VistaUtils.limpiarPantalla();
    }

    /**
     * Muestra el título del juego con ASCII art
     */
    private void mostrarTitulo() {
        System.out.println(AsciiArt.getTituloJuego());
        System.out.println(AsciiArt.getBannerBienvenida());
    }

    /**
     * Bucle principal del juego.
     */
    private void loopJuego() {
        while (juegoActivo) {
            mostrarEstadoJuego();
            procesarMenuFase();
        }
        
        mostrarMensaje(VERDE + "Juego finalizado. ¡Gracias por jugar!" + RESET);
    }

    /**
     * Muestra el estado actual del juego con formato mejorado.
     */
    private void mostrarEstadoJuego() {
        limpiarPantalla();
        
        // Cabecera principal - solo se muestra una vez
        System.out.println(FONDO_AZUL + BLANCO + NEGRITA + "=========================================" + RESET);
        System.out.println(FONDO_AZUL + BLANCO + NEGRITA + "              YU-GI-OOP                 " + RESET);
        System.out.println(FONDO_AZUL + BLANCO + NEGRITA + "=========================================" + RESET);
        
        // Información del turno actual
        Jugador jugadorActual = controlador.getJugadorActual();
        Jugador oponente = controlador.getJugadorOponente();
        
        System.out.println(AMARILLO + "Turno: " + NEGRITA + jugadorActual.getNombre() + RESET);
        System.out.println(AMARILLO + "Fase actual: " + NEGRITA + controlador.getFaseActual() + RESET);
        
        // Línea divisoria
        System.out.println(AsciiArt.getSeparador());
        
        // Campo del oponente con título descriptivo
        System.out.println(ROJO + "Oponente: " + NEGRITA + oponente.getNombre() + RESET);
        System.out.println(ROJO + "Puntos de vida: " + oponente.getPuntosDeVida() + RESET);
        VistaTablero.mostrarTableroOponente(oponente);
        
        // Línea divisoria
        System.out.println(AsciiArt.getSeparador());
        
        // Tu campo con título descriptivo
        System.out.println(VERDE + "Tu campo: " + NEGRITA + jugadorActual.getNombre() + RESET);
        System.out.println(VERDE + "Puntos de vida: " + jugadorActual.getPuntosDeVida() + RESET);
        VistaTablero.mostrarTableroJugador(jugadorActual);
        
        // Línea divisoria final
        System.out.println(AsciiArt.getSeparador());
    }

    /**
     * Muestra una representación visual del campo de un jugador.
     */
    private void mostrarCampoJugador(Jugador jugador) {
        VistaTablero.mostrarZonaCartas();
    }

    /**
     * Procesa el menú correspondiente a la fase actual.
     */
    private void procesarMenuFase() {
        FaseTurno faseActual = controlador.getFaseActual();
        
        // Mostrar las opciones del menú para la fase actual
        VistaMenus.mostrarOpcionesFase(faseActual);
        
        switch (faseActual) {
            case DRAW:
                procesarFaseDraw();
                break;
            case STANDBY:
                procesarFaseStandby();
                break;
            case PRINCIPAL1:
                procesarFasePrincipal1();
                break;
            case BATALLA:
                procesarFaseBatalla();
                break;
            case CAMBIO:
                procesarFaseCambio();
                break;
            case FINAL:
                procesarFaseFinal();
                break;
            default:
                mostrarError("Fase no reconocida");
                break;
        }
    }

    /**
     * Procesa la fase de robo (DRAW).
     */
    private void procesarFaseDraw() {
        // VistaMenus ya muestra las opciones adecuadas para la fase actual
        
        String opcion = VistaUtils.leerEntrada("Seleccione una opción: ");
        
        switch (opcion) {
            case "1":
                VistaUtils.mostrarMensaje(VERDE + controlador.getJugadorActual().getNombre() + " roba una carta." + RESET);
                controlador.avanzarFase();
                break;
            case "4": // Según las opciones definidas en VistaMenus
                juegoActivo = false;
                break;
            default:
                VistaUtils.mostrarError("Opción no válida.");
                break;
        }
    }

    /**
     * Procesa la fase de espera (STANDBY).
     */
    private void procesarFaseStandby() {
        // VistaMenus ya muestra las opciones adecuadas para la fase actual
        
        String opcion = VistaUtils.leerEntrada("Seleccione una opción: ");
        
        switch (opcion) {
            case "1":
                controlador.avanzarFase();
                break;
            case "4": // Según las opciones definidas en VistaMenus
                juegoActivo = false;
                break;
            default:
                VistaUtils.mostrarError("Opción no válida.");
                break;
        }
    }

    /**
     * Procesa la fase Principal 1.
     */
    private void procesarFasePrincipal1() {
        // VistaMenus ya muestra las opciones adecuadas para la fase actual
        
        String opcion = VistaUtils.leerEntrada("Seleccione una opción: ");
        
        switch (opcion) {
            case "1":
                procesarInvocacionMonstruo();
                break;
            case "2":
                procesarActivacionMagia();
                break;
            case "3":
                procesarColocacionTrampa();
                break;
            case "6": // En VistaMenus, opción 6 es continuar a fase de batalla
                if (controlador.irAFaseBatalla()) {
                    VistaUtils.mostrarMensaje(VERDE + "Avanzando a fase de Batalla..." + RESET);
                } else {
                    VistaUtils.mostrarError("No se puede ir a fase de Batalla.");
                }
                break;
            case "5": // Cambiar posición de monstruo
                procesarCambioPosicion();
                break;
            case "9": // Según las opciones definidas en VistaMenus para salir
                juegoActivo = false;
                break;
            case "7": // Ver tablero
                mostrarEstadoJuego();
                break;
            case "8": // Ver mano
                VistaTablero.mostrarCartasEnMano(controlador.getJugadorActual().getMano());
                break;
            default:
                VistaUtils.mostrarError("Opción no válida.");
                break;
        }
    }

    /**
     * Procesa la fase de Batalla.
     */
    private void procesarFaseBatalla() {
        // VistaMenus ya muestra las opciones adecuadas para la fase actual
        
        String opcion = VistaUtils.leerEntrada("Seleccione una opción: ");
        
        switch (opcion) {
            case "1":
                procesarDeclaracionAtaque();
                break;
            case "3": // En VistaMenus, opción 3 es avanzar a la siguiente fase
                controlador.avanzarFase();
                break;
            case "4": // En VistaMenus, opción 4 es salir del juego
                juegoActivo = false;
                break;
            case "2": // Ver tablero
                mostrarEstadoJuego();
                break;
            default:
                VistaUtils.mostrarError("Opción no válida.");
                break;
        }
    }

    /**
     * Procesa la fase de Cambio.
     */
    private void procesarFaseCambio() {
        // VistaMenus ya muestra las opciones adecuadas para la fase actual
        
        String opcion = VistaUtils.leerEntrada("Seleccione una opción: ");
        
        switch (opcion) {
            case "1":
                procesarCambioPosicion();
                break;
            case "3": // En VistaMenus, opción 3 es avanzar a la siguiente fase
                controlador.avanzarFase();
                break;
            case "4": // En VistaMenus, opción 4 es salir del juego
                juegoActivo = false;
                break;
            case "2": // Ver tablero
                mostrarEstadoJuego();
                break;
            default:
                VistaUtils.mostrarError("Opción no válida.");
                break;
        }
    }

    /**
     * Procesa la fase Final.
     */
    private void procesarFaseFinal() {
        // VistaMenus ya muestra las opciones adecuadas para la fase actual
        
        String opcion = VistaUtils.leerEntrada("Seleccione una opción: ");

        switch (opcion) {
            case "1":
                controlador.avanzarFase(); // Esto finalizará el turno actual
                break;
            case "3": // En VistaMenus, la opción 3 es salir del juego en fase final
                juegoActivo = false;
                break;
            case "2": // Ver tablero
                mostrarEstadoJuego();
                break;
            default:
                VistaUtils.mostrarError("Opción no válida.");
                break;
        }
    }

    // Métodos para procesar acciones específicas

    private void procesarInvocacionMonstruo() {
        mostrarTitulo("Invocación de Monstruo");
        
        // Simulación de selección de carta
        CartaMonstruo monstruo = new CartaMonstruo("Monstruo de prueba", 1000, 800);
        
        boolean posicionAtaque = leerEntrada("¿Invocar en posición de ataque? (s/n): ").toLowerCase().startsWith("s");
        boolean bocaArriba = leerEntrada("¿Invocar boca arriba? (s/n): ").toLowerCase().startsWith("s");
        
        if (controlador.invocarMonstruo(monstruo, posicionAtaque, bocaArriba)) {
            mostrarMensaje(VERDE + "¡Monstruo invocado con éxito!" + RESET);
            VistaUtils.pausar();
        } else {
            mostrarError("No se pudo invocar el monstruo.");
            VistaUtils.pausar();
        }
    }



    private void procesarActivacionMagia() {
        mostrarTitulo("Activación de Carta Mágica");
        
        // Simulación de selección de carta
        CartaMagica magia = new CartaMagica("Magia de prueba", TipoMagica.NORMAL, (tablero, carta, jugador) -> {
            System.out.println("Activando efecto de carta mágica normal");
        });
        if (controlador.activarCartaMagica(magia)) {
            mostrarMensaje(VERDE + "¡Carta mágica activada con éxito!" + RESET);
            VistaUtils.pausar();
        } else {
            mostrarError("No se pudo activar la carta mágica.");
            VistaUtils.pausar();
        }
    }

    private void procesarColocacionTrampa() {
        mostrarTitulo("Colocación de Carta Trampa");
        
        // Simulación de selección de carta
        CartaTrampa trampa = new CartaTrampa("Trampa de prueba", (tablero, atacante, defensor, jugador) -> {
            System.out.println("Activando efecto de carta trampa");
        });

        if (controlador.colocarCartaTrampa(trampa)) {
            mostrarMensaje(VERDE + "¡Carta trampa colocada con éxito!" + RESET);
            VistaUtils.pausar();
        } else {
            mostrarError("No se pudo colocar la carta trampa.");
            VistaUtils.pausar();
        }
    }

    private void procesarDeclaracionAtaque() {
        mostrarTitulo("Declaración de Ataque");
        
        // Simulación de selección de cartas
        CartaMonstruo atacante = new CartaMonstruo("Atacante", 1500, 1000);
        CartaMonstruo defensor = new CartaMonstruo("Defensor", 1000, 1500);
        
        if (controlador.declararAtaque(atacante, defensor)) {
            mostrarMensaje(VERDE + "¡Ataque declarado con éxito!" + RESET);
            VistaUtils.pausar();
        } else {
            mostrarError("No se pudo declarar el ataque.");
            VistaUtils.pausar();
        }
    }

    private void procesarCambioPosicion() {
        mostrarTitulo("Cambio de Posición");
        
        // Simulación de selección de carta
        CartaMonstruo monstruo = new CartaMonstruo("Monstruo", 1000, 1000);
        
        if (controlador.alternarPosicionMonstruo(monstruo)) {
            mostrarMensaje(VERDE + "¡Posición alternada con éxito!" + RESET);
            VistaUtils.pausar();
        } else {
            mostrarError("No se pudo alternar la posición.");
            VistaUtils.pausar();
        }
    }

    // Métodos auxiliares para UI

    private void crearMazoPrueba(Jugador jugador) {
        // Aquí crearías un mazo con cartas de prueba
        mostrarMensaje(AMARILLO + "Creando mazo de prueba para " + jugador.getNombre() + RESET);
    }

    private void mostrarTitulo(String titulo) {
        System.out.println("\n" + FONDO_MAGENTA + BLANCO + " " + titulo + " " + RESET);
    }

    private void mostrarOpcionesFase(String tituloFase, String[] opciones) {
        System.out.println("\n" + FONDO_AZUL + BLANCO + " " + tituloFase + " " + RESET);
        for (String opcion : opciones) {
            System.out.println(BLANCO + opcion + RESET);
        }
    }

    private String leerEntrada(String prompt) {
        return VistaUtils.leerEntrada(prompt);
    }

    private void mostrarMensaje(String mensaje) {
        VistaUtils.mostrarMensaje(mensaje);
    }

    private void mostrarError(String error) {
        VistaUtils.mostrarError(error);
    }
}