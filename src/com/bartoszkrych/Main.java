package com.bartoszkrych;

import com.bartoszkrych.classes.Human;
import com.bartoszkrych.classes.Man;
import com.bartoszkrych.classes.Meal;
import com.bartoszkrych.observers.ObsOpinion;
import com.bartoszkrych.observers.ObsPercent;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("meals.fxml"));

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 845, 550));
        primaryStage.show();
    }

//public class Main {
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
