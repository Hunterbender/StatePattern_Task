package com.example._06_fassbender_state_task.dal;

import com.example._06_fassbender_state_task.Task;
import com.example._06_fassbender_state_task.util.PropertyManager;

import java.net.URISyntaxException;
import java.sql.*;
import java.sql.DriverManager;

public class DatabaseManager {
    private Connection connection = null;
    private String driver;
    private String url;
    private String user;
    private String pwd;

    private static final String TASK_SELECT_ALL = "SELECT KENNUNG, BESCHREIBUNG, ABGABEDATUM, STATUS FROM TASK";
    private static final String TASK_INSERT = "INSERT INTO TASK (KENNUNG, BESCHREIBUNG, ABGABEDATUM, STATUS) VALUES (?,?,?,?)";

    private static DatabaseManager instance;

    private DatabaseManager() throws URISyntaxException {
        PropertyManager.getInstance().setFilename("dbtest.properties");
        this.driver = PropertyManager.getInstance().readProperty("driver", "oracle.jdbc.OracleDriver");
        this.url = PropertyManager.getInstance().readProperty("url", "jdbc:oracle:thin:@tcif.htl-villach.at:1521/orcl");
        this.user = PropertyManager.getInstance().readProperty("username", "d3b05");
        this.pwd = PropertyManager.getInstance().readProperty("password", "d3b05");
    }

    public static DatabaseManager getInstance() throws URISyntaxException {
        return instance == null ? instance = new DatabaseManager() : instance;
    }

    public Connection createConnection() throws SQLException {
        Connection con = null;

        try {
            //1. Registrieren des Treibers
            Class.forName(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            //2. Erzeugen der Verbindung
            con = DriverManager.getConnection(url, user, pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    public boolean insertTask(Task task) {
        ResultSet resultSet;
        PreparedStatement preparedStatement;
        int id = -1;
        boolean result = false;

        try (Connection con = this.createConnection()) {
            con.setAutoCommit(true);
            preparedStatement = con.prepareStatement(TASK_INSERT, new String[]{"KENNUNG"});
            preparedStatement.setString(1, task.getIdentifier());
            preparedStatement.setString(2, task.getDescription());
            preparedStatement.setString(3, task.getDueDate().toString());
            preparedStatement.setString(4, task.getState().toString());
            preparedStatement.execute();

            resultSet = preparedStatement.getResultSet();

            if (resultSet.next()) {
                result = true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

}
