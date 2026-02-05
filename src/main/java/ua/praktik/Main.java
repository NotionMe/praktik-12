package ua.praktik;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/Main.fxml"));
        Scene scene = new Scene(loader.load(), 1100, 650);

        stage.setTitle("TableView (No DB)");
        stage.setScene(scene);
        stage.show();
    }
}