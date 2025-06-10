package yugioop.utils;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import yugioop.modelo.carta.Carta;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

public class CartaFactory {

    /**
     * Lee el archivo de cartas desde la ruta especificada y retorna una lista de
     * cartas (de cualquier tipo).
     * 
     * @param rutaArchivo Ruta al archivo JSON de cartas.
     */
    public static List<Carta> cargarCartas(String rutaArchivo) throws IOException {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Map<String, Object>>>() {
        }.getType();
        FileReader reader = new FileReader(rutaArchivo);
        List<Map<String, Object>> cartasRaw = gson.fromJson(reader, listType);
        reader.close();
        List<Carta> cartas = new ArrayList<>();

        for (Map<String, Object> cartaMap : cartasRaw) {
            // String tipo = ((String) cartaMap.get("tipo")).toUpperCase();
            // String nombre = (String) cartaMap.get("nombre");
        //     switch (tipo) {
        //         case "MONSTRUO":
        //             // Validación y parseo robusto de campos
        //             int atk = ((Number) cartaMap.get("ataque")).intValue();
        //             int def = ((Number) cartaMap.get("defensa")).intValue();
        //             int nivel = ((Number) cartaMap.get("nivel")).intValue();
        //             TipoMonstruo tipoMonstruo = TipoMonstruo.valueOf((String) cartaMap.get("tipoMonstruo"));
        //             String elemento = cartaMap.containsKey("elemento") ? (String) cartaMap.get("elemento") : null;
        //             // Crear la carta monstruo
        //             CartaMonstruo monstruo = new CartaMonstruo(nombre, atk, def, tipoMonstruo);
        //             monstruo.setNivel(nivel);
        //             monstruo.setElemento(elemento);
        //             // Si tiene efecto, setearlo (queda para lógica avanzada)
        //             if (cartaMap.containsKey("efecto")) {
        //                 @SuppressWarnings("unchecked")
        //                 Map<String, Object> efecto = (Map<String, Object>) cartaMap.get("efecto");
        //                 monstruo.setEfecto(efecto);
        //             }
        //             cartas.add(monstruo);
        //             break;
        //         case "MAGICA":
        //             CartaMagica magica;
        //             if (cartaMap.containsKey("efecto")) {
        //                 Map<String, Object> efecto = (Map<String, Object>) cartaMap.get("efecto");
        //                 magica = new CartaMagica(nombre, (tab, carta, jugador) -> {
        //                     // Aquí debes mapear el Map a la lógica real según tu sistema de efectos.
        //                     // Ejemplo: si efecto.get("tipo").equals("curar"), entonces
        //                     // jugador.ganarVida(...)
        //                 });
        //             } else {
        //                 magica = new CartaMagica(nombre, (tab, carta, jugador) -> {
        //                 });
        //             }
        //             cartas.add(magica);
        //             break;
        //         case "MAGICA_CAMPO":
        //             // Por ahora, puedes tratarlas igual que una carta mágica común
        //             CartaMagica magicaCampo;
        //             if (cartaMap.containsKey("efecto")) {
        //                 Map<String, Object> efecto = (Map<String, Object>) cartaMap.get("efecto");
        //                 magicaCampo = new CartaMagica(nombre, (tab, carta, jugador) -> {
        //                     // Aquí puedes mapear el efecto de campo si quieres lógica especial
        //                 });
        //             } else {
        //                 magicaCampo = new CartaMagica(nombre, (tab, carta, jugador) -> {
        //                 });
        //             }
        //             cartas.add(magicaCampo);
        //             break;
        //         case "TRAMPA":
        //             CartaTrampa trampa;
        //             if (cartaMap.containsKey("efecto")) {
        //                 Map<String, Object> efecto = (Map<String, Object>) cartaMap.get("efecto");
        //                 trampa = new CartaTrampa(nombre, (tab, atacante, defensor, jugador) -> {
        //                     // Mapear el efecto a la lógica real
        //                 });
        //             } else {
        //                 trampa = new CartaTrampa(nombre, (tab, atacante, defensor, jugador) -> {
        //                 });
        //             }
        //             cartas.add(trampa);
        //             break;
        //         default:
        //             System.out.println("Tipo de carta desconocido: " + tipo);
        //     }
        // }
        // return cartas;
        System.out.println("Método cargarCartas no implementado completamente. Por favor, revisa la lógica de carga de cartas.");
        }
        return cartas;
    }

    /**
     * Devuelve un mapa de nombre de carta a objeto Carta para acceso rápido.
     */
    public static Map<String, Carta> cargarCartasComoMapa(String rutaArchivo) throws IOException {
        List<Carta> lista = cargarCartas(rutaArchivo);
        Map<String, Carta> mapa = new HashMap<>();
        for (Carta c : lista) {
            mapa.put(c.getNombre(), c);
        }
        return mapa;
    }
}
