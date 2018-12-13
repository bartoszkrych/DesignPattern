package com.bartoszkrych;

import com.bartoszkrych.classes.Human;
import com.bartoszkrych.classes.Man;
import com.bartoszkrych.classes.Meal;
import com.bartoszkrych.observers.ObsOpinion;
import com.bartoszkrych.observers.ObsPercent;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.time.Period;
import java.util.Optional;

public class Controller {

    private Human client;
    private ObsOpinion obsOpinion= new ObsOpinion();
    private ObsPercent obsPercent= new ObsPercent();

    public Label yourHeight;
    public Label yourAge;
    public Label yourName;
    public Label yourCPM;
    public Label yourEatenKcal;
    public Label commentObs;
    public Label proteinPercent;
    public Label carboPercent;
    public Label fatPercent;

    @FXML
    public Label yourWeight;

    @FXML
    private TableView<Meal> mealTableView;

    @FXML
    private ObservableList<Meal> data;

    @FXML
    private VBox mainVBox;

    public void initialize(){
        client = new Man("Jan",22,183,88.2);
        client.vAddObserver(obsOpinion);
        client.vAddObserver(obsPercent);

        client.vAddMeal(new Meal(15,90,20));
        client.vAddMeal(new Meal(100,50,30));
        client.vAddMeal(new Meal(20,80,20));


        yourName.setText(client.sGetName());
        yourWeight.setText(""+client.dGetWeight());
        yourHeight.setText(""+client.iGetHeight());
        yourAge.setText(""+client.iGetAge());
        yourCPM.setText(""+client.dGetCPM());

        yourEatenKcal.setText(""+client.dGetEatenKcal());

        proteinPercent.setText(obsPercent.dGetProteinP()+"%");
        fatPercent.setText(obsPercent.dGetFatP()+"%");
        carboPercent.setText(obsPercent.dGetCarboP()+"%");
        commentObs.setText(obsOpinion.sGetComment());

        mealTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Meal>() {
            @Override
            public void changed(ObservableValue<? extends Meal> observable, Meal oldValue, Meal newValue) {
                if(newValue!= null){
                    Meal meal = mealTableView.getSelectionModel().getSelectedItem();

                }
            }
        });

        mealTableView.getItems().setAll(client.getMeals());

    }

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
