package yugioop.modelo.carta;

import java.util.UUID;

public abstract class Carta {
    protected UUID id;
    protected String nombre;
    protected String descripcion;
    protected boolean bocaAbajo;
    protected int turnoColocacion;

    public Carta(String nombre, String descripcion) {
        this.id = UUID.randomUUID();
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.bocaAbajo = true;
        this.turnoColocacion = -1;
    }

    public Carta(String nombre) { // Constructor original
        this(nombre, "Sin descripción.");
    }

    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public UUID getId() { return id; }

    public abstract void revelar();

    public boolean isBocaAbajo() { return bocaAbajo; }
    public void setBocaAbajo(boolean bocaAbajo) { this.bocaAbajo = bocaAbajo; }

    public int getTurnoColocacion() { return turnoColocacion; }
    public void setTurnoColocacion(int turno) { this.turnoColocacion = turno; }
}