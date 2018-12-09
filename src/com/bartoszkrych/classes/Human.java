package com.bartoszkrych.classes;

import java.util.ArrayList;

public abstract class Human
{
    protected String s_name;
    protected int i_age;
    protected int i_height;
    protected double d_weight;

    protected ArrayList<Meal> c_meals;

    protected double d_eaten_kcal;
    protected double d_cpm;

    public Human(String sName, int iAge, int iHeight, double dWeight)
    {
        this.s_name = sName;
        this.i_age = iAge;
        this.i_height = iHeight;
        this.d_weight = dWeight;
        this.c_meals = new ArrayList<Meal>();
        this.d_eaten_kcal = 0;
        this.d_cpm=0;
    }


    public String sGetName()
    {
        return s_name;
    }

    public int iGetAge()
    {
        return i_age;
    }

    public int iGetHeight()
    {
        return i_height;
    }

    public double dGetWeight()
    {
        return d_weight;
    }

    public void vAddMeal(Meal cMeal)
    {
        d_eaten_kcal+=cMeal.dGetKcal();
        c_meals.add(cMeal);
    }

    public abstract double dSetCPM();
}
