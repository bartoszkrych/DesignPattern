package com.bartoszkrych.interfaces;

import com.bartoszkrych.classes.Meal;
import java.util.ArrayList;

public interface Observer{
    void update(double dKcal, double dCPM, ArrayList<Meal> aMeals);
}
