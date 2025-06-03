package yugioop.modelo.jugador;

import yugioop.modelo.carta.Carta;

public class Jugador {
    private String nombre;
    // private Cementerio cementerio; // El jugador tendría un cementerio

    public Jugador(String nombre) {
        this.nombre = nombre;
    }
    public String getNombre() { return nombre; }

    // Para el ejemplo de enviar carta al cementerio
    public void enviarAlCementerio(Carta carta) {
        System.out.println(nombre + " envía " + carta.getNombre() + " al cementerio.");
        // Lógica para agregar al objeto this.cementerio
    }
    public void verificarYDescartarExcesoDeMano() {
        // Si mano.size() > 6, pedir al usuario que descarte hasta tener 6
    }
    public void perderVida(int cantidad) {
        // Logica para perder vida
    }
}
