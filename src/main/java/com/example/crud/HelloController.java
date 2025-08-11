package com.example.crud;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class HelloController {
    @FXML private TableView<Tarea> tableTareas; // Tabla para mostrar las tareas
    @FXML private TextField txtTitulo; // Campo de texto para el título
    @FXML private TextArea txtDescripcion; // Área de texto para la descripción
    @FXML private ComboBox<String> comboPrioridad; // ComboBox para seleccionar la prioridad
    @FXML private DatePicker datePicker; // Selector de fecha para la fecha límite
    @FXML private Button btnAgregar; // Botón para agregar una tarea
    @FXML private Button btnEditar; // Botón para editar una tarea
    @FXML private Button btnEliminar; // Botón para eliminar una tarea
    @FXML private TableColumn<Tarea, Integer> colId;
    @FXML private TableColumn<Tarea, String> colTitulo;
    @FXML private TableColumn<Tarea, String> colDescripcion;
    @FXML private TableColumn<Tarea, Integer> colPrioridad;
    @FXML private TableColumn<Tarea, String> colFechaLimite;
    private Tareas tareaRepository = new Tareas(); // Instancia del repositorio de tareas
    private ObservableList<Tarea> observableTareas; // Lista observable para la tabla
    // Método que se ejecuta al inicializar el controlador
    @FXML
    public void initialize() {

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        colPrioridad.setCellValueFactory(new PropertyValueFactory<>("prioridad"));
        colFechaLimite.setCellValueFactory(new PropertyValueFactory<>("fechaLimite"));

        observableTareas = FXCollections.observableArrayList(tareaRepository.findAll()); // Carga las tareas en la lista observable
        tableTareas.setItems(observableTareas); // Asigna la lista observable a la tabla
        comboPrioridad.getItems().addAll("Alta", "Media", "Baja"); // Agrega opciones al ComboBox de prioridad
        // Listener para cargar la tarea seleccionada en los campos de entrada
        tableTareas.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                cargarTarea(newSelection); // Carga la tarea seleccionada en los campos
            }
        });

        // Asigna acciones a los botones
        btnAgregar.setOnAction(event -> agregarTarea());
        btnEditar.setOnAction(event -> editarTarea());
        btnEliminar.setOnAction(event -> eliminarTarea());



    }
    // Método para cargar los datos de una tarea en los campos de entrada
    private void cargarTarea(Tarea tarea) {
        txtTitulo.setText(tarea.getTitulo()); // Carga el título
        txtDescripcion.setText(tarea.getDescripcion()); // Carga la descripción
        comboPrioridad.setValue(tarea.getPrioridad()); // Carga la prioridad
        datePicker.setValue(tarea.getFechaLimite()); // Carga la fecha límite
    }
    // Método para agregar una nueva tarea
    private void agregarTarea() {
        // Crea una nueva tarea con los datos de los campos de entrada
        Tarea nuevaTarea = new Tarea(0, txtTitulo.getText(), txtDescripcion.getText(), comboPrioridad.getValue(), datePicker.getValue());
        tareaRepository.add(nuevaTarea); // Agrega la tarea al repositorio
        actualizarTabla(); // Actualiza la tabla para mostrar la nueva tarea
        limpiarCampos(); // Limpia los campos de entrada
    }
    // Método para editar una tarea existente
    private void editarTarea() {
        Tarea tareaSeleccionada = tableTareas.getSelectionModel().getSelectedItem(); // Obtiene la tarea seleccionada
        if (tareaSeleccionada != null) {
            // Actualiza los datos de la tarea seleccionada con los datos de los campos de entrada
            tareaSeleccionada.setTitulo(txtTitulo.getText());
            tareaSeleccionada.setDescripcion(txtDescripcion.getText());
            tareaSeleccionada.setPrioridad(comboPrioridad.getValue());
            tareaSeleccionada.setFechaLimite(datePicker.getValue());
            tareaRepository.update(tareaSeleccionada); // Actualiza la tarea en el repositorio
            actualizarTabla(); // Actualiza la tabla
            limpiarCampos(); // Limpia los campos de entrada
        }
    }
    // Método para eliminar una tarea
    private void eliminarTarea() {
        Tarea tareaSeleccionada = tableTareas.getSelectionModel().getSelectedItem(); // Obtiene la tarea seleccionada
        if (tareaSeleccionada != null) {
            tareaRepository.delete(tareaSeleccionada); // Elimina la tarea del repositorio
            actualizarTabla(); // Actualiza la tabla
            limpiarCampos(); // Limpia los campos de entrada
        }
    }
    // Método para limpiar los campos de entrada
    private void limpiarCampos() {
        txtTitulo.clear(); // Limpia el campo de título
        txtDescripcion.clear(); // Limpia el área de descripción
        comboPrioridad.setValue(null); // Limpia el ComboBox de prioridad
        datePicker.setValue(null); // Limpia el selector de fecha
    }
    // Método para actualizar la tabla con las tareas del repositorio
    private void actualizarTabla() {
        observableTareas.setAll(tareaRepository.findAll()); // Actualiza la lista observable con las tareas del repositorio
    }


}