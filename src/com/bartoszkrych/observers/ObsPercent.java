package com.bartoszkrych.observers;

import com.bartoszkrych.classes.Meal;
import com.bartoszkrych.interfaces.Observer;

import java.util.ArrayList;

import static java.lang.Math.round;

public class ObsPercent implements Observer {

    private double d_protein_p;
    private double d_fat_p;
    private double d_carbo_p;

    public ObsPercent() {
        this.d_protein_p = 0;
        this.d_fat_p = 0;
        this.d_carbo_p = 0;
    }

    public double dGetProteinP() {
        return d_protein_p;
    }

    public double dGetFatP() {
        return d_fat_p;
    }

    public double dGetCarboP() {
        return d_carbo_p;
    }

    @Override
    public void update(double dKcal, double dCPM, ArrayList<Meal> aMeals)
    {
        double d_protein_kcal=0.0;
        double d_fat_kcal=0.0;
        double d_carbo_kcal=0.0;
        for (Meal aM : aMeals) {
            d_protein_kcal += aM.dtGetPCFkCal()[0];
            d_carbo_kcal += aM.dtGetPCFkCal()[1];
            d_fat_kcal += aM.dtGetPCFkCal()[2];
        }

        d_protein_p= (round(d_protein_kcal/dKcal*10000));
        d_fat_p=(round(d_fat_kcal/dKcal*10000));
        d_carbo_p=round((d_carbo_kcal/dKcal*10000));
        d_protein_p/=100;
        d_fat_p/=100;
        d_carbo_p/=100;
    }
}
