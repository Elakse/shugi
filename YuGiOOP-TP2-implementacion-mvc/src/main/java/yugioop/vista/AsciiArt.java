package yugioop.vista;

/**
 * Clase para recursos visuales ASCII, como títulos y colores para la terminal.
 */
public class AsciiArt {
    // Constantes de colores ANSI para terminal
    public static final String RESET = "\u001B[0m";
    public static final String NEGRO = "\u001B[30m";
    public static final String ROJO = "\u001B[31m";
    public static final String VERDE = "\u001B[32m";
    public static final String AMARILLO = "\u001B[33m";
    public static final String AZUL = "\u001B[34m";
    public static final String MAGENTA = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String BLANCO = "\u001B[37m";
    
    /**
     * Retorna el título ASCII del juego
     * @return String con el título ASCII del juego
     */
    public static String getTituloJuego() {
        return AMARILLO +
        "____    ____  __    __       _______  __       ______     ______   .______  \n" +
        "\\   \\  /   / |  |  |  |     /  _____||  |     /  __  \\   /  __  \\  |   _  \\ \n" +
        " \\   \\/   /  |  |  |  |    |  |  __  |  |    |  |  |  | |  |  |  | |  |_)  |\n" +
        "  \\_    _/   |  |  |  |    |  | |_ | |  |    |  |  |  | |  |  |  | |   ___/ \n" +
        "    |  |     |  `--'  |    |  |__| | |  |    |  `--'  | |  `--'  | |  |     \n" +
        "    |__|      \\______/      \\______| |__|     \\______/   \\______/  | _|     " +
        RESET;
    }
    
    /**
     * Retorna un banner de bienvenida al juego
     * @return String con el banner de bienvenida
     */
    public static String getBannerBienvenida() {
        return VERDE + "=====================================================\n" +
               "          ¡BIENVENIDO A YU-GI-OOP!\n" +
               "=====================================================" + RESET;
    }
    
    /**
     * Retorna un separador de secciones
     * @return String con el separador
     */
    public static String getSeparador() {
        return CYAN + "-----------------------------------------------------" + RESET;
    }
}
