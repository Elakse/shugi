package yugioop.modelo.carta;

public class CartaMonstruo extends Carta {
       private int ataque;
    private int defensa;
    private boolean modoAtaque;
    private int nivel;
    
    public CartaMonstruo(String nombre) {
        super(nombre);
        this.ataque = 1000; // Ejemplo
        this.defensa = 1000; // Ejemplo
        this.modoAtaque = true; // Por defecto en modo ataque
        this.nivel = 4; // La mayoría de los monstruos básicos son nivel 4
    }
    
    public CartaMonstruo(String nombre, int ataque, int defensa) {
        super(nombre);
        this.ataque = ataque;
        this.defensa = defensa;
        this.modoAtaque = true;
        this.nivel = 4;
    }
    
    @Override 
    public void revelar() { 
        // Lógica para revelar la carta monstruo
        System.out.println("Monstruo " + nombre + " revelado."); 
    }
    
    public void atacar(CartaMonstruo target) { 
        // Lógica para atacar a otro monstruo
        System.out.println(this.nombre + " ataca a " + target.getNombre()); 
    }
    
    public void cambiarPosicion() { 
        // Lógica para cambiar la posición del monstruo
        System.out.println(this.nombre + " cambia a modo " + 
                         (this.modoAtaque ? "ataque" : "defensa") + "."); 
    }
    
    public void activar() { 
        // Lógica para activar efectos de la carta monstruo
        System.out.println(this.nombre + " activado."); 
    }
    
    public void sacrificar() { 
        // Lógica para sacrificar la carta monstruo
        System.out.println(this.nombre + " sacrificado."); 
    }
    
    public void enviarAlCementerio() { 
        // Lógica para enviar la carta al cementerio
        System.out.println(this.nombre + " enviado al cementerio."); 
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
}
