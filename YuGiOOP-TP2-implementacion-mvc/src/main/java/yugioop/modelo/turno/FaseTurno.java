package yugioop.modelo.turno;

public enum FaseTurno {
    DRAW, // El jugador roba una carta de su mazo.
    STANDBY, // Se resuelven efectos que ocurren al inicio del turno.
    PRINCIPAL1, // El jugador puede invocar monstruos, activar cartas y efectos, y realizar ataques.
    BATALLA, // El jugador puede atacar al oponente y sus monstruos.
    CAMBIO, // Se pueden alternar las cartas en el campo de juego entre ataque y defensa.
    FINAL; // Finaliza el turno y se resuelven los efectos que ocurren al final del turno.

    /**
     * Devuelve la siguiente fase del turno.
     * Si es la última fase (FINAL), regresa a la primera (DRAW).
     * @return La siguiente fase del turno.
     */
    public FaseTurno siguiente() {
        int siguiente = (this.ordinal() + 1) % FaseTurno.values().length;
        return FaseTurno.values()[siguiente];
    }
}