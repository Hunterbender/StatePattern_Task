module com.example._06_fassbender_state_task {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example._06_fassbender_state_task to javafx.fxml;
    exports com.example._06_fassbender_state_task;
}