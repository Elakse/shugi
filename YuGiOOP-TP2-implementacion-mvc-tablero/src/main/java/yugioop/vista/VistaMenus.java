package yugioop.vista;

import yugioop.modelo.turno.FaseTurno;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Clase encargada de gestionar los menús de las diferentes fases del juego.
 */
public class VistaMenus {
    
    /**
     * Muestra el menú correspondiente a la fase actual
     * @param faseActual Fase actual del turno
     * @return String con el título del menú
     */
    public static String getTituloMenuFase(FaseTurno faseActual) {
        switch (faseActual) {
            case DRAW:
                return AsciiArt.AZUL + "== FASE DE ROBO ==" + AsciiArt.RESET;
            case STANDBY:
                return AsciiArt.AZUL + "== FASE DE PREPARACIÓN ==" + AsciiArt.RESET;
            case PRINCIPAL1:
                return AsciiArt.VERDE + "== FASE PRINCIPAL ==" + AsciiArt.RESET;
            case BATALLA:
                return AsciiArt.ROJO + "== FASE DE BATALLA ==" + AsciiArt.RESET;
            case CAMBIO:
                return AsciiArt.VERDE + "== FASE DE CAMBIO ==" + AsciiArt.RESET;
            case FINAL:
                return AsciiArt.AMARILLO + "== FASE FINAL ==" + AsciiArt.RESET;
            default:
                return "== FASE DESCONOCIDA ==";
        }
    }
    
    /**
     * Muestra las opciones disponibles para la fase actual
     * @param faseActual Fase actual del turno
     */
    public static void mostrarOpcionesFase(FaseTurno faseActual) {
        VistaUtils.mostrarMensaje(getTituloMenuFase(faseActual));
        
        switch (faseActual) {
            case DRAW:
                VistaUtils.mostrarMensaje("1. Robar carta");
                VistaUtils.mostrarMensaje("2. Ver tablero");
                VistaUtils.mostrarMensaje("3. Ver mano");
                VistaUtils.mostrarMensaje("4. Salir");
                break;
                
            case STANDBY:
                VistaUtils.mostrarMensaje("1. Continuar a fase principal");
                VistaUtils.mostrarMensaje("2. Ver tablero");
                VistaUtils.mostrarMensaje("3. Ver mano");
                VistaUtils.mostrarMensaje("4. Salir");
                break;
                
            case PRINCIPAL1:
                VistaUtils.mostrarMensaje("1. Invocar monstruo");
                VistaUtils.mostrarMensaje("2. Colocar carta mágica");
                VistaUtils.mostrarMensaje("3. Colocar carta trampa");
                VistaUtils.mostrarMensaje("4. Activar carta mágica");
                VistaUtils.mostrarMensaje("5. Cambiar posición de monstruo");
                VistaUtils.mostrarMensaje("6. Continuar a fase de batalla");
                VistaUtils.mostrarMensaje("7. Ver tablero");
                VistaUtils.mostrarMensaje("8. Ver mano");
                VistaUtils.mostrarMensaje("9. Salir");
                break;
                
            case BATALLA:
                VistaUtils.mostrarMensaje("1. Declarar ataque");
                VistaUtils.mostrarMensaje("2. Continuar a fase de cambio");
                VistaUtils.mostrarMensaje("3. Ver tablero");
                VistaUtils.mostrarMensaje("4. Ver mano");
                VistaUtils.mostrarMensaje("5. Salir");
                break;
                
            case CAMBIO:
                VistaUtils.mostrarMensaje("1. Cambiar posición de monstruo");
                VistaUtils.mostrarMensaje("2. Continuar a fase final");
                VistaUtils.mostrarMensaje("3. Ver tablero");
                VistaUtils.mostrarMensaje("4. Ver mano");
                VistaUtils.mostrarMensaje("5. Salir");
                break;
                
            case FINAL:
                VistaUtils.mostrarMensaje("1. Terminar turno");
                VistaUtils.mostrarMensaje("2. Ver tablero");
                VistaUtils.mostrarMensaje("3. Ver mano");
                VistaUtils.mostrarMensaje("4. Salir");
                break;
                
            default:
                VistaUtils.mostrarMensaje("1. Continuar");
                VistaUtils.mostrarMensaje("2. Salir");
                break;
        }
    }
}
