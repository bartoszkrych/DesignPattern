package com.bartoszkrych;


import com.bartoszkrych.classes.Human;
import com.bartoszkrych.classes.Man;
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

        this.primaryStage = primaryStage;

        Human c_man = new Man("Jan",22,183,88.2);
        showClientView(c_man);


        //showCreatingView();
    }

    public static void showCreatingView() throws IOException{


        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("creatingView.fxml"));
        mainLayout = loader.load();
        primaryStage.setTitle("Diet control");
        primaryStage.setScene(new Scene(mainLayout, 301, 289));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void showClientView(Human client) throws IOException{

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
/*
        Human c_man = new Man("Jan",22,183,88.2);
        c_man.vAddObserver(new ObsOpinion());
        c_man.vAddObserver(new ObsPercent());
        c_man.vAddMeal(new Meal(15,90,20));
        c_man.vAddMeal(new Meal(100,50,30));
        c_man.vAddMeal(new Meal(20,80,20));
        c_man.vAddMeal(new Meal(100,80,0));
*/

    }
}
