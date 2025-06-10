package yugioop.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import yugioop.modelo.carta.Carta;
import yugioop.modelo.carta.CartaMonstruo;
import yugioop.modelo.carta.trampas.*;
import yugioop.modelo.carta.magicas.*;

public class CargaDeCartas {
    public static List<CartaJson> cargarCartas(String ruta) {
        try {
            //  ObjectMapper para leer el archivo JSON
            // y mapearlo a una lista de CartaJson
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(new File(ruta), mapper.getTypeFactory().constructCollectionType(List.class, CartaJson.class));
        } catch (Exception e) {
            throw new RuntimeException("Error cargando cartas: " + e.getMessage(), e);
        }
    }


    // Convierte una lista de objetos CartaJson a una lista de cartas 
    public static List<Carta> convertirACartas(List<CartaJson> cartasJson) {
        List<Carta> cartas = new ArrayList<>();
        for (CartaJson cj : cartasJson) {
            if ("MONSTRUO".equalsIgnoreCase(cj.tipo)) {
                CartaMonstruo monstruo = new CartaMonstruo(
                    cj.nombre,
                    cj.ataque != null ? cj.ataque : 0,
                    cj.defensa != null ? cj.defensa : 0,
                    cj.nivel != null ? cj.nivel : 1
                    // agrega más campos si tu constructor lo requiere
                );
                cartas.add(monstruo);
            }else if ("TRAMPA".equalsIgnoreCase(cj.tipo)) {
                switch (cj.nombre) {
                    case "Contraataque":
                        cartas.add(new Contraataque(cj.nombre, cj.cantidad != null ? cj.cantidad : 1));
                        break;
                    case "RobarCartas":
                        cartas.add(new RobarCartas(cj.nombre, cj.cantidad != null ? cj.cantidad : 1));
                        break;
                    case "RecuperarVida":
                        cartas.add(new RecuperarVida(cj.nombre, cj.cantidad != null ? cj.cantidad : 1));
                        break;
                    case "Descartar":
                        cartas.add(new Descartar(cj.nombre, cj.cantidad != null ? cj.cantidad : 1));
                        break;
                    default:
                        throw new IllegalArgumentException("Trampa desconocida: " + cj.nombre);
                }
            }else if("MAGICA".equalsIgnoreCase(cj.tipo)){
                switch (cj.nombre){
                    case "Defensiva":
                        cartas.add(new Defensiva(
                            cj.nombre,
                            cj.turnos != null ? cj.turnos : 1,
                            cj.mensajePeticion != null ? cj.mensajePeticion : ""
                        ));
                        break;
                    case "Equipamiento":
                        cartas.add(new Equipamiento(
                            cj.nombre,
                            cj.turnos != null ? cj.turnos : 1,
                            cj.diferencialAtaque != null ? cj.diferencialAtaque : 0,
                            cj.diferencialDefensa != null ? cj.diferencialDefensa : 0,
                            cj.mensajePeticion != null ? cj.mensajePeticion : ""
                        ));
                        break;
                    case "RoboDeCartas":
                        cartas.add(new RoboDeCartas(
                            cj.nombre,
                            cj.cantidad != null ? cj.cantidad : 1
                        ));
                        break;
                    case "SegundaOportunidad":
                        cartas.add(new SegundaOportunidad(
                            cj.nombre,
                            cj.cantidad != null ? cj.cantidad : 1
                        ));
                        break;
                    default:
                        throw new IllegalArgumentException("Mágica desconocida: " + cj.nombre);
                }
            }
        }
        return cartas;
    }
}
