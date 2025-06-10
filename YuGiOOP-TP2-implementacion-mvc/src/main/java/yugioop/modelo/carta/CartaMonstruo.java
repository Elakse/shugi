package yugioop.modelo.carta;

import yugioop.modelo.tablero.Tablero;
import yugioop.modelo.carta.magicas.Equipamiento;

public class CartaMonstruo extends Carta {
    private int ataque;
    private int defensa;
    private boolean modoAtaque;
    private int nivel;
    private Equipamiento cartaMagicaEquipada;
    boolean inhabilitada;

    public CartaMonstruo(String nombre, int ataque, int defensa, int nivel) {
        super(nombre);
        this.ataque = ataque;
        this.defensa = defensa;
        this.modoAtaque = true;
        this.nivel = nivel;
        this.cartaMagicaEquipada = null;
        this.inhabilitada = false; // Por defecto no está inhabilitada
    }
    
    @Override
    public boolean esActivableADiscrecion(){
        return false;
    }

    public void activar(Tablero tablero){
        CartaMonstruo monstruo = tablero.pedirCartaMonstruoPorPosicion("Elegir un monstruo a atacar");
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
    }

    public int computarAtaqueContraModoAtaque(CartaMonstruo monstruoAtacado) {
        int atacanteATK = this.computarATK();
        int atacadoATK = monstruoAtacado.computarATK();
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
        int atacanteATK = this.computarATK();
        int atacadoDEF = monstruoAtacado.computarDEF();
        int danio = 0;
        
        if (atacanteATK > atacadoDEF) {
            danio = 1;
        } else if (atacanteATK < atacadoDEF) {
            danio = atacanteATK - atacadoDEF;
        }
        return danio;
    }

    public int computarATK() {
        if(this.cartaMagicaEquipada != null) {
            // Si tiene una carta mágica aplicada, se suma el diferencial de ataque
            return this.ataque + this.cartaMagicaEquipada.getDiferencialAtaque();
        } else {
            // Si no tiene carta mágica aplicada, se retorna el ataque base
            return this.ataque;
        }
    }

    public int computarDEF() {
        if(this.cartaMagicaEquipada != null) {
            // Si tiene una carta mágica aplicada, se suma el diferencial de defensa
            return this.defensa + this.cartaMagicaEquipada.getDiferencialDefensa();
        } else {
            // Si no tiene carta mágica aplicada, se retorna la defensa base
            return this.defensa;
        }
    }
    
    public void cambiarPosicion() { 
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
        return this.cartaMagicaEquipada != null;
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

    public Equipamiento getcartaMagicaEquipada() {
        return cartaMagicaEquipada;
    }

    public void equiparCartaMagica(Equipamiento cartaMagica) {
        this.cartaMagicaEquipada = cartaMagica;
        System.out.println(this.nombre + " ha sido equipado con la carta mágica: " + cartaMagica.getNombre());
    }

    public void desequiparCartaMagica() {
        this.cartaMagicaEquipada = null;
        System.out.println(this.nombre + " ha sido desequipado de la carta mágica.");
    }
    
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public TipoZona getTipoZona() { return TipoZona.MONSTRUO; }

    public String getTipo(){
        return "Monstruo";
    }
}