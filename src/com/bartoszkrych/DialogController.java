package com.bartoszkrych;

import com.bartoszkrych.classes.Meal;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;


public class DialogController {

    @FXML
    private TextField  commentField;
    @FXML
    private TextField  proteinField;
    @FXML
    private TextField  carboField;
    @FXML
    private TextField  fatField;

    public Meal processResult(){
        String comment = commentField.getText().trim();
        double protein = fromString(proteinField.getText().trim());
        double carbo = fromString(carboField.getText().trim());
        double fat = fromString(fatField.getText().trim());

        Meal newItem = new Meal(protein,carbo,fat,comment);
        return newItem;
    }


    private Double fromString(String s) {
        if (s.isEmpty() || "-".equals(s) || ".".equals(s) || "-.".equals(s)) {
            return 0.0 ;
        } else {
            return Double.valueOf(s);
        }
    }
}
