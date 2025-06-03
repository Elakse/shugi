package modelo.tablero;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import yugioop.modelo.carta.Carta;
import yugioop.modelo.carta.CartaFactory;
import yugioop.modelo.carta.CartaMonstruo;
import yugioop.modelo.carta.TipoMonstruo;
import yugioop.modelo.carta.CartaMagica;
import yugioop.modelo.carta.CartaTrampa;


public class CartaIntegracionTest {

    /**
     * Test de integración: carga todas las cartas monstruo desde el JSON y verifica sus atributos básicos.
     */
    @Test
    void testCargaCartasMonstruoDesdeJson() throws IOException {
        List<Carta> cartas = CartaFactory.cargarCartas("cartas.json");
        // Filtrar solo monstruos
        List<CartaMonstruo> monstruos = cartas.stream()
                .filter(c -> c instanceof CartaMonstruo)
                .map(c -> (CartaMonstruo) c)
                .toList();

        // Verificar que se hayan cargado cartas
        assertTrue(!cartas.isEmpty());
        // Ver todas las cartas
        System.out.println("Todas las cartas cargadas:======================");
        cartas.forEach(c -> System.out.println(c.getNombre()));

        // Ver todas las cartas monstruo
        System.out.println("\nCartas monstruo cargadas:======================");
        monstruos.forEach(c -> System.out.println(c.getNombre()));

        // Ver todas las cartas mágicas
        System.out.println("\nCartas mágicas cargadas:======================");
        cartas.stream()
                .filter(c -> c instanceof CartaMagica)
                .map(c -> (CartaMagica) c)
                .forEach(c -> System.out.println(c.getNombre()));

        // Ver todas las cartas trampa
        System.out.println("\nCartas trampa cargadas:======================");
        cartas.stream()
                .filter(c -> c instanceof CartaTrampa)
                .map(c -> (CartaTrampa) c)
                .forEach(c -> System.out.println(c.getNombre()));


        // Verifica que se hayan cargado varios monstruos conocidos
        assertTrue(monstruos.stream().anyMatch(m -> m.getNombre().contains("Dragón Blanco")));
        assertTrue(monstruos.stream().anyMatch(m -> m.getNombre().contains("Mago Oscuro")));
        assertTrue(monstruos.stream().anyMatch(m -> m.getNombre().contains("Kuriboh")));

        // Verifica los atributos de "Dragón Blanco de Ojos Azules"
        CartaMonstruo dragon = monstruos.stream().filter(m -> m.getNombre().contains("Dragón Blanco")).findFirst().orElse(null);
        assertNotNull(dragon);
        assertEquals(3000, dragon.getAtaque());
        assertEquals(2500, dragon.getDefensa());
        assertEquals(8, dragon.getNivel());
        assertEquals(TipoMonstruo.DRAGON, dragon.getTipoMonstruo());
        assertEquals("LUZ", dragon.getElemento());

        // Verifica los atributos de "Kuriboh"
        CartaMonstruo kuriboh = monstruos.stream().filter(m -> m.getNombre().contains("Kuriboh")).findFirst().orElse(null);
        assertNotNull(kuriboh);
        assertEquals(1, kuriboh.getNivel());
        assertEquals(300, kuriboh.getAtaque());
        assertEquals(200, kuriboh.getDefensa());
        assertEquals("OSCURIDAD", kuriboh.getElemento());
    }
}
