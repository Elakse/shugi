package yugioop.modelo.carta.condicion;

public enum TipoEventoDeJuego {
    ATAQUE_OPONENTE,
    OPONENTE_JUEGA_MAGICA,
    OPONENTE_INVOCA_MONSTRUO,
    OPONENTE_COLOCA_CARTA,
    FIN_TURNO_OPONENTE,
    JUGADOR_RECIBE_ATAQUE_DIRECTO,
    OPONENTE_ACTIVA_HECHIZO // Para efectos como Barrera Espiritual
}