package com.example.crud;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Tareas {

    private final StringProperty id;
    private final StringProperty titulo;
    private final StringProperty descripcion;
    private final StringProperty prioridad;
    private final StringProperty fechalimite;

    public Tareas(String id, String titulo, String descipcion, String prioridad, String fechalimite) {

        this.id = new SimpleStringProperty(id);
        this.titulo = new SimpleStringProperty(titulo);
        this.descripcion = new SimpleStringProperty(descipcion);
        this.prioridad = new SimpleStringProperty(prioridad);
        this.fechalimite = new SimpleStringProperty(fechalimite);

    }

    public String getId() { return id.get(); }

    public String getTitulo() {
        return titulo.get();
    }

    public String getDescripcion() { return descripcion.get(); }

    public String getPrioridad() { return prioridad.get(); }

    public String getFechalimite() { return fechalimite.get(); }

}
