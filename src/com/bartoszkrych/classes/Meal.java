package com.bartoszkrych.classes;

import java.io.Serializable;

public class Meal implements Serializable
{
    private double d_protein;
    private double d_carbohydrates;
    private double d_fat;
    private double d_kcal;

    private String s_comment;

    private final int fi_g_fat = 9;
    private final int fi_g_carbo = 4;
    private final int fi_g_protein = 4;

    public Meal(double dProtein, double dCarbohydrates, double dFat, String sComment)
    {
        this.d_protein = dProtein;
        this.d_carbohydrates = dCarbohydrates;
        this.d_fat = dFat;
        this.s_comment = sComment;
        vSetKcal();
    }

    public String sGetComment() {
        return s_comment;
    }

    private void vSetKcal()
    {
        d_kcal = d_carbohydrates*fi_g_carbo+d_fat*fi_g_fat+d_protein*fi_g_protein;
        d_kcal = Math.round(d_kcal*100);
        d_kcal/=100;
    }

    public double dGetProtein()
    {
        return d_protein;
    }

    public double dGetCarbohydrates()
    {
        return d_carbohydrates;
    }

    public double[] dtGetPCFkCal(){
        return new double[]{d_protein * fi_g_protein, d_carbohydrates * fi_g_carbo, d_fat * fi_g_fat};
    }

    public double dGetFat()
    {
        return d_fat;
    }

    public double dGetKcal()
    {
        return d_kcal;
    }
}
