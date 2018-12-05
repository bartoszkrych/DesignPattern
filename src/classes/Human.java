package classes;

import java.util.ArrayList;

public abstract class Human
{
    private String s_name;
    private int i_age;
    private int i_height;
    private double d_weight;

    private ArrayList<Meal> c_meals;

    private double d_eaten_kcal;

    public Human(String sName, int iAge, int iHeight, double dWeight)
    {
        this.s_name = sName;
        this.i_age = iAge;
        this.i_height = iHeight;
        this.d_weight = dWeight;
        this.c_meals = new ArrayList<Meal>();
        this.d_eaten_kcal = 0;
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

}
