package modelo.tableroCartas;

import org.junit.jupiter.api.Test;

public class TrampaTest {
    @Test
    void testCartaTrampaRobarCartas() {
        System.out.println("\n===============================");
        System.out.println("Test: Robar cartas al activarse");
        System.out.println("===============================\n");
    }          

    @Test
    void testCartaTrampaDescartar() {
        System.out.println("\n===============================");
        System.out.println("Test: Descartar cartas al activarse");
        System.out.println("===============================\n");
    }            

    @Test
    void testCartaTrampaAumentarDefensa() {
        System.out.println("\n===============================");
        System.out.println("Test: Aumentar DEF de monstruos");
        System.out.println("===============================\n");
    }      

    @Test
    void testCartaTrampaReducirAtaque() {
        System.out.println("\n===============================");
        System.out.println("Test: Reducir ATK del monstruo atacante");
        System.out.println("===============================\n");
    }        

    @Test
    void testCartaTrampaContraataque() {
        System.out.println("\n===============================");
        System.out.println("Test: Causa daño al atacante");
        System.out.println("===============================\n");
    }         

    @Test
    void testCartaTrampaRecuperarVida() {
        System.out.println("\n===============================");
        System.out.println("Test: Recupera puntos de vida");
        System.out.println("===============================\n");
    }        

    @Test
    void testCartaTrampaDestruirCarta() {
        System.out.println("\n===============================");
        System.out.println("Test: Destruye cartas específicas");
        System.out.println("===============================\n");
    }        

    @Test
    void testCartaTrampaCambiarPosicion() {
        System.out.println("\n===============================");
        System.out.println("Test: Cambia posición de monstruos");
        System.out.println("===============================\n");
    }
}