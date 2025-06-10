package modelo.tableroCartas;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;

import yugioop.modelo.batalla.SistemaBatalla;
import yugioop.modelo.carta.CartaMonstruo;
// import yugioop.modelo.carta.TipoMonstruo;
import yugioop.modelo.jugador.Jugador;
import yugioop.modelo.tablero.Tablero;

class MonstruoTest {
    private Jugador marco;
    private Jugador polo;
    private Tablero tablero;
    
    @BeforeEach
    void setUp() {
        marco = new Jugador("Marco");
        polo = new Jugador("Polo");
        tablero = new Tablero(marco, polo);
        // Iniciar turno para ambos jugadores para permitir invocaciones
        // tablero.iniciarTurno(marco);
        // tablero.iniciarTurno(polo);
    }
    @Test
    void testAtaqueATKvsATK_ganaAtacante() {
        System.out.println("\n===============================");
        System.out.println("Test: Ataque ATK vs ATK - Gana Atacante");
        System.out.println("===============================\n");
        // CartaMonstruo dragon = new CartaMonstruo("Dragón Blanco", 3000, 2500, TipoMonstruo.DRAGON);
        // CartaMonstruo mago = new CartaMonstruo("Mago Oscuro", 2500, 2100, TipoMonstruo.HECHICERO);
        // tablero.invocarMonstruo(marco, dragon, 0, false);
        // tablero.invocarMonstruo(polo, mago, 0, false);
        
        // SistemaBatalla sistemaBatalla = new SistemaBatalla(tablero);
        // sistemaBatalla.ejecutarAtaque(marco, 0, polo, 0);
        
        // assertEquals(7500, polo.getPuntosDeVida());
        // assertEquals(8000, marco.getPuntosDeVida());
        // assertEquals(1, polo.getCementerio().size());
        // assertEquals(0, marco.getCementerio().size());
    }

    @Test
    void testAtaqueATKvsATK_ganaDefensor() {
        System.out.println("\n===============================");
        System.out.println("Test: Ataque ATK vs ATK - Gana Defensor");
        System.out.println("===============================\n");
        // CartaMonstruo dragon = new CartaMonstruo("Dragón Blanco", 2500, 2500, TipoMonstruo.DRAGON);
        // CartaMonstruo mago = new CartaMonstruo("Mago Oscuro", 3000, 2100, TipoMonstruo.HECHICERO);
        // tablero.invocarMonstruo(marco, dragon, 0, false);
        // tablero.invocarMonstruo(polo, mago, 0, false);
        // SistemaBatalla sistemaBatalla = new SistemaBatalla(tablero);
        // sistemaBatalla.ejecutarAtaque(marco, 0, polo, 0);
        // assertEquals(8000, polo.getPuntosDeVida());
        // assertEquals(7500, marco.getPuntosDeVida());
        // assertEquals(0, polo.getCementerio().size());
        // assertEquals(1, marco.getCementerio().size());
    }

    @Test
    void testAtaqueATKvsATK_igual() {
        System.out.println("\n===============================");
        System.out.println("Test: Ataque ATK vs ATK - Igual");
        System.out.println("===============================\n");
        // CartaMonstruo dragon = new CartaMonstruo("Dragón Blanco", 2500, 2500, TipoMonstruo.DRAGON);
        // CartaMonstruo mago = new CartaMonstruo("Mago Oscuro", 2500, 2100, TipoMonstruo.HECHICERO);
        // tablero.invocarMonstruo(marco, dragon, 0, false);
        // tablero.invocarMonstruo(polo, mago, 0, false);
        // SistemaBatalla sistemaBatalla = new SistemaBatalla(tablero);
        // sistemaBatalla.ejecutarAtaque(marco, 0, polo, 0);
        // assertEquals(8000, marco.getPuntosDeVida());
        // assertEquals(8000, polo.getPuntosDeVida());
        // assertEquals(1, marco.getCementerio().size());
        // assertEquals(1, polo.getCementerio().size());
    }

    @Test
    void testAtaqueATKvsDEF_ganaAtacante() {
        System.out.println("\n===============================");
        System.out.println("Test: Ataque ATK vs DEF - Gana Atacante");
        System.out.println("===============================\n");
        // CartaMonstruo dragon = new CartaMonstruo("Dragón Blanco", 3000, 2500, TipoMonstruo.DRAGON);
        // CartaMonstruo mago = new CartaMonstruo("Mago Oscuro", 2000, 3100, TipoMonstruo.HECHICERO);
    //     tablero.invocarMonstruo(marco, dragon, 0, false);
    //     tablero.invocarMonstruo(polo, mago, 0, true); 
    //     SistemaBatalla sistemaBatalla = new SistemaBatalla(tablero);
    //     sistemaBatalla.ejecutarAtaque(marco, 0, polo, 0);
    //     assertEquals(7900, marco.getPuntosDeVida()); 
    //     assertEquals(8000, polo.getPuntosDeVida()); 
    //     assertEquals(0, marco.getCementerio().size()); 
    //     assertEquals(0, polo.getCementerio().size()); 
    }

    @Test
    void testAtaqueATKvsDEF_ganaDefensor() {
        System.out.println("\n===============================");
        System.out.println("Test: Ataque ATK vs DEF - Gana Defensor");
        System.out.println("===============================\n");
        // CartaMonstruo dragon = new CartaMonstruo("Dragón Blanco", 2000, 2500, TipoMonstruo.DRAGON);
        // CartaMonstruo mago = new CartaMonstruo("Mago Oscuro", 2000, 3100, TipoMonstruo.HECHICERO);
        // tablero.invocarMonstruo(marco, dragon, 0, false);
        // tablero.invocarMonstruo(polo, mago, 0, true); 
        // SistemaBatalla sistemaBatalla = new SistemaBatalla(tablero);
        // sistemaBatalla.ejecutarAtaque(marco, 0, polo, 0);
        // assertEquals(8000, polo.getPuntosDeVida()); 
        // assertEquals(6900, marco.getPuntosDeVida()); 
        // assertEquals(0, marco.getCementerio().size()); 
        // assertEquals(0, polo.getCementerio().size()); 
    }

    @Test
    void testAtaqueATKvsDEF_igual() {
        System.out.println("\n===============================");
        System.out.println("Test: Ataque ATK vs DEF - Igual");
        System.out.println("===============================\n");
        // CartaMonstruo dragon = new CartaMonstruo("Dragón Blanco", 3000, 2500, TipoMonstruo.DRAGON);
        // CartaMonstruo mago = new CartaMonstruo("Mago Oscuro", 2000, 3000, TipoMonstruo.HECHICERO);
        // tablero.invocarMonstruo(marco, dragon, 0, false);
        // tablero.invocarMonstruo(polo, mago, 0, true); // defensa
        // SistemaBatalla sistemaBatalla = new SistemaBatalla(tablero);
        // sistemaBatalla.ejecutarAtaque(marco, 0, polo, 0);
        // assertEquals(8000, polo.getPuntosDeVida());
        // assertEquals(8000, marco.getPuntosDeVida());
        // assertEquals(0, marco.getCementerio().size()); 
        // assertEquals(0, polo.getCementerio().size()); 
    }

    @Test
    void testAtaqueDirecto() {
        System.out.println("\n===============================");
        System.out.println("Test: Ataque directo");
        System.out.println("===============================\n");
        // CartaMonstruo dragon = new CartaMonstruo("Dragón Blanco", 3000, 2500, TipoMonstruo.DRAGON);
        // tablero.invocarMonstruo(marco, dragon, 0, false);
        // // Polo no tiene monstruos
        // SistemaBatalla sistemaBatalla = new SistemaBatalla(tablero);
        // sistemaBatalla.ejecutarAtaqueDirecto(marco, 0, polo);
        // assertEquals(5000, polo.getPuntosDeVida());
        // assertEquals(0, marco.getCementerio().size()); 
        // assertEquals(0, polo.getCementerio().size()); 
    }

    @Test
    void testMonstruoConCeroATKAtaca() {
        System.out.println("\n===============================");
        System.out.println("Test: Monstruo con ATK 0 ataca");
        System.out.println("===============================\n");
        // CartaMonstruo debil = new CartaMonstruo("Slime", 0, 1000, TipoMonstruo.AGUA);
        // CartaMonstruo fuerte = new CartaMonstruo("Guerrero", 1500, 1200, TipoMonstruo.GUERRERO);
        // tablero.invocarMonstruo(marco, debil, 0, false);
        // tablero.invocarMonstruo(polo, fuerte, 0, false);

        // SistemaBatalla sistemaBatalla = new SistemaBatalla(tablero);
        // // El atacante es "marco" en la posición 0, el defensor es "polo" en la posición 0
        // sistemaBatalla.ejecutarAtaque(marco, 0, polo, 0);

        // assertEquals(6500, marco.getPuntosDeVida()); // pierde 1500
        // assertEquals(8000, polo.getPuntosDeVida());
        // assertEquals(1, marco.getCementerio().size()); 
        // assertEquals(0, polo.getCementerio().size()); 
    }

    @Test
    void testMonstruoConCeroDEFEsAtacado() {
        System.out.println("\n===============================");
        System.out.println("Test: Monstruo con DEF 0 es atacado");
        System.out.println("===============================\n");
        // CartaMonstruo atacante = new CartaMonstruo("Guerrero", 1000, 1000, TipoMonstruo.GUERRERO);
        // CartaMonstruo debil = new CartaMonstruo("Slime", 300, 0, TipoMonstruo.AGUA);
        // tablero.invocarMonstruo(marco, atacante, 0, false);
        // tablero.invocarMonstruo(polo, debil, 0, true);
        // SistemaBatalla sistemaBatalla = new SistemaBatalla(tablero);
        // sistemaBatalla.ejecutarAtaque(marco, 0, polo, 0);
        // assertEquals(8000, marco.getPuntosDeVida());
        // assertEquals(8000, polo.getPuntosDeVida());
        // assertEquals(1, polo.getCementerio().size()); 
        // assertEquals(0, marco.getCementerio().size()); 
    }

    @Test
    void testMonstruosMismoNombreDiferentesStats() {
        System.out.println("\n===============================");
        System.out.println("Test: Monstruos con el mismo nombre, pero diferentes stats");
        System.out.println("===============================\n");
        // CartaMonstruo slime1 = new CartaMonstruo("Slime", 500, 500, TipoMonstruo.AGUA);
        // CartaMonstruo slime2 = new CartaMonstruo("Slime", 2000, 2000, TipoMonstruo.AGUA);
        // tablero.invocarMonstruo(marco, slime1, 0, false);
        // tablero.invocarMonstruo(polo, slime2, 0, false);
        // SistemaBatalla sistemaBatalla = new SistemaBatalla(tablero);
        // sistemaBatalla.ejecutarAtaque(marco, 0, polo, 0);
        // assertEquals(6500, marco.getPuntosDeVida()); 
        // assertEquals(8000, polo.getPuntosDeVida());
        // assertEquals(1, marco.getCementerio().size()); 
        // assertEquals(0, polo.getCementerio().size()); 
    }

    @Test
    void testMonstruoMismoATKyDEF() {
        System.out.println("\n===============================");
        System.out.println("Test: Monstruo con ATK 0 ataca");
        System.out.println("===============================\n");
        // CartaMonstruo Cosito = new CartaMonstruo("Cosito", 0, 100, TipoMonstruo.ROCA);
        // CartaMonstruo Coso = new CartaMonstruo("Coso", 1000, 1000, TipoMonstruo.ROCA);
        // tablero.invocarMonstruo(marco, Cosito, 0, false);
        // tablero.invocarMonstruo(polo, Coso, 0, true);
        // SistemaBatalla sistemaBatalla = new SistemaBatalla(tablero);
        // sistemaBatalla.ejecutarAtaque(marco, 0, polo, 0);
        // assertEquals(7000, marco.getPuntosDeVida());
        // assertEquals(8000, polo.getPuntosDeVida());
        // assertEquals(0, marco.getCementerio().size()); 
        // assertEquals(0, polo.getCementerio().size()); 
    }

    @Test
    void testAtaqueDirectoConCeroATK() {
        System.out.println("\n===============================");
        System.out.println("Test: Monstruo con ATK 0 ataca directamente");
        System.out.println("===============================\n");
        // CartaMonstruo debil = new CartaMonstruo("Slime", 0, 1000, TipoMonstruo.AGUA);
        // tablero.invocarMonstruo(marco, debil, 0, false);
        // SistemaBatalla sistemaBatalla = new SistemaBatalla(tablero);
        // sistemaBatalla.ejecutarAtaque(marco, 0, polo, 0);
        // assertEquals(8000, polo.getPuntosDeVida()); // No pierde vida porque ATK es 0
        // assertEquals(0, marco.getCementerio().size()); 
        // assertEquals(0, polo.getCementerio().size()); 
    }
}