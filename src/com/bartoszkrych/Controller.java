package com.bartoszkrych;

import com.bartoszkrych.classes.Human;
import com.bartoszkrych.classes.Man;
import com.bartoszkrych.classes.Meal;
import com.bartoszkrych.observers.ObsOpinion;
import com.bartoszkrych.observers.ObsPercent;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.io.IOException;
import java.time.Period;
import java.util.Optional;

public class Controller {

    private Human client;
    private ObsOpinion obsOpinion= new ObsOpinion();
    private ObsPercent obsPercent= new ObsPercent();

    public TableColumn<Meal,String> colComment;
    public TableColumn<Meal,Double> colProtein;
    public TableColumn<Meal,Double> colCarbo;
    public TableColumn<Meal,Double> colFat;
    public TableColumn<Meal,Double> colKcal;

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

        client.vAddMeal(new Meal(15.7,91.2,20.2,"Dinner"));
        client.vAddMeal(new Meal(99.9,52.4,30.7,"breakfast"));
        client.vAddMeal(new Meal(20.1,80.2,29.9,"After training"));


        yourName.setText(client.sGetName());
        yourWeight.setText(""+client.dGetWeight());
        yourHeight.setText(""+client.iGetHeight());
        yourAge.setText(""+client.iGetAge());
        yourCPM.setText(""+client.dGetCPM());

        //colComment.setCellValueFactory(new PropertyValueFactory<>("sp_comment"));
        colComment.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Meal, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Meal, String> param) {
                return new SimpleStringProperty(param.getValue().sGetComment());
            }
        });
        colCarbo.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Meal, Double>, ObservableValue<Double>>() {
            @Override
            public ObservableValue<Double> call(TableColumn.CellDataFeatures<Meal, Double> param) {
                return new SimpleDoubleProperty(param.getValue().dGetCarbohydrates()).asObject();
            }
        });
        colFat.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Meal, Double>, ObservableValue<Double>>() {
            @Override
            public ObservableValue<Double> call(TableColumn.CellDataFeatures<Meal, Double> param) {
                return new SimpleDoubleProperty(param.getValue().dGetFat()).asObject();
            }
        });
        colProtein.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Meal, Double>, ObservableValue<Double>>() {
            @Override
            public ObservableValue<Double> call(TableColumn.CellDataFeatures<Meal, Double> param) {
                return new SimpleDoubleProperty(param.getValue().dGetProtein()).asObject();
            }
        });
        colKcal.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Meal, Double>, ObservableValue<Double>>() {
            @Override
            public ObservableValue<Double> call(TableColumn.CellDataFeatures<Meal, Double> param) {
                return new SimpleDoubleProperty(param.getValue().dGetKcal()).asObject();
            }
        });


        data = FXCollections.observableArrayList(client.getMeals());


        mealTableView.setItems(data);

        updata();

        //mealTableView = mealTableView.getItems();
        /*
        mealTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Meal>() {
            @Override
            public void changed(ObservableValue<? extends Meal> observable, Meal oldValue, Meal newValue) {
                if(newValue!= null){
                    Meal meal = mealTableView.getSelectionModel().getSelectedItem();

                }
            }
        });*/

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
            data = mealTableView.getItems();
            data.add(newItem);
            updata();
            //todoListView.getItems().setAll(TodoData.getInstance().getTodoItems());
            //          todoListView.getSelectionModel().select(newItem);
        }
    }


    private void updata(){
        yourEatenKcal.setText(""+client.dGetEatenKcal());

        proteinPercent.setText(obsPercent.dGetProteinP()+"%");
        fatPercent.setText(obsPercent.dGetFatP()+"%");
        carboPercent.setText(obsPercent.dGetCarboP()+"%");
        commentObs.setText(obsOpinion.sGetComment());
    }

}
