package modelo.tableroCartas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import yugioop.modelo.carta.trampas.Evento;
import yugioop.modelo.carta.trampas.RobarCartas;
import yugioop.modelo.jugador.Jugador;
import yugioop.modelo.tablero.ITableroJugador;
import yugioop.modelo.tablero.IZonaTablero;
import yugioop.modelo.tablero.Tablero;
import yugioop.modelo.turno.TurnoManager;
import yugioop.modelo.carta.Carta;
import yugioop.modelo.carta.CartaMonstruo;
import yugioop.modelo.carta.CartaTrampa;
import yugioop.modelo.carta.trampas.Contraataque;
import yugioop.modelo.carta.trampas.Descartar;
import yugioop.modelo.carta.trampas.RecuperarVida;
import yugioop.modelo.jugador.Mazo;
import yugioop.modelo.jugador.Cementerio;
import yugioop.modelo.tablero.TableroJugador;
import yugioop.modelo.jugador.Mano;

public class TrampaTest {
    // private void procesarEventoParaTest(Tablero tablero, Evento evento) {
    //     Jugador oponente = tablero.getTurnoManager().getJugadorOponente();
    //     ITableroJugador tableroOponente = tablero.obtenerTablero(oponente);
    //     IZonaTablero<Carta> zona = tableroOponente.getZonaMagiaTrampa();

    //     List<CartaTrampa> trampas = new ArrayList<>();
    //     for (Carta carta : zona.obtenerOcupantes()) {
    //         if (carta instanceof CartaTrampa) {
    //             trampas.add((CartaTrampa) carta);
    //         }
    //     }

    //     for (CartaTrampa trampa : trampas) {
    //         if (trampa.debeActivarse(evento, tablero)) {
    //             trampa.revelar();
    //             trampa.activar(tablero);
    //             int pos = zona.obtenerOcupantes().indexOf(trampa);   
    //             zona.removerCartaDeSlot(pos);
    //             Jugador duenio = tablero.getTurnoManager().getJugadorOponente();
    //             duenio.enviarAlCementerio(trampa);
    //         }
    //     }
    // }
    
    // @Test
    // void testCartaTrampaRobarCartas() {
    //     System.out.println("\n===============================");
    //     System.out.println("Test: Robar cartas al activarse");
    //     System.out.println("===============================\n");

    //     Mazo mazo1 = new Mazo();
    //     Cementerio cementerio1 = new Cementerio();
    //     TableroJugador tablero1 = new TableroJugador();
    //     Mano mano1 = new Mano();
    //     Jugador jugador1 = new Jugador("Jugador 1", mazo1, cementerio1, tablero1); 
        
        
        
    //     Mazo mazo2 = new Mazo();
    //     Cementerio cementerio2 = new Cementerio();
    //     TableroJugador tableroJugador2 = new TableroJugador();
    //     Mano mano2 = new Mano();
    //     Jugador jugador2 = new Jugador("Jugador 2", mazo2, cementerio2, tableroJugador2); 
    
        
    //     Tablero tablero = new Tablero(jugador1, jugador2);

    //     TurnoManager turnoManager = new TurnoManager(jugador1, jugador2, true);
    //     tablero.setTurnoManager(turnoManager);

    //     for (int i = 0; i < 5; i++) {
    //         CartaMonstruo cartaDummy = new CartaMonstruo("Carta de Prueba " + i);
    //         jugador2.getMazo().sumarCartas(cartaDummy);
    //     }

    //     assertEquals(5, jugador2.getMazo().getCantCartasEnMazo(), "El mazo debería tener 5 cartas antes de la prueba");


    //     RobarCartas trampa = new RobarCartas(3);

    //     tablero.obtenerTablero(jugador2).colocarMagiaTrampa(trampa, 0);

    //     assertEquals(0, jugador2.getMano().getCantCartasEnMano());

    //     procesarEventoParaTest(tablero, Evento.JUGAR_MAGIA);

    //     assertEquals(3, jugador2.getMano().getCantCartasEnMano(),
    //             "El jugador 2 debería tener 3 cartas en su mano después de activar la trampa");
    //     assertTrue(jugador2.getCementerio().getCartasEnCementerio().contains(trampa),
    //             "La trampa debería estar en el cementerio después de activarse");
    //     assertFalse(trampa.isBocaAbajo(),
    //             "La trampa no debería estar boca abajo después de activarse");
    //     assertEquals(2, jugador2.getMazo().getCantCartasEnMazo(),
    //             "El mazo debería tener 2 cartas después de robar 3");
    // }

    // @Test
    // void testTrampaDescartarSeActivaYDescartaCartas() {
    //     System.out.println("\n===============================");
    //     System.out.println("Test: Trampa Descartar se activa y descarta cartas");
    //     System.out.println("===============================\n");

    //     Mazo mazo1 = new Mazo();
    //     Cementerio cementerio1 = new Cementerio();
    //     TableroJugador tableroJugador1 = new TableroJugador();
    //     Mano mano1 = new Mano();
    //     Jugador jugador1 = new Jugador("Jugador 1", mazo1, cementerio1, tableroJugador1); // El que ataca
        
    //     Mazo mazo2 = new Mazo();
    //     Cementerio cementerio2 = new Cementerio();
    //     TableroJugador tableroJugador2 = new TableroJugador();
    //     Mano mano2 = new Mano();
    //     Jugador jugador2 = new Jugador("Jugador 2", mazo2, cementerio2, tableroJugador2); // El dueño de la trampa
    //     Tablero tablero = new Tablero(jugador1, jugador2);
    //     TurnoManager turnoManager = new TurnoManager(jugador1, jugador2, true);
    //     tablero.setTurnoManager(turnoManager);

    //     for (int i = 0; i < 5; i++) {
    //         CartaMonstruo carta = new CartaMonstruo("Monstruo " + i, 1000, 1000);
    //         jugador1.setMazo(carta);
    //     }
    //     jugador1.robarCartas(5);
    //     assertEquals(5, jugador1.getMano().getCantCartasEnMano(), "El jugador 1 debe tener 5 cartas en la mano antes del ataque");

    //     Descartar trampa = new Descartar(2); // Debe descartar 2 cartas
    //     tablero.obtenerTablero(jugador2).colocarMagiaTrampa(trampa, 0);

    //     procesarEventoParaTest(tablero, Evento.ATACAR);

    //     assertEquals(3, jugador1.getMano().getCantCartasEnMano(), "El jugador 1 debe tener 3 cartas en la mano después de activar la trampa");
    //     assertTrue(jugador2.getCementerio().getCartasEnCementerio().contains(trampa), "La trampa debe estar en el cementerio después de activarse");
    //     assertFalse(trampa.isBocaAbajo(), "La trampa no debe estar boca abajo después de activarse");
    // }

    // @Test
    // void testTrampaRecuperarVidaSeActivaYGanaVida() {
    //     System.out.println("\n===============================");
    //     System.out.println("Test: Trampa Recuperar Vida se activa y recupera vida");
    //     System.out.println("===============================\n");

    //     Mazo mazo1 = new Mazo();
    //     Cementerio cementerio1 = new Cementerio();
    //     TableroJugador tableroJugador1 = new TableroJugador();
    //     Mano mano1 = new Mano();
    //     Jugador jugador1 = new Jugador("Jugador 1", mazo1, cementerio1, tableroJugador1); // El que juega el hechizo
        
    //     Mazo mazo2 = new Mazo();
    //     Cementerio cementerio2 = new Cementerio();
    //     TableroJugador tableroJugador2 = new TableroJugador();
    //     Mano mano2 = new Mano();
    //     Jugador jugador2 = new Jugador("Jugador 2", mazo2, cementerio2, tableroJugador2); // El dueño de la trampa
    //     Tablero tablero = new Tablero(jugador1, jugador2);
    //     TurnoManager turnoManager = new TurnoManager(jugador1, jugador2, true);
    //     tablero.setTurnoManager(turnoManager);

    //     int vidaInicial = jugador2.getPuntosDeVida();

    //     RecuperarVida trampa = new RecuperarVida(500); // Gana 500 LP
    //     tablero.obtenerTablero(jugador2).colocarMagiaTrampa(trampa, 0);

    //     procesarEventoParaTest(tablero, Evento.JUGAR_MAGIA);

    //     assertEquals(vidaInicial + 500, jugador2.getPuntosDeVida(), "El jugador 2 debe haber recuperado 500 puntos de vida");
    //     assertTrue(jugador2.getCementerio().getCartasEnCementerio().contains(trampa), "La trampa debe estar en el cementerio después de activarse");
    //     assertFalse(trampa.isBocaAbajo(), "La trampa no debe estar boca abajo después de activarse");
    // }

    // @Test
    // void testTrampaContraataqueSeActivaYDaniaAlAtacante() {
    //     System.out.println("\n===============================");
    //     System.out.println("Test: Trampa Contraataque se activa y dania al atacante");
    //     System.out.println("===============================\n");
        
    //     Mazo mazo1 = new Mazo();
    //     Cementerio cementerio1 = new Cementerio();
    //     TableroJugador tableroJugador1 = new TableroJugador();
    //     Mano mano1 = new Mano();
    //     Jugador jugador1 = new Jugador("Jugador 1", mazo1, cementerio1, tableroJugador1); // El que ataca
        
    //     Mazo mazo2 = new Mazo();
    //     Cementerio cementerio2 = new Cementerio();
    //     TableroJugador tableroJugador2 = new TableroJugador();
    //     Mano mano2 = new Mano();
    //     Jugador jugador2 = new Jugador("Jugador 2", mazo2, cementerio2, tableroJugador2); // El dueño de la trampa
    //     Tablero tablero = new Tablero(jugador1, jugador2);
    //     TurnoManager turnoManager = new TurnoManager(jugador1, jugador2, true);
    //     tablero.setTurnoManager(turnoManager);

    //     int vidaInicial = jugador1.getPuntosDeVida();

    //     Contraataque trampa = new Contraataque(500); // Dania 500 LP
    //     tablero.obtenerTablero(jugador2).colocarMagiaTrampa(trampa, 0);

    //     procesarEventoParaTest(tablero, Evento.COLOCAR_CARTA);

    //     assertEquals(vidaInicial - 500, jugador1.getPuntosDeVida(), "El jugador 1 debe haber perdido 500 puntos de vida");
    //     assertTrue(jugador2.getCementerio().getCartasEnCementerio().contains(trampa), "La trampa debe estar en el cementerio después de activarse");
    //     assertFalse(trampa.isBocaAbajo(), "La trampa no debe estar boca abajo después de activarse");
    // }
}