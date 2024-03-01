package com.example.jatinlab2;

import com.example.jatinlab2.restaurant; // Update import statement

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML private TextField eserverid;
    @FXML private TextField eservername;
    @FXML private TextField eserverdish;
    @FXML private TextField eserverrating;
    @FXML private TableView<restaurant> tableView;
    @FXML private TableColumn<restaurant, Integer> serverid;
    @FXML private TableColumn<restaurant, String> servername;
    @FXML private TableColumn<restaurant, String> serverdish;
    @FXML private TableColumn<restaurant, Integer> serverrating;

    ObservableList<restaurant> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        serverid.setCellValueFactory(new PropertyValueFactory<>("serverid"));
        servername.setCellValueFactory(new PropertyValueFactory<>("servername"));
        serverdish.setCellValueFactory(new PropertyValueFactory<>("serverdish"));
        serverrating.setCellValueFactory(new PropertyValueFactory<>("serverrating"));
        tableView.setItems(list);
    }

    @FXML
    protected void onHelloButtonClick() {
        populateTable();
    }

    public void populateTable() {
        list.clear();
        // Establish a database connection
        String jdbcUrl = "jdbc:mysql://localhost:3306/jatinlab2";
        String dbUser = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            // Execute a SQL query to retrieve data from the database
            String query = "SELECT * FROM restaurant "; // Use the correct table name
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // Populate the table with data from the database
            while (resultSet.next()) {
                int serverid = resultSet.getInt("serverid");
                String servername = resultSet.getString("servername");
                String serverdish = resultSet.getString("serevrdish");
                int serverrating = resultSet.getInt("serverrating");
                tableView.getItems().add(new restaurant(serverid, servername, serverdish, serverrating));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Addingbutton(ActionEvent actionEvent) {
        String Eservername = eservername.getText();
        String Eserverdish = eserverdish.getText();
        String Eserverrating = eserverrating.getText();

        String jdbcUrl = "jdbc:mysql://localhost:3306/jatinlab2";
        String dbUser = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            // Execute a SQL query to retrieve data from the database
            String query = "INSERT INTO restaurant`(`servername`, `serverdish`, `serverrating`) VALUES ('"+Eservername+"','"+Eserverdish+"','"+Eserverrating+"')";
            Statement statement = connection.createStatement();
            statement.execute(query);

            populateTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void DeletingButton(ActionEvent actionEvent) {
        Integer id = Integer.valueOf(eserverid.getText());

        String jdbcUrl = "jdbc:mysql://localhost:3306/jatinlab2";
        String dbUser = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            // Execute a SQL query to retrieve data from the database
            String query = "DELETE FROM restaurant` WHERE serverid = '"+serverid+"'";
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void UpdateData(ActionEvent actionEvent) {
        String Eserverid = eserverid.getText();
        String Eservername = eservername.getText();
        String Eserverdish = eserverdish.getText();
        String Eserverrating = eserverrating.getText();

        String jdbcUrl = "jdbc:mysql://localhost:3306/jatinlab2";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            // Execute a SQL query to retrieve data from the database
            String query = "UPDATE restaurant` SET `servername`='"+Eservername+"',`serverdish`='"+Eserverdish+"',`serverrating`='"+Eserverrating+"' WHERE serverid='"+Eserverid+"'";
            Statement statement = connection.createStatement();
            statement.execute(query);

            populateTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
