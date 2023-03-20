package com.example._06_fassbender_state_task.dal;

import com.example._06_fassbender_state_task.Task;
import com.example._06_fassbender_state_task.util.PropertyManager;

import java.sql.*;

public class DatabaseManager {
    private Connection connection = null;
    private String driver;
    private String url;
    private String user;
    private String pwd;
    private static final String TASK_SELECT_ALL = "SELECT * FROM TASK";
    private static final String TASK_INSERT = "INSERT INTO TASK (IDENTIFIER, DESCRIPTION, DUEDATE, STATE) VALUES (?,?,?,?)";

    private static DatabaseManager instance;

    private DatabaseManager() {
        PropertyManager.getInstance().setFilename("db.properties");
        driver = PropertyManager.getInstance().readProperty("driver", "oracle.jdbc.OracleDriver");
        url = PropertyManager.getInstance().readProperty("url", "jdbc:oracle:thin:@tcif.htl-villach.at:1521/orcl");
        user = PropertyManager.getInstance().readProperty("user", "d3b05");
        pwd = PropertyManager.getInstance().readProperty("pwd", "d3b05");
    }

    public static DatabaseManager getInstance() {
        return instance == null ? instance = new DatabaseManager() : instance;
    }

    private Connection createConnection() throws SQLException {
        Connection con = null;
        try{
            // 1. Registtrieren des Treibers
            Class.forName(driver);
        }catch (Exception e){
            e.printStackTrace();
        }

        try {
            //2. Erzeugen der Connection mit url, user, pwd
            con = DriverManager.getConnection(url, user, pwd);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return con;
    }

    public boolean insertTask(Task t) {
        ResultSet resultSet;
        PreparedStatement prepStmt;
        boolean result = false;

        try(Connection con = this.createConnection()){
            con.setAutoCommit(true);
            prepStmt = con.prepareStatement(TASK_INSERT, new String[]{"IDENTIFIER"});
            prepStmt.setString(1, t.getIdentifier());
            prepStmt.setString(2, t.getDescription());
            prepStmt.setDate(3, Date.valueOf(t.getDueDate()));
            prepStmt.setString(4, t.getState().toString());

            //Achtung hier execute ohne Übergabe des Statements
            prepStmt.execute();

            //Auslesen der generierten Id, damit das obj, vollständig ist!
            resultSet = prepStmt.getGeneratedKeys();
            if(resultSet.next()){
                result = true;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }


}
