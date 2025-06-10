package yugioop.modelo.turno;

public enum FaseTurno {
    DRAW, 
    STANDBY, 
    PRINCIPAL1, 
    BATALLA,
    CAMBIO, 
    FINAL;

    public FaseTurno siguiente() {
        int siguiente = (this.ordinal() + 1) % FaseTurno.values().length;
        return FaseTurno.values()[siguiente];
    }
}