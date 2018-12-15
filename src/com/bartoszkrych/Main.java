package com.bartoszkrych;


import com.bartoszkrych.classes.Human;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private static Stage primaryStage;
    private static VBox mainLayout;

    @Override
    public void start(Stage primaryStage) throws Exception{

        Main.primaryStage = primaryStage;

        showCreatingView();
    }

    private static void showCreatingView() throws IOException{


        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("creatingView.fxml"));
        mainLayout = loader.load();
        primaryStage.setTitle("Diet control");
        primaryStage.setScene(new Scene(mainLayout, 301, 289));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    static void showClientView(Human client) throws IOException{

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("clientView.fxml"));
        mainLayout = loader.load();

        Controller controller = loader.getController();
        controller.setClient(client);

        primaryStage.setScene(new Scene(mainLayout, 848, 550));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
