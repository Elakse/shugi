package modelo.turno;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import yugioop.modelo.jugador.Jugador;
import yugioop.modelo.turno.FaseTurno;
import yugioop.modelo.turno.TurnoManager;

public class TurnosTest {
    
    private Jugador jugador1;
    private Jugador jugador2;
    private TurnoManager turnoManager;
    
    @BeforeEach
    public void setUp() {
        jugador1 = new Jugador("Jugador 1");
        jugador2 = new Jugador("Jugador 2");
    }
    
    @Test
    public void testInicializacionCorrecta() {
        System.out.println("\n===============================");
        System.out.println("Test: Inicializacion Correcta");
        System.out.println("===============================\n");
        
        turnoManager = new TurnoManager(jugador1, jugador2, true);
        
        assertEquals(jugador1, turnoManager.getJugadorActual());
        assertEquals(jugador2, turnoManager.getJugadorOponente());
        assertEquals(FaseTurno.DRAW, turnoManager.getFaseActual());
        assertEquals(1, turnoManager.getNumeroTurnoGlobal());
        assertFalse(turnoManager.seRealizoInvocacionNormal());
        assertFalse(turnoManager.seActivoCartaCampo());
        
        turnoManager = new TurnoManager(jugador1, jugador2, false);
        
        assertEquals(jugador2, turnoManager.getJugadorActual());
        assertEquals(jugador1, turnoManager.getJugadorOponente());
        assertEquals(FaseTurno.DRAW, turnoManager.getFaseActual());
    }
    
    @Test
    public void testAvanceDeFasesNormal() {
        System.out.println("\n===============================");
        System.out.println("Test: Avance de fases normal");
        System.out.println("===============================\n");
        
        turnoManager = new TurnoManager(jugador1, jugador2, true);
        
        turnoManager.finalizarTurnoActual(); // Avanza al turno 2
        turnoManager.finalizarTurnoActual(); // Avanza al turno 3, jugador1 de nuevo
        
        assertTrue(turnoManager.avanzarFase());
        assertEquals(FaseTurno.STANDBY, turnoManager.getFaseActual());
        
        assertTrue(turnoManager.avanzarFase());
        assertEquals(FaseTurno.PRINCIPAL1, turnoManager.getFaseActual());
        
        assertTrue(turnoManager.avanzarFase());
        assertEquals(FaseTurno.BATALLA, turnoManager.getFaseActual());
        
        assertTrue(turnoManager.avanzarFase());
        assertEquals(FaseTurno.CAMBIO, turnoManager.getFaseActual());
        
        assertTrue(turnoManager.avanzarFase());
        assertEquals(FaseTurno.FINAL, turnoManager.getFaseActual());
        
        assertTrue(turnoManager.avanzarFase());
        assertEquals(FaseTurno.DRAW, turnoManager.getFaseActual());
        assertEquals(jugador2, turnoManager.getJugadorActual());
    }
    
    @Test
    public void testPrimerTurnoSaltaBatalla() {
        System.out.println("\n===============================");
        System.out.println("Test: Primer turno salta batalla");
        System.out.println("===============================\n");
        
        turnoManager = new TurnoManager(jugador1, jugador2, true);
        
        turnoManager.avanzarFase(); // DRAW -> STANDBY
        turnoManager.avanzarFase(); // STANDBY -> PRINCIPAL1
        assertEquals(FaseTurno.PRINCIPAL1, turnoManager.getFaseActual());
        
        turnoManager.avanzarFase();
        assertEquals(FaseTurno.CAMBIO, turnoManager.getFaseActual());
    }
    
    @Test
    public void testJugadorEligeIrABatalla() {
        System.out.println("\n===============================");
        System.out.println("Test: Jugador elige ir a batalla");
        System.out.println("===============================\n");
        
        turnoManager = new TurnoManager(jugador1, jugador2, true);
        turnoManager.finalizarTurnoActual(); // Avanza al turno 2
        turnoManager.finalizarTurnoActual(); // Avanza al turno 3
        
        turnoManager.avanzarFase(); // DRAW -> STANDBY
        turnoManager.avanzarFase(); // STANDBY -> PRINCIPAL1
        
        assertTrue(turnoManager.puedeElegirBatalla());
        turnoManager.avanzarFase();
        assertEquals(FaseTurno.BATALLA, turnoManager.getFaseActual());
        
        assertTrue(turnoManager.puedeElegirBatalla());
        turnoManager.avanzarFase();
        assertEquals(FaseTurno.CAMBIO, turnoManager.getFaseActual());
    }
    
    @Test
    public void testJugadorEligeSaltarBatalla() {
        System.out.println("\n===============================");
        System.out.println("Test: Jugador elige saltar batalla");
        System.out.println("===============================\n");
        
        turnoManager = new TurnoManager(jugador1, jugador2, true);
        turnoManager.finalizarTurnoActual(); // Avanza al turno 2
        
        turnoManager.avanzarFase(); // DRAW -> STANDBY
        turnoManager.avanzarFase(); // STANDBY -> PRINCIPAL1
        
        turnoManager.saltarBatalla();
        assertEquals(FaseTurno.CAMBIO, turnoManager.getFaseActual());
    }
    
    @Test
    public void testPrimerTurnoJugadorInicialRestriccion() {
        System.out.println("\n===============================");
        System.out.println("Test: Primer turno jugador inicial restriccion");
        System.out.println("===============================\n");
        
        turnoManager = new TurnoManager(jugador1, jugador2, true);
        
        turnoManager.avanzarFase(); // DRAW -> STANDBY
        turnoManager.avanzarFase(); // STANDBY -> PRINCIPAL1
        
        assertFalse(turnoManager.puedeElegirBatalla());
        turnoManager.avanzarFase();
        assertEquals(FaseTurno.CAMBIO, turnoManager.getFaseActual());
    }
    
    @Test
    public void testResetFlagsAlCambiarTurno() {
        System.out.println("\n===============================");
        System.out.println("Test: Reset flags al cambiar turno");
        System.out.println("===============================\n");
        
        turnoManager = new TurnoManager(jugador1, jugador2, true);
        
        turnoManager.registrarInvocacionNormal();
        turnoManager.registrarActivacionCartaCampo();
        
        assertTrue(turnoManager.seRealizoInvocacionNormal());
        assertTrue(turnoManager.seActivoCartaCampo());
        
        turnoManager.finalizarTurnoActual();
        
        assertFalse(turnoManager.seRealizoInvocacionNormal());
        assertFalse(turnoManager.seActivoCartaCampo());
    }
    
    @Test
    public void testCicloCompleto() {
        System.out.println("\n===============================");
        System.out.println("Test: Ciclo completo");
        System.out.println("===============================\n");
        
        turnoManager = new TurnoManager(jugador1, jugador2, true);
        
        assertEquals(FaseTurno.DRAW, turnoManager.getFaseActual());
        
        turnoManager.avanzarFase(); // DRAW -> STANDBY 
        assertEquals(FaseTurno.STANDBY, turnoManager.getFaseActual());
        
        turnoManager.avanzarFase(); // STANDBY -> PRINCIPAL1
        assertEquals(FaseTurno.PRINCIPAL1, turnoManager.getFaseActual());
        
        turnoManager.avanzarFase(); // PRINCIPAL1 -> CAMBIO (primer turno, sin BATALLA)
        assertEquals(FaseTurno.CAMBIO, turnoManager.getFaseActual());
        
        turnoManager.avanzarFase(); // CAMBIO -> FINAL
        assertEquals(FaseTurno.FINAL, turnoManager.getFaseActual());
        
        turnoManager.avanzarFase(); // FINAL -> DRAW (próximo jugador)
        assertEquals(FaseTurno.DRAW, turnoManager.getFaseActual());
        assertEquals(jugador2, turnoManager.getJugadorActual());
        
        // Avanzamos hasta PRINCIPAL1 
        turnoManager.avanzarFase(); // DRAW -> STANDBY
        turnoManager.avanzarFase(); // STANDBY -> PRINCIPAL1
        
        // Ahora sí debería poder ir a BATALLA (ya no es el primer turno)
        assertTrue(turnoManager.puedeElegirBatalla());
        
        turnoManager.avanzarFase(); // PRINCIPAL1 -> BATALLA
        assertEquals(FaseTurno.BATALLA, turnoManager.getFaseActual());
        
        turnoManager.avanzarFase(); // BATALLA -> CAMBIO
        assertEquals(FaseTurno.CAMBIO, turnoManager.getFaseActual());
    }

    @Test
    public void testAccionesPorFase() {
        System.out.println("\n===============================");
        System.out.println("Test: Acciones por fase");
        System.out.println("===============================\n");
        
        turnoManager = new TurnoManager(jugador1, jugador2, true);
        
        turnoManager.avanzarFase(); // DRAW -> STANDBY
        turnoManager.avanzarFase(); // STANDBY -> PRINCIPAL1
        assertEquals(FaseTurno.PRINCIPAL1, turnoManager.getFaseActual());
        
        assertTrue(turnoManager.puedeInvocarMonstruo());
        assertTrue(turnoManager.puedeActivarCartaMagicaOTrampa());
        assertTrue(turnoManager.puedeColocarCartas());
        
        turnoManager.avanzarFase(); // PRINCIPAL1 -> CAMBIO
        assertEquals(FaseTurno.CAMBIO, turnoManager.getFaseActual());
        
        assertTrue(turnoManager.puedeAlternarPosiciones());
        assertFalse(turnoManager.puedeInvocarMonstruo());
        assertFalse(turnoManager.puedeActivarCartaMagicaOTrampa());
    }
}