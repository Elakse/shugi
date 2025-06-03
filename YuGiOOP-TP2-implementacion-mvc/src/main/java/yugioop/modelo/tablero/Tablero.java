package yugioop.modelo.tablero;

import yugioop.modelo.carta.CartaMagica;
import yugioop.modelo.carta.CartaMonstruo;
import yugioop.modelo.carta.CartaTrampa;
import yugioop.modelo.jugador.Jugador;
import java.util.List;

/**
 * Representa el tablero completo del juego, que contiene los tableros de ambos jugadores
 * y la casilla de campo compartida.
 */
public class Tablero {
    private ITableroJugador tableroJugador1;
    private Jugador propietarioJugador1;
    private ITableroJugador tableroJugador2;
    private Jugador propietarioJugador2;

    private CasillaCampo casillaCampoCompartida;
    private Jugador propietarioCartaCampoActual;

    // Gestor de efectos para manejo de mágicas y trampas
    private yugioop.modelo.efectos.GestorDeEfectos gestorDeEfectos;

    public Tablero(Jugador jugador1, Jugador jugador2) {
        this.propietarioJugador1 = jugador1;
        this.tableroJugador1 = new TableroJugador();
        this.propietarioJugador2 = jugador2;
        this.tableroJugador2 = new TableroJugador();

        this.casillaCampoCompartida = new CasillaCampo();
        this.propietarioCartaCampoActual = null;

    }

    /**
     * Obtiene el tablero correspondiente al jugador especificado.
     * @param jugador El jugador cuyo tablero se desea obtener.
     * @return El ITableroJugador correspondiente al jugador, o null si el jugador no es reconocido.
     */
    public ITableroJugador obtenerTablero(Jugador jugador) {
        if (jugador == this.propietarioJugador1) {
            return tableroJugador1;
        } else if (jugador == this.propietarioJugador2) {
            return tableroJugador2;
        }
        System.out.println("Error: Jugador no reconocido en este tablero: " + (jugador != null ? jugador.getNombre() : "null"));
        return null;
    }

    
    /**
     * Establece una nueva carta como la carta de campo activa.
     * @param cartaCampo La nueva carta de campo
     */
    public void setCartaCampo(CartaMagica cartaCampo) {
        if (cartaCampo != null && cartaCampo.esDeTipoCampo()) {
            this.casillaCampoCompartida.colocarCarta(cartaCampo);
        }
    }
    
    /**
     * Verifica si un tablero de jugador tiene monstruos en su zona de monstruos.
     * @param jugador El jugador a verificar
     * @return true si hay al menos un monstruo en el campo, false en caso contrario
     */
    public boolean tieneMonstruos(Jugador jugador) {
        ITableroJugador tableroJugador = obtenerTablero(jugador);
        if (tableroJugador == null) return false;
        
        for (int i = 0; i < tableroJugador.getZonaMonstruos().getCantidadSlotsLibres(); i++) {
            ICasillaTablero<CartaMonstruo> casilla = tableroJugador.getZonaMonstruos().getSlot(i);
            if (casilla != null && !casilla.estaLibre()) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Obtiene una lista de todos los monstruos activos en el campo de un jugador.
     * @param jugador El jugador cuyos monstruos se desean obtener
     * @return Lista de monstruos en el campo del jugador
     */
    public List<CartaMonstruo> getMonstruos(Jugador jugador) {
        ITableroJugador tableroJugador = obtenerTablero(jugador);
        List<CartaMonstruo> monstruos = new java.util.ArrayList<>();
        
        if (tableroJugador == null) return monstruos;
        
        for (int i = 0; i < tableroJugador.getZonaMonstruos().getCantidadSlotsLibres(); i++) {
            ICasillaTablero<CartaMonstruo> casilla = tableroJugador.getZonaMonstruos().getSlot(i);
            if (casilla != null && !casilla.estaLibre()) {
                monstruos.add(casilla.getOcupante());
            }
        }
        
        return monstruos;
    }
    
    
    
    /**
     * Calcular cuántos sacrificios son necesarios para invocar un monstruo según su nivel.
     * @param nivel El nivel del monstruo
     * @return El número de sacrificios necesarios (0, 1 o 2)
     */
    public int calcularSacrificiosNecesarios(int nivel) {
        if (nivel >= 5 && nivel <= 6) {
            return 1;
        } else if (nivel >= 7) {
            return 2;
        }
        return 0; // Niveles 1-4 no requieren sacrificios
    }
    
    // Variable para controlar la cantidad de invocaciones normales por turno
    private java.util.Map<Jugador, Boolean> invocacionNormalRealizada = new java.util.HashMap<>();
    
    /**
     * Reinicia el contador de invocaciones normales para un nuevo turno
     * @param jugador El jugador que inicia su turno
     */
    public void iniciarTurno(Jugador jugador) {
        invocacionNormalRealizada.put(jugador, false);
    }
    
    /**
     * TODO: REVISAR
     * Invoca un monstruo en el campo en posición de ataque o defensa siguiendo las reglas del juego.
     * Si el monstruo es de nivel 5-6, requiere 1 sacrificio.
     * Si el monstruo es de nivel 7 o mayor, requiere 2 sacrificios.
     * Los monstruos de nivel 1-4 no requieren sacrificios.
     * Sólo se permite una invocación normal por turno.
     * Los monstruos sacrificados son enviados al cementerio.
     * 
     * @param jugadorInvocador El jugador que invoca al monstruo
     * @param monstruo El monstruo a invocar
     * @param posicion La posición en la zona de monstruos (0-4)
     * @param modoDefensa true si el monstruo se invoca en defensa, false para modo ataque
     * @param posicionesSacrificio Arreglo con las posiciones de los monstruos a sacrificar (puede ser null para monstruos nivel 1-4)
     * @return true si la invocación fue exitosa, false en caso contrario
     */
    public boolean invocarMonstruo(Jugador jugadorInvocador, CartaMonstruo monstruo, int posicion, boolean modoDefensa, int[] posicionesSacrificio) {
        // Validación de parámetros
        if (jugadorInvocador == null || monstruo == null) {
            System.out.println("Error: Jugador o monstruo inválido.");
            return false;
        }
        
        // Verificar si ya realizó una invocación normal este turno
        Boolean yaInvoco = invocacionNormalRealizada.get(jugadorInvocador);
        if (yaInvoco != null && yaInvoco) {
            System.out.println("Error: Ya realizaste una invocación normal en este turno.");
            return false;
        }
        
        ITableroJugador tableroJugador = obtenerTablero(jugadorInvocador);
        if (tableroJugador == null) {
            System.out.println("Error: No se encontró el tablero del jugador " + jugadorInvocador.getNombre());
            return false;
        }
        
        // Verificar si la posición es válida y está libre
        if (posicion < 0 || posicion >= tableroJugador.getZonaMonstruos().getCantidadSlotsLibres()) {
            System.out.println("Error: Posición inválida para invocar el monstruo.");
            return false;
        }
        
        if (!tableroJugador.getZonaMonstruos().getSlot(posicion).estaLibre()) {
            System.out.println("Error: La posición " + posicion + " ya está ocupada por otro monstruo.");
            return false;
        }
        
        // Calcular sacrificios necesarios según el nivel del monstruo
        int sacrificiosNecesarios = calcularSacrificiosNecesarios(monstruo.getNivel());
        
        // Verificar y procesar sacrificios si son necesarios
        if (sacrificiosNecesarios > 0) {
            // Verificar que se proporcionaron las posiciones de sacrificio
            if (posicionesSacrificio == null || posicionesSacrificio.length < sacrificiosNecesarios) {
                System.out.println("Error: Se requieren " + sacrificiosNecesarios + 
                                  " sacrificios para invocar a " + monstruo.getNombre() + 
                                  " pero no se proporcionaron suficientes posiciones.");
                return false;
            }
            
            // Verificar que las posiciones son válidas y contienen monstruos
            for (int i = 0; i < sacrificiosNecesarios; i++) {
                int pos = posicionesSacrificio[i];
                if (pos < 0 || pos >= tableroJugador.getZonaMonstruos().getCantidadSlotsLibres()) {
                    System.out.println("Error: Posición de sacrificio inválida: " + pos);
                    return false;
                }
                
                if (tableroJugador.getZonaMonstruos().getSlot(pos).estaLibre()) {
                    System.out.println("Error: No hay monstruo para sacrificar en la posición " + pos);
                    return false;
                }
            }
            
            // Realizar los sacrificios (remover los monstruos y enviarlos al cementerio)
            // Nota: Removemos desde la posición más alta a la más baja para evitar problemas con los índices
            java.util.Arrays.sort(posicionesSacrificio);
            for (int i = sacrificiosNecesarios - 1; i >= 0; i--) {
                CartaMonstruo sacrificio = tableroJugador.removerMonstruo(posicionesSacrificio[i]);
                if (sacrificio != null) {
                    System.out.println("Sacrificando a " + sacrificio.getNombre() + " para invocar a " + monstruo.getNombre());
                    jugadorInvocador.enviarAlCementerio(sacrificio); // Usamos el método existente
                }
            }
        }
        
        // Realizar la invocación del monstruo
        boolean resultado = tableroJugador.colocarMonstruo(monstruo, posicion);
        if (resultado) {
            // Establecer el modo del monstruo (ataque o defensa)
            monstruo.setModoAtaque(!modoDefensa);
            // Marcar que ya se realizó la invocación normal en este turno
            invocacionNormalRealizada.put(jugadorInvocador, true);
            
            // Revelar la carta (si se invoca en modo ataque siempre se revela)
            if (!modoDefensa) {
                monstruo.revelar();
            }
            
            System.out.println("Monstruo " + monstruo.getNombre() + " nivel " + monstruo.getNivel() + 
                             " invocado exitosamente en modo " + (modoDefensa ? "defensa" : "ataque") + 
                             (sacrificiosNecesarios > 0 ? " mediante " + sacrificiosNecesarios + " sacrificios." : "."));
        } else {
            System.out.println("Error: No se pudo invocar al monstruo. La operación falló.");
        }
        
        return resultado;
    }
    
    /**
     * TODO: REVISAR
     * Sobrecarga del método invocarMonstruo para monstruos que no requieren sacrificios
     * o cuando el sistema decide automáticamente los sacrificios.
     */
    public boolean invocarMonstruo(Jugador jugadorInvocador, CartaMonstruo monstruo, int posicion, boolean modoDefensa) {
        // Para monstruos que no requieren sacrificios
        int sacrificiosNecesarios = calcularSacrificiosNecesarios(monstruo.getNivel());
        if (sacrificiosNecesarios == 0) {
            return invocarMonstruo(jugadorInvocador, monstruo, posicion, modoDefensa, null);
        }
        
        // Para monstruos que requieren sacrificios, seleccionamos automáticamente
        ITableroJugador tableroJugador = obtenerTablero(jugadorInvocador);
        if (tableroJugador == null) return false;
        
        List<CartaMonstruo> monstruosEnCampo = tableroJugador.obtenerMonstruosEnCampo();
        if (monstruosEnCampo.size() < sacrificiosNecesarios) {
            System.out.println("Error: Se requieren " + sacrificiosNecesarios + 
                               " sacrificios para invocar a " + monstruo.getNombre() + 
                               " pero solo hay " + monstruosEnCampo.size() + " monstruos en el campo.");
            return false;
        }
        
        // Encontrar las posiciones de los monstruos a sacrificar
        int[] posicionesSacrificio = new int[sacrificiosNecesarios];
        int sacrificiosEncontrados = 0;
        
        // Primero intentamos sacrificar los monstruos de menor nivel/ataque
        java.util.List<java.util.Map.Entry<Integer, CartaMonstruo>> monstruosConPosicion = new java.util.ArrayList<>();
        
        for (int i = 0; i < tableroJugador.getZonaMonstruos().getCantidadSlotsLibres(); i++) {
            ICasillaTablero<CartaMonstruo> casilla = tableroJugador.getZonaMonstruos().getSlot(i);
            if (!casilla.estaLibre()) {
                monstruosConPosicion.add(new java.util.AbstractMap.SimpleEntry<>(i, casilla.getOcupante()));
            }
        }
        
        // Ordenar por nivel y luego por ataque
        java.util.Collections.sort(monstruosConPosicion, (a, b) -> {
            int compNivel = Integer.compare(a.getValue().getNivel(), b.getValue().getNivel());
            if (compNivel != 0) return compNivel;
            return Integer.compare(a.getValue().getAtaque(), b.getValue().getAtaque());
        });
        
        // Seleccionar los primeros N monstruos para sacrificar
        for (int i = 0; i < sacrificiosNecesarios && i < monstruosConPosicion.size(); i++) {
            posicionesSacrificio[i] = monstruosConPosicion.get(i).getKey();
            sacrificiosEncontrados++;
        }
        
        if (sacrificiosEncontrados < sacrificiosNecesarios) {
            System.out.println("Error: No se encontraron suficientes monstruos para sacrificar.");
            return false;
        }
        
        // Realizar la invocación con los sacrificios seleccionados
        return invocarMonstruo(jugadorInvocador, monstruo, posicion, modoDefensa, posicionesSacrificio);
    }

    /**
     * Coloca una carta mágica en la zona de magia/trampa del jugador especificado.
     * Este método centraliza la responsabilidad de colocación en el Tablero en lugar
     * de delegarla a la carta.
     * 
     * @param jugador El jugador que coloca la carta
     * @param cartaMagica La carta mágica a colocar
     * @param posicion La posición en la zona de magia/trampa (0-4)
     * @return true si la colocación fue exitosa, false en caso contrario
     */
    public boolean colocarMagica(Jugador jugador, CartaMagica cartaMagica, int posicion) {
        ITableroJugador tableroJugador = obtenerTablero(jugador);
        if (tableroJugador == null) {
            System.out.println("Error: No se encontró el tablero del jugador " + jugador.getNombre());
            return false;
        }
        
        if (tableroJugador.hayEspacioEnZonaMagiaTrampa() && 
            tableroJugador.getZonaMagiaTrampa().getSlot(posicion).estaLibre()) {
            return tableroJugador.colocarMagiaTrampa(cartaMagica, posicion);
        } else {
            System.out.println("Error: No hay espacio en la zona de magia/trampa del jugador " + jugador.getNombre());
            return false;
        }
    }
    
    /**
     * Coloca una carta trampa en la zona de magia/trampa del jugador especificado.
     * Este método centraliza la responsabilidad de colocación en el Tablero en lugar
     * de delegarla a la carta.
     * 
     * @param jugador El jugador que coloca la carta
     * @param cartaTrampa La carta trampa a colocar
     * @param posicion La posición en la zona de magia/trampa (0-4)
     * @return true si la colocación fue exitosa, false en caso contrario
     */
    public boolean colocarTrampa(Jugador jugador, CartaTrampa cartaTrampa, int posicion) {
        ITableroJugador tableroJugador = obtenerTablero(jugador);
        if (tableroJugador == null) {
            System.out.println("Error: No se encontró el tablero del jugador " + jugador.getNombre());
            return false;
        }
        
        if (tableroJugador.hayEspacioEnZonaMagiaTrampa()) {
            return tableroJugador.colocarMagiaTrampa(cartaTrampa, posicion);
        } else {
            System.out.println("Error: No hay espacio en la zona de magia/trampa del jugador " + jugador.getNombre());
            return false;
        }
    }
   
}