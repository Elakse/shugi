package yugioop.modelo.carta;

import yugioop.modelo.jugador.ContextoJugador;
import yugioop.modelo.mesa.MesaYugioh;

public class CartaMonstruo extends Carta {
    private int ataque;
    private int ataqueActual;
    private int defensa;
    private int defensaActual;
    private boolean modoAtaque;
    private int nivel;
    boolean inhabilitada;

    public CartaMonstruo(String nombre, int ataque, int defensa, int nivel) {
        super(nombre);
        this.ataque = ataque;
        this.ataqueActual = ataque;
        this.defensa = defensa;
        this.defensaActual = defensa;
        this.modoAtaque = true;
        this.nivel = nivel;
        this.inhabilitada = false; // Por defecto no está inhabilitada
    }

    public int sacrificiosNecesarios() {
        if (this.nivel <= 4) {
            return 0;
        } else if (this.nivel <= 6) {
            return 1;
        } else {
            return 2;
        }
    }
    
    @Override
    public boolean esActivableADiscrecion(){
        return false;
    }

    public void activar(MesaYugioh mesa, int posObjetivo) {
        if (estaInhabilitada()) {
            throw new IllegalStateException("El monstruo está inhabilitado.");
        }

        ContextoJugador actual = mesa.obtenerContextoJugadorActual();
        ContextoJugador oponente = mesa.obtenerContextoJugadorOponente();
        CartaMonstruo objetivo = oponente.obtenerCartaMonstruo(posObjetivo);

        System.out.printf("%s ataca a %s%n", nombre, objetivo.getNombre());

        if (objetivo.estaEnModoAtaque()) {
            resolverModoAtaque(mesa, actual, oponente, objetivo);
        } else {
            resolverModoDefensa(mesa, actual, oponente, objetivo);
        }
    }

    private void resolverModoAtaque(MesaYugioh mesa, ContextoJugador actual, ContextoJugador oponente, CartaMonstruo objetivo) {
        int danio = computarAtaqueContraModoAtaque(objetivo);

        if (danio > 0) {
            // Destruyo al oponente y le hago daño
            oponente.destruirCartaMonstruo(objetivo);
            mesa.jugadorOponentePierdeVida(danio);
        } else if (danio < 0) {
            // Me destruyen a mí y recibo daño
            actual.destruirCartaMonstruo(this);
            mesa.jugadorActualPierdeVida(-danio);
        } else {
            // Empate: ambos se destruyen
            actual.destruirCartaMonstruo(this);
            oponente.destruirCartaMonstruo(objetivo);
        }
    }

    private void resolverModoDefensa(MesaYugioh mesa, ContextoJugador actual, ContextoJugador oponente, CartaMonstruo objetivo) {
        int danio = computarAtaqueContraModoDefensa(objetivo);

        if (danio > 0) {
            // Destruyo al monstruo en defensa
            oponente.destruirCartaMonstruo(objetivo);
        } else if (danio < 0) {
            // Me destruyen a mí y recibo daño de penetración
            actual.destruirCartaMonstruo(this);
            mesa.jugadorActualPierdeVida(-danio);
        } else {
            // Empate: ambos se destruyen
            actual.destruirCartaMonstruo(this);
            oponente.destruirCartaMonstruo(objetivo);
        }
    }


    /*public void activar(MesaYugioh mesa, int posObjetivo) {
        if (!this.estaInhabilitada()) {
            System.out.println(this.nombre + " ataca a " + monstruo.getNombre());
            if(monstruo.estaEnModoAtaque()) {
                int danio = this.computarAtaqueContraModoAtaque(monstruo);
                if (danio > 0) {
                    tablero.destruirMonstruo(monstruo);
                    tablero.herirJugador(tablero.obtenerJugadorOponente(), danio);
                } else if (danio < 0) {
                    tablero.destruirMonstruo(this);
                    tablero.herirJugador(tablero.obtenerJugadorActual(), -danio);
                } else {
                    tablero.destruirMonstruo(monstruo);
                    tablero.destruirMonstruo(this);
                }
            }
            else {
                int danio = this.computarAtaqueContraModoDefensa(monstruo);
                if(danio < 0){
                    tablero.destruirMonstruo(this);
                    tablero.herirJugador(tablero.obtenerJugadorActual(), -danio);
                } else if (danio > 0) { //Danio positivo significa que el monstruo en defensa fue destruido
                    tablero.destruirMonstruo(monstruo);
                } else {
                    tablero.destruirMonstruo(monstruo);
                    tablero.destruirMonstruo(this);
                }
            }
        } else {
            System.out.println("El monstruo está inhabilitado.");
        }
    }*/

    public void incrementarAtkActual(int dif){
        cambiarAtaqueActual(ataque + dif);
    }

    public void reducirAtkActual(int dif){
        cambiarAtaqueActual(ataque - dif);
    }

    public void incrementarDefActual(int dif){
        cambiarDefensaActual(defensa + dif);
    }

    public void reducirDefActual(int dif){
        cambiarDefensaActual(defensa - dif);
    }

    public void cambiarAtaqueActual(int atk){
        this.ataqueActual = atk;
    }

    public void cambiarDefensaActual(int def){
        this.defensaActual = def;
    }

    public int computarAtaqueContraModoAtaque(CartaMonstruo monstruoAtacado) {
        int atacanteATK = this.ataqueActual;
        int atacadoATK = monstruoAtacado.ataqueActual;
        int danio = 0;
        
        if (atacanteATK > atacadoATK) {
            System.out.println(monstruoAtacado.getNombre() + " ha sido destruido.");
            danio = atacanteATK - atacadoATK;
        } else if (atacanteATK < atacadoATK) {
            System.out.println(this.getNombre() + " ha sido destruido.");
            danio = atacanteATK - atacadoATK;
        } else {
            System.out.println("Ambos monstruos han sido destruidos.");
            danio = 0;
        }
        
        return danio;
    }
    
    public int computarAtaqueContraModoDefensa(CartaMonstruo monstruoAtacado) {
        int atacanteATK = this.ataqueActual;
        int atacadoDEF = monstruoAtacado.defensaActual;
        int danio = 0;
        
        if (atacanteATK > atacadoDEF) {
            danio = 1;
        } else if (atacanteATK < atacadoDEF) {
            danio = atacanteATK - atacadoDEF;
        }
        return danio;
    }

    public void reestablecerAtributos(){
        this.ataqueActual = ataque;
        this.defensaActual = defensa;
    }
    
    public void cambiarModo() { 
        // Toggling the attack mode of the monster
        this.modoAtaque = !this.modoAtaque;
        System.out.println(this.nombre + " ha cambiado a modo " + (this.modoAtaque ? "ataque." : "defensa."));
    }
    
    public void activar() { 
        // Lógica para activar efectos de la carta monstruo
        System.out.println(this.nombre + " activado."); 
    }
    
    public boolean estaInhabilitada() {
        return inhabilitada;
    }

    public void habilitar() {
        this.inhabilitada = true;
        System.out.println(this.nombre + " ha sido inhabilitada.");
    }

    public void inhabilitar() {
        this.inhabilitada = false;
        System.out.println(this.nombre + " ha sido habilitada.");
    }

    public int getAtaque() { 
        return this.ataque;
    }
    
    public int getDefensa() { 
        return this.defensa; 
    }
    
    public boolean estaEnModoAtaque() {
        return this.modoAtaque;
    }

    public boolean tienecartaMagicaEquipada() {
        return (this.ataqueActual != this.ataque || this.defensaActual != this.defensa);
    }
    
    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }
    
    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }
    
    public int getNivel() {
        return this.nivel;
    }
    
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public TipoZona getTipoZona() { return TipoZona.MONSTRUO; }

    public String getTipo(){
        return "Monstruo";
    }
}