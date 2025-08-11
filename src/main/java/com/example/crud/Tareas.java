package com.example.crud;

import java.util.ArrayList;
import java.util.List;

public class Tareas {

    private List<Tarea> tareas = new ArrayList<>(); // Lista para almacenar las tareas
    private int nextId = 1; // Contador para asignar IDs únicos a las tareas
    // Método para obtener todas las tareas
    public List<Tarea> findAll() {
        return tareas; // Devuelve la lista de tareas
    }
    // Método para agregar una nueva tarea
    public void add(Tarea tarea) {
        tarea.setId(nextId++); // Asigna un ID único a la tarea y aumenta el contador
        tareas.add(tarea); // Agrega la tarea a la lista
    }
    // Método para actualizar una tarea existente
    public void update(Tarea tarea) {
        // Busca la tarea en la lista y la actualiza
        for (int i = 0; i < tareas.size(); i++) {
            if (tareas.get(i).getId() == tarea.getId()) {
                tareas.set(i, tarea); // Reemplaza la tarea existente con la nueva
                break; // Sale del bucle una vez que se encuentra la tarea
            }
        }
    }
    // Método para eliminar una tarea
    public void delete(Tarea tarea) {
        tareas.remove(tarea); // Elimina la tarea de la lista
    }
}
