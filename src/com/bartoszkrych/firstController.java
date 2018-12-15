package com.bartoszkrych;

import com.bartoszkrych.classes.Human;
import com.bartoszkrych.classes.Man;
import com.bartoszkrych.classes.Woman;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class firstController {

    private Main main;


    public RadioButton sexMan;
    public RadioButton sexWoman;
    private Human client;


    public TextField setName;
    public TextField setAge;
    public TextField setHeight;
    public TextField setWeight;

    public void initialize(){
        ToggleGroup sexGroup = new ToggleGroup();
        sexMan.setToggleGroup(sexGroup);
        sexWoman.setToggleGroup(sexGroup);
    }

    public void createHuman() throws IOException {

        String s_name = setName.getText().trim();
        int i_age = 0;
        int i_height = 0;
        double d_weight = 0;

        boolean valid = true;

        try {
            d_weight = Double.parseDouble(setWeight.getText().trim());
            setWeight.setStyle(null);
        } catch (NumberFormatException ex) {
            valid = false;
            setWeight.setStyle("-fx-control-inner-background: rgba(229,0,0,0.4);");
        }

        try {
            i_age = Integer.parseInt(setAge.getText().trim());
            setAge.setStyle(null);
        } catch (NumberFormatException ex) {
            valid = false;
            setAge.setStyle("-fx-control-inner-background: rgba(229,0,0,0.4);");
        }

        try {
            i_height = Integer.parseInt(setHeight.getText().trim());
            setHeight.setStyle(null);
        } catch (NumberFormatException ex) {
            valid = false;
            setHeight.setStyle("-fx-control-inner-background: rgba(229,0,0,0.4);");
        }

        if(valid){
            if(sexMan.isSelected()){
                client = new Man (s_name,i_age,i_height,d_weight);
            }else{
                client = new Woman(s_name,i_age,i_height,d_weight);
            }
            main.showClientView(client);
        }
    }
}
