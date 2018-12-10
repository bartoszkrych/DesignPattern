package com.bartoszkrych;

import com.bartoszkrych.classes.Man;
import com.bartoszkrych.classes.Meal;
import com.bartoszkrych.observers.ObsPercent;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/*
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }
*/
public class Main {
    public static void main(String[] args) {
        //launch(args);

        Man c_man = new Man("Jan",22,183,88.2);
        c_man.vAddObserver(new ObsPercent());
        c_man.vAddMeal(new Meal(15,16,0));
        c_man.vAddMeal(new Meal(0,16,11));
    }
}
