package com.uaspborestoran;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import com.uaspborestoran.data.Menu;
import com.uaspborestoran.service.connection;

/**
 * JavaFX App
 */
public class App extends Application {
    public static Menu menu;
    public static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        // scene = new Scene(loadFXML("primary"), 640, 480);
        scene = new Scene(loadFXML("login"));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static void setScene(String fxml) throws IOException {
        Stage stage = (Stage) scene.getWindow();
        scene = new Scene(loadFXML(fxml));
        stage.setScene(scene);
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        try {
            connection connectionService = new connection();
            connectionService.start();
        } catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
        }
        
        launch();
    }

}