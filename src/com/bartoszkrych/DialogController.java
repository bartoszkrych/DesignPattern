package com.bartoszkrych;

import com.bartoszkrych.classes.Meal;
import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;


public class DialogController {

    @FXML
    private TextField  commentField;
    @FXML
    private Spinner<Integer> proteinField;
    @FXML
    private Spinner<Integer> carboField;
    @FXML
    private Spinner<Integer>  fatField;

    public Meal processResult(){
        String comment = commentField.getText().trim();
        int protein = proteinField.getValue().intValue();
        int carbo = carboField.getValue().intValue();
        int fat = fatField.getValue().intValue();

        Meal newItem = new Meal(protein,carbo,fat,comment);
        return newItem;
    }
}
