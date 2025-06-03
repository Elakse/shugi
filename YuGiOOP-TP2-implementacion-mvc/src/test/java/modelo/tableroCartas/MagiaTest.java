package modelo.tableroCartas;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import yugioop.modelo.tablero.Tablero;
import yugioop.modelo.carta.CartaMonstruo;
import yugioop.modelo.carta.CartaMagica;
import yugioop.modelo.carta.TipoMonstruo;
import yugioop.modelo.carta.TipoMagica;
import yugioop.modelo.efectos.magicos.RoboDeCartas;
import yugioop.modelo.efectos.magicos.CartaDeCampo;
import yugioop.modelo.efectos.magicos.Defensivo;
import yugioop.modelo.efectos.magicos.SegundaOportunidad;
import yugioop.modelo.jugador.Jugador;
import yugioop.modelo.turno.TurnoManager;

public class MagiaTest {
    private Jugador jugador1;
    private Jugador jugador2;
    private Tablero tablero;
    private TurnoManager turnoManager;
    
    @BeforeEach
    void setUp() {
        jugador1 = new Jugador("Jugador 1");
        jugador2 = new Jugador("Jugador 2");
        tablero = new Tablero(jugador1, jugador2);
        turnoManager = new TurnoManager(jugador1, jugador2, true);
        
        // Iniciar un turno para habilitar invocaciones
        turnoManager.iniciarTurno();
    }
    
    @Test
    void testCartaMagicaSegundaOportunidad() {
        System.out.println("\n===============================");
        System.out.println("Test: Segunda oportunidad, recupera carta del cementerio");
        System.out.println("===============================\n");
        
        // Colocar una carta en el cementerio
        CartaMonstruo monstruo = new CartaMonstruo("Dragón Blanco", 3000, 2500, TipoMonstruo.DRAGON);
        jugador1.enviarAlCementerio(monstruo);
        
        // Verificar que la carta está en el cementerio
        assertEquals(1, jugador1.getCementerio().size());
        assertTrue(jugador1.getCementerio().contains(monstruo));
        
        // Crear carta mágica de segunda oportunidad 
        // Usamos el índice 0 para recuperar la primera carta del cementerio
        CartaMagica.EfectoMagico efectoSegundaOportunidad = new SegundaOportunidad(0);
        CartaMagica segundaOportunidad = new CartaMagica("Monstruo Renacido", efectoSegundaOportunidad);
        
        // Verificar el tamaño inicial de la mano
        int tamanoManoInicial = jugador1.getMano().size();
        
        // Colocar y activar la carta mágica
        tablero.colocarMagica(jugador1, segundaOportunidad, 0);
        // verifico que se coloca la carta en el tablero
        System.out.println("Carta colocada en el tablero: " + segundaOportunidad.getNombre());
        
        // Activar la carta
        
        // Verificar que la carta se movió del cementerio a la mano
        assertEquals(1, jugador1.getCementerio().size()); // Solo queda la carta mágica usada
        assertFalse(jugador1.getCementerio().contains(monstruo));
        assertTrue(jugador1.getCementerio().contains(segundaOportunidad));
        
        // Verificar que la carta fue añadida a la mano del jugador
        assertEquals(tamanoManoInicial + 1, jugador1.getMano().size());
        assertTrue(jugador1.getMano().contains(monstruo));
    }   

    @Test
    void testCartaMagicaEquipamiento() {
        System.out.println("\n===============================");
        System.out.println("Test: Equipamiento, aumenta/disminuye estadísticas de un monstruo");
        System.out.println("===============================\n");
        
        // Invocar un monstruo
        CartaMonstruo monstruo = new CartaMonstruo("Guerrero", 1500, 1200, TipoMonstruo.GUERRERO);
        tablero.invocarMonstruo(jugador1, monstruo, 0, false);
        
        int ataqueOriginal = monstruo.getAtaque();
        int defensaOriginal = monstruo.getDefensa();
        
        // Crear carta mágica de equipamiento
        CartaMagica.EfectoMagico efectoEquipamiento = new yugioop.modelo.efectos.magicos.Equipamiento(0, false, 1000, 0, 2);
        CartaMagica equipamiento = new CartaMagica("Hacha de la Desesperación", TipoMagica.EQUIPO, efectoEquipamiento, 2); // Duración: 2 turnos
        
        // Colocar y activar la carta de equipamiento
        tablero.colocarMagica(jugador1, equipamiento, 0);
        // Activar la carta
        
        
        // Verificar que el ataque ha aumentado
        assertEquals(ataqueOriginal + 1000, monstruo.getAtaque());
        assertEquals(defensaOriginal, monstruo.getDefensa()); // La defensa no debe cambiar
    }         

    @Test
    void testCartaMagicaRoboDeCartas() {
        System.out.println("\n===============================");
        System.out.println("Test: Robo de cartas, permite robar cartas adicionales");
        System.out.println("===============================\n");
        
        // Contar cartas iniciales en mano y mazo
        int cartasEnManoInicial = jugador1.getMano().size();
        
        // Agregar algunas cartas al mazo para poder robar
        for (int i = 0; i < 5; i++) {
            jugador1.getMazo().agregarCarta(new CartaMonstruo("Monstruo " + i, 1000, 1000, TipoMonstruo.AGUA));
        }
        
        int cartasEnMazoInicial = jugador1.getMazo().cantidadCartas();
        
        // Crear carta mágica de robo
        CartaMagica.EfectoMagico efectoRoboCartas = new RoboDeCartas(2);
        CartaMagica roboCartas = new CartaMagica("Olla de la Codicia", efectoRoboCartas);
        
        // Colocar y activar la carta de robo
        tablero.colocarMagica(jugador1, roboCartas, 0);
        // Activar la carta
        
        
        // Verificar que la carta fue al cementerio después de ser usada
        assertTrue(jugador1.getCementerio().contains(roboCartas));
    }         

    @Test
    void testCartaMagicaDefensiva() {
        System.out.println("\n===============================");
        System.out.println("Test: Defensiva, impide ataques del oponente");
        System.out.println("===============================\n");
        
        // Invocar monstruos para ambos jugadores
        CartaMonstruo monstruoJugador1 = new CartaMonstruo("Guerrero", 1500, 1200, TipoMonstruo.GUERRERO);
        CartaMonstruo monstruoJugador2 = new CartaMonstruo("Dragón", 2000, 1700, TipoMonstruo.DRAGON);
        
        tablero.invocarMonstruo(jugador1, monstruoJugador1, 0, false);
        tablero.invocarMonstruo(jugador2, monstruoJugador2, 0, false);
        
        // Crear carta mágica defensiva
        CartaMagica.EfectoMagico efectoDefensivo = new Defensivo(0, 3);
        CartaMagica cartaDefensiva = new CartaMagica("Espadas de Luz Reveladora", TipoMagica.DEFENSIVA, efectoDefensivo, 3); // Duración: 3 turnos
        
        // Colocar y activar la carta defensiva
        tablero.colocarMagica(jugador1, cartaDefensiva, 0);
        // Activar la carta
        
        
        // Para este test, simplemente verificamos que la carta fue activada y enviada al cementerio
        assertTrue(jugador1.getCementerio().contains(cartaDefensiva));
        // Y verificamos que el slot donde estaba ahora está libre
        assertTrue(tablero.obtenerTablero(jugador1).getZonaMagiaTrampa().getSlot(0).estaLibre());
    }            

    @Test 
    void testCartaMagicaDeCampo() {
        System.out.println("\n===============================");
        System.out.println("Test: De campo, modifica condiciones globales del juego");
        System.out.println("===============================\n");
        
        // Invocar monstruos para ambos jugadores
        CartaMonstruo magoJugador1 = new CartaMonstruo("Mago Oscuro", 2500, 2100, TipoMonstruo.HECHICERO);
        CartaMonstruo hadaJugador2 = new CartaMonstruo("Hada", 1800, 1500, TipoMonstruo.HADA);
        
        tablero.invocarMonstruo(jugador1, magoJugador1, 0, false);
        tablero.invocarMonstruo(jugador2, hadaJugador2, 0, false);
        
        int ataqueOriginalMago = magoJugador1.getAtaque();
        int defensaOriginalMago = magoJugador1.getDefensa();
        int ataqueOriginalHada = hadaJugador2.getAtaque();
        int defensaOriginalHada = hadaJugador2.getDefensa();
        
        // Crear carta mágica de campo
        CartaMagica.EfectoMagico efectoCampo = new CartaDeCampo("Yami", 200, 200, -200, -200);
        CartaMagica cartaCampo = new CartaMagica("Yami", TipoMagica.CAMPO, efectoCampo);
        
        // Colocar y activar la carta de campo
        tablero.colocarMagica(jugador1, cartaCampo, 0);
        // Activar la carta
        
        
        // Verificar que los efectos se aplicaron correctamente
        assertEquals(ataqueOriginalMago + 200, magoJugador1.getAtaque()); // Mago gana ATK
        assertEquals(defensaOriginalMago + 200, magoJugador1.getDefensa()); // Mago gana DEF
        assertEquals(ataqueOriginalHada - 200, hadaJugador2.getAtaque()); // Hada pierde ATK
        assertEquals(defensaOriginalHada - 200, hadaJugador2.getDefensa()); // Hada pierde DEF
    }              
}
