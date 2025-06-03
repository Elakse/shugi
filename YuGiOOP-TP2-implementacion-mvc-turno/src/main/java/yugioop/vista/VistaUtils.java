package yugioop.vista;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import java.io.IOException;
import java.util.Scanner;

/**
 * Clase de utilidades para la interfaz de terminal.
 * Contiene métodos para mostrar mensajes, limpiar pantalla, pausar, etc.
 */
public class VistaUtils {
    private static Scanner scanner = new Scanner(System.in);
    private static Terminal terminal;
    private static LineReader reader;
    
    static {
        try {
            terminal = TerminalBuilder.builder()
                    .system(true)
                    .build();
            
            reader = LineReaderBuilder.builder()
                    .terminal(terminal)
                    .build();
        } catch (IOException e) {
            System.err.println("Error al inicializar terminal: " + e.getMessage());
        }
    }
    
    /**
     * Limpia la pantalla de la terminal
     */
    public static void limpiarPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
    /**
     * Muestra un mensaje en la consola
     * @param mensaje Mensaje a mostrar
     */
    public static void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
    
    /**
     * Pausa la ejecución hasta que el usuario presione Enter
     */
    public static void pausar() {
        System.out.println(AsciiArt.VERDE + "Presiona Enter para continuar..." + AsciiArt.RESET);
        scanner.nextLine();
    }
    
    /**
     * Lee una entrada del usuario con un prompt
     * @param prompt Texto del prompt
     * @return String con la entrada del usuario
     */
    public static String leerEntrada(String prompt) {
        if (reader != null) {
            return reader.readLine(prompt);
        } else {
            System.out.print(prompt);
            return scanner.nextLine();
        }
    }
    
    /**
     * Lee una opción numérica del usuario, validando que sea un número entre min y max
     * @param prompt Texto del prompt
     * @param min Valor mínimo aceptado
     * @param max Valor máximo aceptado
     * @return int con la opción seleccionada
     */
    public static int leerOpcion(String prompt, int min, int max) {
        int opcion = -1;
        boolean valido = false;
        
        while (!valido) {
            try {
                String entrada = leerEntrada(prompt);
                opcion = Integer.parseInt(entrada);
                
                if (opcion >= min && opcion <= max) {
                    valido = true;
                } else {
                    mostrarMensaje(AsciiArt.ROJO + "Opción inválida. Debe estar entre " + min + " y " + max + AsciiArt.RESET);
                }
            } catch (NumberFormatException e) {
                mostrarMensaje(AsciiArt.ROJO + "Por favor, ingresa un número válido." + AsciiArt.RESET);
            }
        }
        
        return opcion;
    }
    
    /**
     * Muestra un mensaje de error en la consola
     * @param error Mensaje de error a mostrar
     */
    public static void mostrarError(String error) {
        mostrarMensaje(AsciiArt.ROJO + "ERROR: " + error + AsciiArt.RESET);
    }
}
