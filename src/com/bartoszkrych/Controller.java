package com.bartoszkrych;

import com.bartoszkrych.classes.Human;
import com.bartoszkrych.classes.Meal;
import com.bartoszkrych.observers.ObsOpinion;
import com.bartoszkrych.observers.ObsPercent;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;

public class Controller {

    private Human client;
    private ObsOpinion obsOpinion= new ObsOpinion();
    private ObsPercent obsPercent= new ObsPercent();

    @FXML
    private ComboBox mealsComboBox;

    @FXML
    private TableColumn<Meal,String> colComment;
    @FXML
    private TableColumn<Meal,Double> colProtein;
    @FXML
    private TableColumn<Meal,Double> colCarbo;
    @FXML
    private TableColumn<Meal,Double> colFat;
    @FXML
    private TableColumn<Meal,Double> colKcal;

    @FXML
    private TextField  commentField;
    @FXML
    private TextField proteinField;
    @FXML
    private TextField carboField;
    @FXML
    private TextField  fatField;
    @FXML
    private Label yourHeight;
    @FXML
    private Label yourAge;
    @FXML
    private Label yourName;
    @FXML
    private Label yourCPM;
    @FXML
    private Label yourEatenKcal;
    @FXML
    private Label commentObs;
    @FXML
    private Label proteinPercent;
    @FXML
    private Label carboPercent;
    @FXML
    private Label fatPercent;
    @FXML
    private Label yourWeight;
    @FXML
    private TableView<Meal> mealTableView;

    private ObservableList<Meal> data;


    public void initialize(){

        obsOpinion = new ObsOpinion();
        obsPercent = new ObsPercent();

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

    }

    @FXML
    public void addMealClick(){

        String comment = commentField.getText().trim();
        double protein=0;
        double carbo=0;
        double fat=0;


        boolean valid = true;
        try {
            protein = Double.parseDouble(proteinField.getText().trim());
            proteinField.setStyle(null);
        } catch (NumberFormatException ex) {
            valid = false;
            proteinField.setStyle("-fx-control-inner-background: rgba(229,0,0,0.4);");
        }

        try {
            carbo = Double.parseDouble(carboField.getText().trim());
            carboField.setStyle(null);
        } catch (NumberFormatException ex) {
            valid = false;
            carboField.setStyle("-fx-control-inner-background: rgba(229,0,0,0.4);");
        }

        try {
            fat = Double.parseDouble(fatField.getText().trim());
            fatField.setStyle(null);
        } catch (NumberFormatException ex) {
            valid = false;
            fatField.setStyle("-fx-control-inner-background: rgba(229,0,0,0.4);");
        }

            if (valid) {
                commentField.setText("");
                fatField.setText("");
                proteinField.setText("");
                carboField.setText("");
                Meal newItem = new Meal(protein,carbo,fat,comment);
                client.vAddMeal(newItem);
                data = mealTableView.getItems();
                data.add(newItem);
                mealsComboBox.getItems().add(newItem.sGetComment());
                updata();
            }

    }



    public void setClient(Human cClient){
        client=cClient;

        client.vAddObserver(obsOpinion);
        client.vAddObserver(obsPercent);

        yourName.setText(client.sGetName());
        yourWeight.setText(""+client.dGetWeight());
        yourHeight.setText(""+client.iGetHeight());
        yourAge.setText(""+client.iGetAge());
        yourCPM.setText(""+client.dGetCPM());


        data = FXCollections.observableArrayList(client.getMeals());
        mealTableView.setItems(data);
        mealTableView.getItems().setAll(client.getMeals());

        updataComboBox();

        updata();
    }

    public void deleteMealClick(ActionEvent actionEvent) {
        if(!mealsComboBox.getSelectionModel().isEmpty()){
            if(client.bDeleteMeal(mealsComboBox.getSelectionModel().getSelectedItem().toString())){
                data = FXCollections.observableArrayList(client.getMeals());
                mealTableView.setItems(data);
                updata();
                updataComboBox();
            }
        }

    }

    private void updata(){
        yourEatenKcal.setText(""+client.dGetEatenKcal());

        proteinPercent.setText(obsPercent.dGetProteinP()+"%");
        fatPercent.setText(obsPercent.dGetFatP()+"%");
        carboPercent.setText(obsPercent.dGetCarboP()+"%");
        commentObs.setText(obsOpinion.sGetComment());
    }
    private void updataComboBox(){
        mealsComboBox.getItems().clear();
        for (Meal aData : data) {
            mealsComboBox.getItems().add(aData.sGetComment());
        }

    }
}




/*
    @FXML
    public void showNewMealDialog(){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainVBox.getScene().getWindow());
        dialog.setTitle("Add new Meal");
        dialog.setHeaderText("Use this dialog to add a new meal");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("_________.fxml"));
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
*/