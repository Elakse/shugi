package yugioop.modelo.tablero;
import yugioop.modelo.carta.Carta;
import yugioop.modelo.carta.CartaMagica;
import yugioop.modelo.carta.CartaTrampa;

/**
 * Representa una casilla en la zona de magia y trampas del tablero.
 * Puede contener cartas de tipo CartaMagica o CartaTrampa.
 */
public class CasillaMagiaTrampa extends CasillaBase<Carta> {

    public CasillaMagiaTrampa() {
        super();
    }

    /**
     * Verifica si la casilla de magia/trampa está libre.
     * @return true si la casilla está libre, false si ya tiene un ocupante.
     */
    public boolean estaLibre() { 
        return this.ocupante == null;
    }

    /**
     * Intenta colocar una Carta (Mágica o Trampa) en la casilla.
     * @param carta La Carta a colocar.
     * @return true si se colocó exitosamente, false si la casilla ya está ocupada o si la carta no es válida.
     */
    public boolean colocarCarta(Carta carta) {
        if (!estaLibre()) {
            System.out.println("Casilla de magia/trampa ya ocupada.");
            return false;
        }
        
        if (!(carta instanceof CartaMagica || carta instanceof CartaTrampa)) {
            System.out.println("Error: Solo se pueden colocar Cartas Mágicas o Trampas en esta casilla.");
            return false;
        }
        
        this.ocupante = carta;
        
        if (carta instanceof CartaTrampa) {
            System.out.println("Trampa " + carta.getNombre() + " colocada boca abajo.");
        } else if (carta instanceof CartaMagica) {
            CartaMagica cartaMagica = (CartaMagica) carta;
            if (cartaMagica.esDeTipoCampo()) {
                System.out.println("Mágica de campo " + carta.getNombre() + " colocada. Debe ser enviada a la zona de campo para activarse.");
            } else {
                System.out.println("Mágica " + carta.getNombre() + " colocada.");
                carta.revelar();
            }
        }
        
        return true;
    }

    /**
     * Obtiene el ocupante de la casilla.
     * @return La Carta que ocupa la casilla, o null si está libre.
     */
    public Carta getOcupante() {
        return this.ocupante;
    }

    /**
     * Remueve el ocupante de la casilla.
     * @return La Carta que fue removida, o null si no había ocupante.
     */
    public Carta removerOcupante() {
        Carta temp = this.ocupante;
        this.ocupante = null;
        return temp;
    }
}
