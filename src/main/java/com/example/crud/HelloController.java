package com.example.crud;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.util.Callback;

public class HelloController {
    @FXML
    private TableView<Tareas> tableTareas;
    @FXML
    private TableColumn<Tareas,String> colId;
    @FXML
    private TableColumn<Tareas,String> colTitulo;
    @FXML
    private TableColumn<Tareas,String> colDescripcion;
    @FXML
    private TableColumn<Tareas,String> colPrioridad;
    @FXML
    private TableColumn<Tareas,String> colFechaLimite;

    ComboBox<String> comboTareas = new ComboBox<>();

    @FXML
    public void initialize() {
        // Enlaza las columnas del TableView con las propiedades del modelo Pelicula
        colId.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getId()));
        colTitulo.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getTitulo()));
        colDescripcion.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getDescripcion()));
        colPrioridad.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getPrioridad()));
        colFechaLimite.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getFechalimite()));



        Callback<TableColumn<Tareas,String>, TableCell<Tareas, String>> construirColEliminar = (param) ->{
            final TableCell<Tareas,String> celda = new TableCell<Tareas,String>() {


                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        comboTareas.getItems().addAll("Opción 1", "Opción 2", "Opción 3");

                    }
                }
            };
            return celda;
        };
        comboTareas.setOnAction(e -> {
            String seleccion = comboTareas.getValue();
            switch (seleccion) {
                case "Opción 1":
                    editar();
                    break;
                case "Opción 2":
                    eliminar();
                    break;
                case "Opción 3":
                    modificar();
                    break;
            }
        });
    }

    @FXML
    private void crearTareas(){}

    @FXML
    private void editar() {}
    @FXML
    private void eliminar() {}
    @FXML
    private void modificar() {}

}