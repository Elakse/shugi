package yugioop.modelo.carta;

import yugioop.modelo.jugador.ContextoJugador;
import yugioop.modelo.mesa.MesaYugioh;

public class CartaMonstruo extends Carta {
    private Integer ataque;
    private Integer ataqueActual;
    private Integer defensa;
    private Integer defensaActual;
    private boolean modoAtaque;
    private Integer nivel;
    boolean inhabilitada;

    public CartaMonstruo(String nombre, Integer ataque, Integer defensa, Integer nivel) {
        super(nombre, true);
        this.ataque = ataque;
        this.ataqueActual = ataque;
        this.defensa = defensa;
        this.defensaActual = defensa;
        this.modoAtaque = true;
        this.nivel = nivel;
        this.inhabilitada = false; // Por defecto no está inhabilitada
    }

    public Integer sacrificiosNecesarios() {
        if (this.nivel <= 4) {
            return 0;
        } else if (this.nivel <= 6) {
            return 1;
        } else {
            return 2;
        }
    }

    @Override
    public boolean esMonstruo(){
        return esMonstruo;
    }

    @Override
    public boolean esActivableADiscrecion(){
        return false;
    }

    public void activar(MesaYugioh mesa, Integer posObjetivo) {
        if (estaInhabilitada()) {
            throw new IllegalStateException("El monstruo está inhabilitado.");
        }

        ContextoJugador actual = mesa.obtenerContextoJugadorActual();
        ContextoJugador oponente = mesa.obtenerContextoJugadorOponente();
        CartaMonstruo objetivo = oponente.obtenerCartaMonstruo(posObjetivo);

        if (objetivo.estaEnModoAtaque()) {
            resolverModoAtaque(mesa, actual, oponente, objetivo);
        } else {
            resolverModoDefensa(mesa, actual, oponente, objetivo);
        }
    }

    private void resolverModoAtaque(MesaYugioh mesa, ContextoJugador actual, ContextoJugador oponente, CartaMonstruo objetivo) {
        Integer danio = computarAtaqueContraModoAtaque(objetivo);

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
        Integer danio = computarAtaqueContraModoDefensa(objetivo);

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

    public void incrementarAtkActual(Integer dif){
        cambiarAtaqueActual(ataque + dif);
    }

    public void reducirAtkActual(Integer dif){
        cambiarAtaqueActual(ataque - dif);
    }

    public void incrementarDefActual(Integer dif){
        cambiarDefensaActual(defensa + dif);
    }

    public void reducirDefActual(Integer dif){
        cambiarDefensaActual(defensa - dif);
    }

    public void cambiarAtaqueActual(Integer atk){
        this.ataqueActual = atk;
    }

    public void cambiarDefensaActual(Integer def){
        this.defensaActual = def;
    }

    public Integer computarAtaqueContraModoAtaque(CartaMonstruo monstruoAtacado) {
        Integer atacanteATK = this.ataqueActual;
        Integer atacadoATK = monstruoAtacado.ataqueActual;
        Integer danio = 0;
        
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
    
    public Integer computarAtaqueContraModoDefensa(CartaMonstruo monstruoAtacado) {
        Integer atacanteATK = this.ataqueActual;
        Integer atacadoDEF = monstruoAtacado.defensaActual;
        Integer danio = 0;
        
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

    public Integer getAtaque() { 
        return this.ataque;
    }
    
    public Integer getDefensa() { 
        return this.defensa; 
    }
    
    public boolean estaEnModoAtaque() {
        return this.modoAtaque;
    }

    public boolean tienecartaMagicaEquipada() {
        return (this.ataqueActual != this.ataque || this.defensaActual != this.defensa);
    }
    
    public void setAtaque(Integer ataque) {
        this.ataque = ataque;
    }
    
    public void setDefensa(Integer defensa) {
        this.defensa = defensa;
    }
    
    public Integer getNivel() {
        return this.nivel;
    }
    
    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

}