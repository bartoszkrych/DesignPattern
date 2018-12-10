package com.bartoszkrych.observers;

import com.bartoszkrych.classes.Meal;
import com.bartoszkrych.interfaces.Observer;

import java.util.ArrayList;

public class ObsEatenKcal implements Observer {
    String s_comment;

    public ObsEatenKcal() {
        this.s_comment = "";
    }

    @Override
    public void update(double dKcal, double dCPM, ArrayList<Meal> aMeals) {

    }
}
