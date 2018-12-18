package com.bartoszkrych;

import com.bartoszkrych.classes.Human;
import com.bartoszkrych.classes.Man;
import com.bartoszkrych.classes.Woman;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class firstController {

    @FXML
    private RadioButton sexMan;
    @FXML
    private RadioButton sexWoman;

    @FXML
    private TextField setName;
    @FXML
    private TextField setAge;
    @FXML
    private TextField setHeight;
    @FXML
    private TextField setWeight;

    public void initialize(){
        ToggleGroup sexGroup = new ToggleGroup();
        sexMan.setToggleGroup(sexGroup);
        sexWoman.setToggleGroup(sexGroup);
    }

    public void createHumanClick() throws IOException {
        String s_name = setName.getText().trim();
        int i_age = 0;
        int i_height = 0;
        double d_weight = 0;
        Human client;


        boolean isFile = false;
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            Date date = new Date();

            File f_file = new File(s_name + dateFormat.format(date) + ".ser");

            if (f_file.exists() && f_file.length() != 0) {
                FileInputStream fis = new FileInputStream(f_file);
                ObjectInputStream ois = new ObjectInputStream(fis);

                client = (Human) ois.readObject();
                ois.close();
                fis.close();
                isFile = true;
                Main.showClientView(client);
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
            return;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (!isFile) {

            boolean valid = true;
            if(s_name.equals("")){
                valid=false;
                setName.setStyle("-fx-control-inner-background: rgba(229,0,0,0.4);");
            }else{
                setName.setStyle(null);
            }
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


            if (valid) {
                if (sexMan.isSelected()) {
                    client = new Man(s_name, i_age, i_height, d_weight);
                } else {
                    client = new Woman(s_name, i_age, i_height, d_weight);
                }
                Main.showClientView(client);
            }
        }


    }

    public void exitButtonClick() {
        Stage stage = (Stage) setName.getScene().getWindow();
        stage.close();
    }
}
