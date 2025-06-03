package yugioop;

import yugioop.vista.VistaTerminal;

/**
 * Clase principal para iniciar el juego Yu-Gi-OOP.
 */
public class Main {
    public static void main(String[] args) {        
        // Usar las vistas
        VistaTerminal vista = new VistaTerminal();
        vista.iniciar();
    }
}