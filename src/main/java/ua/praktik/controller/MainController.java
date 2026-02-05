package ua.praktik.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import net.datafaker.Faker;
import ua.praktik.model.User;
import ua.praktik.repository.UserRepository;
import ua.praktik.repository.UserRepositoryImpl;
import ua.praktik.util.DatabaseInit;

public class MainController {
    @FXML
    private Button getBtn;

    @FXML
    private Button addBtn;

    @FXML
    private TextField inputText;


    private final UserRepository userRepository = new UserRepositoryImpl();

    @FXML
    private ResourceBundle resources;


    @FXML
    private TableView<User> userTable;

    @FXML
    void onAdd(ActionEvent event) {
        for (int i = 0; i < 5; i++) {
            Faker faker = new Faker();
            String username = faker.name().fullName();
            String password = faker.name().prefix();

            User user = new User(i, username, password);
            try {
                DatabaseInit.initUser();
                userRepository.save(user);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void onGet(ActionEvent event) {
        try {
            var user = userRepository.findAll();
            TableColumn<User, Integer> id = new TableColumn<>("id");
            TableColumn<User, String> username = new TableColumn<>("username");
            TableColumn<User, String> password = new TableColumn<>("password");

            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            username.setCellValueFactory(new PropertyValueFactory<>("username"));
            password.setCellValueFactory(new PropertyValueFactory<>("password"));

            userTable.getColumns().setAll(id, username, password);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {

    }

}
