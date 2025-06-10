package yugioop.modelo.carta;

// Asumiendo que Jugador y Tablero serán importados donde se usen los efectos.
// import yugioop.modelo.jugador.Jugador;
// import yugioop.modelo.tablero.Tablero;

public class CartaMonstruo extends Carta {
    private int ataqueBase;
    private int defensaBase;
    private int ataqueActual;
    private int defensaActual;
    private boolean modoAtaque;
    private int nivel;
    private Elemento elemento;
    private Atributo atributo;
    private boolean haAtacadoEsteTurno;
    private boolean puedeCambiarPosicion; // Si ya cambió posición este turno.
    private boolean puedeAtacar; // Si puede atacar este turno (ej. no si cambió a defensa)

    public CartaMonstruo(String nombre, String descripcion, int ataque, int defensa, int nivel, Elemento elemento, Atributo atributo) {
        super(nombre, descripcion);
        this.ataqueBase = ataque;
        this.defensaBase = defensa;
        this.ataqueActual = ataque;
        this.defensaActual = defensa;
        this.modoAtaque = true; // Por defecto en modo ataque
        this.nivel = nivel;
        this.elemento = elemento;
        this.atributo = atributo;
        this.bocaAbajo = true; 
        this.haAtacadoEsteTurno = false;
        this.puedeCambiarPosicion = true;
        this.puedeAtacar = true;
    }
    
    // Adaptación de constructores originales
    public CartaMonstruo(String nombre, int ataque, int defensa, int nivel) {
        this(nombre, "Monstruo.", ataque, defensa, nivel, Elemento.LUZ, Atributo.GUERRERO); // Defaults
    }
    public CartaMonstruo(String nombre) {
        this(nombre, "Monstruo.",1000, 1000, 4, Elemento.LUZ, Atributo.GUERRERO); // Defaults
    }


    @Override
    public void revelar() {
        if (this.isBocaAbajo()) {
            // System.out.println("Monstruo " + nombre + " revelado. ATK: " + ataqueActual + " DEF: " + defensaActual);
            this.setBocaAbajo(false);
        }
    }

    public void cambiarModo() {
        if (!this.puedeCambiarPosicion) {
            // System.out.println(this.nombre + " no puede cambiar de posición este turno.");
            return;
        }
        this.modoAtaque = !this.modoAtaque;
        if (this.isBocaAbajo()) { // Si estaba boca abajo y cambia de modo, se revela
            this.revelar();
        }
        this.puedeCambiarPosicion = false;
        this.puedeAtacar = this.modoAtaque; // Si cambia a defensa, no puede atacar este turno.
        // System.out.println(this.nombre + " cambia a modo " + (this.modoAtaque ? "Ataque" : "Defensa") + ".");
    }

    public int getAtaque() { return this.ataqueActual; }
    public int getDefensa() { return this.defensaActual; }
    public boolean estaEnModoAtaque() { return this.modoAtaque; }
    public int getNivel() { return this.nivel; }
    public Elemento getElemento() { return elemento; }
    public Atributo getAtributo() { return atributo; }
    public boolean haAtacadoEsteTurno() { return haAtacadoEsteTurno; }
    public boolean puedeCambiarPosicionEsteTurno() { return puedeCambiarPosicion; }
    public boolean puedeAtacarEsteTurno() { return this.modoAtaque && this.puedeAtacar && !this.haAtacadoEsteTurno; }


    public void setAtaque(int nuevoAtaque) { this.ataqueActual = Math.max(0, nuevoAtaque); }
    public void setDefensa(int nuevaDefensa) { this.defensaActual = Math.max(0, nuevaDefensa); }
    public void modificarAtaque(int cantidad) { 
        this.ataqueActual = Math.max(0, this.ataqueActual + cantidad);
        // System.out.println(this.nombre + " ATK ahora es " + this.ataqueActual);
    }
    public void modificarDefensa(int cantidad) { 
        this.defensaActual = Math.max(0, this.defensaActual + cantidad);
        // System.out.println(this.nombre + " DEF ahora es " + this.defensaActual);
    }
    
    public void setModoAtaque(boolean modoAtaque) { this.modoAtaque = modoAtaque; }
    public void setHaAtacadoEsteTurno(boolean haAtacado) { 
        this.haAtacadoEsteTurno = haAtacado;
        if (haAtacado) this.puedeCambiarPosicion = false; // Si ataca, no puede cambiar pos en el mismo turno
    }
    public void setPuedeCambiarPosicion(boolean puede) { this.puedeCambiarPosicion = puede; }
    public void setPuedeAtacar(boolean puede) { this.puedeAtacar = puede; }


    public void resetearFlagsDeTurno() {
        this.haAtacadoEsteTurno = false;
        this.puedeCambiarPosicion = true;
        this.puedeAtacar = true; 
    }

    // Los métodos del código original como atacar(CartaMonstruo), sacrificar(), enviarAlCementerio()
    // son acciones que el Jugador/Tablero/SistemaBatalla realizan *sobre* la carta o *usando* la carta.
    // La carta en sí misma no "ataca" a otra en términos de código directo aquí, sino que provee sus stats.
}