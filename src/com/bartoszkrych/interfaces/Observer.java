package com.bartoszkrych.interfaces;

import com.bartoszkrych.classes.Meal;

import java.io.Serializable;
import java.util.ArrayList;

public interface Observer extends Serializable {
    void update(double dKcal, double dCPM, ArrayList<Meal> aMeals);
}
