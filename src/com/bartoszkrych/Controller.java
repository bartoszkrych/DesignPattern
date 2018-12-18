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
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    void setClient(Human cClient){
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

    public void deleteMealClick() {
        if(!mealsComboBox.getSelectionModel().isEmpty()){
            if(client.bDeleteMeal(mealsComboBox.getSelectionModel().getSelectedItem().toString())){
                data = FXCollections.observableArrayList(client.getMeals());
                mealTableView.setItems(data);
                updata();
                updataComboBox();
            }
        }

    }

    public void exitButtonClick() {

        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            Date date = new Date();
            FileOutputStream fileOut =
                    new FileOutputStream(client.sGetName()+dateFormat.format(date)+".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(client);
            out.close();
            fileOut.close();

            Stage stage = (Stage) yourName.getScene().getWindow();
            stage.close();
        } catch (IOException i) {
            i.printStackTrace();
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