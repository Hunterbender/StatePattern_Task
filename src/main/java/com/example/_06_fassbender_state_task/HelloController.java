package com.example._06_fassbender_state_task;

import com.example._06_fassbender_state_task.service.TaskService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.Console;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    public Button btnCreateTask;
    public Button btnStartTask;
    public Button btnCloseTask;
    public Button btnAbortTask;
    public ListView<Task> lvTasks;
    public Label lbStatus;

    private PopupController controller;

    private ObservableList<Task> taskObservableList = FXCollections.observableArrayList();
    @FXML
    private Label welcomeText;

    private Task task;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.lvTasks.setItems(taskObservableList);

        btnCreateTask.setOnAction(actionEvent -> {
            try {
                popNewWindow();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        btnStartTask.setOnAction(actionEvent -> {
            if (lvTasks.getSelectionModel().getSelectedItem() != null) {
                task = lvTasks.getSelectionModel().getSelectedItem();
                this.lbStatus.setText(task.start());
                lvTasks.refresh();
            }
        });

        btnCloseTask.setOnAction(actionEvent -> {
            if (lvTasks.getSelectionModel().getSelectedItem() != null) {
                task = lvTasks.getSelectionModel().getSelectedItem();
                this.lbStatus.setText(task.finish());
                lvTasks.refresh();
            }
        });

        btnAbortTask.setOnAction(actionEvent -> {
            if (lvTasks.getSelectionModel().getSelectedItem() != null) {
                task = lvTasks.getSelectionModel().getSelectedItem();
                this.lbStatus.setText(task.decline());
                lvTasks.refresh();
            }
        });
    }

    private void popNewWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("popup-view.fxml"));
        AnchorPane root = loader.load();
        root.getChildren().add(new Label());
        Scene scene = new Scene(root, 320, 200);
        Stage secondStage = new Stage();
        secondStage.setResizable(false);
        secondStage.initModality(Modality.APPLICATION_MODAL);
        secondStage.setScene(scene);
        secondStage.showAndWait();
        controller = loader.getController();
        if (controller.getTask() != null) {
            this.taskObservableList.add(controller.getTask());
        }
    }
}