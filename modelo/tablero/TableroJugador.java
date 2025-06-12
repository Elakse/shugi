package yugioop.modelo.tablero;

import yugioop.modelo.carta.Carta;
import yugioop.modelo.carta.CartaMonstruo;
import yugioop.modelo.carta.CartaMagica;
import yugioop.modelo.carta.CartaTrampa;
import java.util.List;

/**
 * Implementación de ITableroJugador que representa el tablero de un jugador.
 * Contiene zonas para monstruos y cartas mágicas/trampas.
 */
public class TableroJugador implements ITableroJugador {
    private ZonaMonstruos zonaMonstruos;
    private ZonaMagiaTrampa zonaMagiaTrampa;
    private List<CartaMagica> cartasMagicasActivas;

    public TableroJugador(Integer tamanioZona) {
        this.zonaMonstruos = new ZonaMonstruos(tamanioZona);
        this.zonaMagiaTrampa = new ZonaMagiaTrampa(tamanioZona);
    }

    
    @Override
    public void colocarCarta(boolean esMonstruo, Carta carta, Integer pos) {
        if (esMonstruo) {
            this.zonaMonstruos.colocarCartaEnSlot((CartaMonstruo) carta, pos);
        } else {
            this.zonaMagiaTrampa.colocarCartaEnSlot(carta, pos);
        }
    }

    @Override
    public List<CartaMagica> obtenerCartasMagicasActivas() {
        return this.cartasMagicasActivas;
    }

    @Override
    public List<CartaTrampa> obtenerCartasTrampa(){
        return zonaMagiaTrampa.obtenerCartasTrampa();
    }

    @Override
    public void agregarCartaMagicaActiva(CartaMagica cartaMagica){
        cartasMagicasActivas.add(cartaMagica);
    }

    @Override
    public void removerCartaMagicaActiva(CartaMagica cartaMagica){
        cartasMagicasActivas.remove(cartaMagica);
    }

    @Override
    public void cambiarModoMonstruo(Integer posicion) {
        this.zonaMonstruos.cambiarModoMonstruo(posicion);;
    }

    @Override
    public Integer obtenerPosicionMonstruo(CartaMonstruo monstruo) {
        return this.zonaMonstruos.obtenerPosicionDeCarta(monstruo);
    }

    @Override
    public CartaMonstruo obtenerCartaMonstruo(Integer pos){
        return this.zonaMonstruos.obtenerCarta(pos);
    }

    @Override
    public void inhabilitarCartaMonstruo(Integer pos){
        zonaMonstruos.obtenerCarta(pos).inhabilitar();
    }

    @Override
    public void habilitarCartaMonstruo(Integer pos){
        zonaMonstruos.obtenerCarta(pos).habilitar();
    }

    @Override
    public CartaMagica obtenerCartaMagica(Integer pos){
        return this.zonaMagiaTrampa.obtenerCartaMagica(pos);
    }

    @Override
    public CartaTrampa obtenerCartaTrampa(Integer pos){
        return this.zonaMagiaTrampa.obtenerCartaTrampa(pos);
    }

    @Override
    public CartaMonstruo obtenerCartasZonaMonstruo(){
        return this.zonaMonstruos.obtenerOcupantes();
    }

    @Override
    public Carta obtenerCartasZonaMagiaTrampa(){
        return this.zonaMagiaTrampa.obtenerOcupantes();
    }

    /**
     * Obtiene la zona de monstruos del tablero.
     * @return La zona de monstruos como IZonaTablero<CartaMonstruo>.
     */
    @Override
    public IZonaTablero<CartaMonstruo> getZonaMonstruos() { return zonaMonstruos; }
    
    /**
     * Obtiene la zona de magia y trampas del tablero.
     * @return La zona de magia y trampas como IZonaTablero<Carta>.
     */
    @Override
    public IZonaTablero<Carta> getZonaMagiaTrampa() { return zonaMagiaTrampa; }


    @Override
    public void removerMonstruoPorPosicion(Integer pos) {
        zonaMonstruos.removerCartaPorPosicion(pos);
    }

    @Override
    public void removerMagicaPorPosicion(Integer pos) {
        zonaMagiaTrampa.removerCartaPorPosicion(pos);
    }
    @Override
    public void removerTrampaPorPosicion(Integer pos) {
        zonaMagiaTrampa.removerCartaPorPosicion(pos);
    }

    @Override
    public void removerCartaMonstruo(CartaMonstruo monstruo){
        zonaMonstruos.removerCartaMonstruo(monstruo);
    }

    @Override
    public void removerCartaMagica(CartaMagica cartaMagica) {
        zonaMagiaTrampa.removerCartaMagica(cartaMagica);
    }

    @Override
    public void removerCartaTrampa(CartaTrampa cartaTrampa) {
        zonaMagiaTrampa.removerCartaTrampa(cartaTrampa);
    }

    @Override
    public Integer getCantMonstrosOcupantes() {
        return zonaMonstruos.getCantCartasOcupantes();
    }

    /**
     * Verifica si hay algún espacio libre en la Zona de Monstruos.
     * @return true si hay al menos un espacio libre, false si todos están ocupados.
     */
    @Override
    public boolean hayEspacioEnZonaMonstruos() {
        return zonaMonstruos.hayEspacioLibre();
    }

    /**
     * Verifica si hay algún espacio libre en la Zona de Magia y Trampas.
     * @return true si hay al menos un espacio libre, false si todos están ocupados.
     */
    @Override
    public boolean hayEspacioEnZonaMagiaTrampa() {
        return zonaMagiaTrampa.hayEspacioLibre();
    }
}