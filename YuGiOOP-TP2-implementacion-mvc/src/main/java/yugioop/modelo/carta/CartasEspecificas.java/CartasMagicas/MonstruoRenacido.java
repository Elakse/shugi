package yugioop.modelo.carta.magica;

import yugioop.modelo.carta.Carta;
import yugioop.modelo.carta.CartaMagica;
import yugioop.modelo.carta.CartaMonstruo;
import yugioop.modelo.carta.TipoMagica;
import yugioop.modelo.jugador.Jugador;
import yugioop.modelo.tablero.Tablero;
// import yugioop.modelo.Cementerio; // Necesario para MonstruoRenacido pero no tengo la clase

public class MonstruoRenacido extends CartaMagica {
    public MonstruoRenacido() {
        super("Monstruo Renacido", "Selecciona un monstruo de tu cementerio o del oponente y colócalo en tu campo en Posición de Ataque o Defensa boca arriba (Invocación Especial).", TipoMagica.NORMAL);
        this.descripcion = "El jugador puede seleccionar una carta de su cementerio y colocarla en su mano.";
    }

    @Override
    public void activar(Jugador activador, Jugador oponente, Tablero tablero, CartaMonstruo monstruoObjetivo) {
        super.activar(activador, oponente, tablero, monstruoObjetivo);
        
    }
}