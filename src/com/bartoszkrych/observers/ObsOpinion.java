package com.bartoszkrych.observers;

import com.bartoszkrych.classes.Meal;
import com.bartoszkrych.interfaces.Observer;

import java.util.ArrayList;

public class ObsOpinion implements Observer {
    private String s_comment;

    public ObsOpinion() {
        this.s_comment = "";
    }

    @Override
    public void update(double dKcal, double dCPM, ArrayList<Meal> aMeals) {

        double d_kcal = dKcal - dCPM;

        if(d_kcal>100 && d_kcal<=300) s_comment = "You will be bigger! ;-)";
        else if(d_kcal>300) s_comment = "Too much! Go to gym!";
        else if(d_kcal<-100 && d_kcal>=-300) s_comment = "Grow thin ;-)";
        else if(d_kcal<-300) s_comment = "Sooo bad! Eat more today!";
        else s_comment="Perfect!";

        System.out.println( dCPM+"/"+dKcal+"    "+s_comment);
    }

    public String sGetComment() {
        return s_comment;
    }
}
