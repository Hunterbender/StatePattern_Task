package com.example._06_fassbender_state_task;

import com.example._06_fassbender_state_task.dal.DatabaseManager;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class PopupController implements Initializable {
    public TextField inputKennung;
    public TextField inputBeschreibung;
    public Button btnBestaetigen;
    public DatePicker inputAbgabedatum;
    private Stage stage;

    private Task task;

    public Task getTask() {
        return this.task;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeBtns();
    }

    private void initializeBtns() {
        this.btnBestaetigen.setOnAction(actionEvent -> {
            try {
                validateUserInput();
                task = grabTask();
                stage = (Stage) this.btnBestaetigen.getScene().getWindow();
                stage.close();
            } catch (IllegalArgumentException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Fehler");
                alert.setHeaderText("Bitte überprüfen sie die Eingabefelder!");
                alert.setContentText("Fehlermeldung: " + e.getMessage());
                alert.showAndWait();
            }

            try {
                DatabaseManager.getInstance().insertTask(this.task);
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        });

    }

    private Task grabTask() {
        Task task = new Task(this.inputKennung.getText(), this.inputBeschreibung.getText(), this.inputAbgabedatum.getValue());
        return task;
    }
    private void validateUserInput() throws IllegalArgumentException {
        boolean success = Arrays.asList(this.inputKennung, this.inputBeschreibung).stream().anyMatch(textField -> !textField.getText().isEmpty());


        if (!success || this.inputAbgabedatum.getValue() == null) {
            throw new IllegalArgumentException();
        }

    }
}
