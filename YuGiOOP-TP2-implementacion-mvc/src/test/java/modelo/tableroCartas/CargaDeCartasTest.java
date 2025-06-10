package modelo.tableroCartas;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

import yugioop.modelo.carta.CartaMonstruo;
import yugioop.modelo.carta.magicas.Defensiva;
import yugioop.modelo.carta.magicas.Equipamiento;
import yugioop.modelo.carta.magicas.RoboDeCartas;
import yugioop.modelo.carta.magicas.SegundaOportunidad;
import yugioop.modelo.carta.trampas.Contraataque;
import yugioop.modelo.carta.trampas.Descartar;
import yugioop.modelo.carta.trampas.RecuperarVida;
import yugioop.modelo.carta.trampas.RobarCartas;
import yugioop.utils.CargaDeCartas;
import yugioop.utils.CartaJson;

class CargaDeCartasTest {
    @Test
    void testCargaYConversion() {
        // List<CartaJson> cartasJson = CargaDeCartas.cargarCartas("cartas.json");
        // assertFalse(cartasJson.isEmpty());

        // List<Object> cartasjs = CargaDeCartas.convertirACartas(cartasJson)
        // assertEquals(cartasJson.size(), cartasjs.size());

        // System.out.println("Cartas cargadas y convertidas correctamente:");
        // for (Object carta : cartasjs) {
        //     if (carta instanceof CartaMonstruo) {
        //         CartaMonstruo m = (CartaMonstruo) carta;
        //         System.out.println("MONSTRUO: " + m.getNombre() + " | ATK: " + m.getAtaque() + " | DEF: " + m.getDefensa());
        //     } else if (carta instanceof Defensiva) {
        //         Defensiva d = (Defensiva) carta;
        //         System.out.println("MAGICA DEFENSIVA: " + d.getNombre());
        //     } else if (carta instanceof Equipamiento) {
        //         Equipamiento e = (Equipamiento) carta;
        //         System.out.println("MAGICA EQUIPAMIENTO: " + e.getNombre());
        //     } else if (carta instanceof RoboDeCartas) {
        //         RoboDeCartas r = (RoboDeCartas) carta;
        //         System.out.println("MAGICA ROBO DE CARTAS: " + r.getNombre());
        //     } else if (carta instanceof SegundaOportunidad) {
        //         SegundaOportunidad s = (SegundaOportunidad) carta;
        //         System.out.println("MAGICA SEGUNDA OPORTUNIDAD: " + s.getNombre());
        //     } else if (carta instanceof Contraataque) {
        //         Contraataque c = (Contraataque) carta;
        //         System.out.println("TRAMPA CONTRAATAQUE: " + c.getNombre());
        //     } else if (carta instanceof Descartar) {
        //         Descartar d = (Descartar) carta;
        //         System.out.println("TRAMPA DESCARTAR: " + d.getNombre());
        //     } else if (carta instanceof RecuperarVida) {
        //         RecuperarVida r = (RecuperarVida) carta;
        //         System.out.println("TRAMPA RECUPERAR VIDA: " + r.getNombre());
        //     } else if (carta instanceof RobarCartas) {
        //         RobarCartas r = (RobarCartas) carta;
        //         System.out.println("TRAMPA ROBAR CARTAS: " + r.getNombre());
        //     } else {
        //         System.out.println("Carta desconocida: " + carta);
        //     }
        // }
    }
}