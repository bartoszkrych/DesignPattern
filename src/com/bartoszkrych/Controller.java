package com.bartoszkrych;

import com.bartoszkrych.classes.Human;
import com.bartoszkrych.classes.Meal;
import com.bartoszkrych.observers.ObsOpinion;
import com.bartoszkrych.observers.ObsPercent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.time.Period;
import java.util.Optional;

public class Controller {

    private Human client;

    @FXML
    private VBox mainVBox;


    @FXML
    public void showNewMealDialog(){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainVBox.getScene().getWindow());
        dialog.setTitle("Add new Meal");
        dialog.setHeaderText("Use this dialog to add a new meal");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("newMealDialog.fxml"));
        try{
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch(IOException e){
            System.out.println("Couldnt load the dialog");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            DialogController controller = fxmlLoader.getController();
            Meal newItem = controller.processResult();
            client.vAddMeal(newItem);

            //todoListView.getItems().setAll(TodoData.getInstance().getTodoItems());
            //          todoListView.getSelectionModel().select(newItem);
        }
    }

}
