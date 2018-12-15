package com.bartoszkrych.classes;

import com.bartoszkrych.interfaces.Observer;
import com.bartoszkrych.interfaces.Subject;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.round;

public abstract class Human implements Subject
{
    private String s_name;
    private int i_age;
    private int i_height;
    private double d_weight;

    protected ArrayList<Meal> c_meals;

    protected double d_eaten_kcal;
    protected double d_cpm;

    protected List<Observer> observers;

    public Human(String sName, int iAge, int iHeight, double dWeight)
    {
        this.s_name = sName;
        this.i_age = iAge;
        this.i_height = iHeight;
        this.d_weight = dWeight;
        this.c_meals = new ArrayList<Meal>();
        this.d_eaten_kcal = 0;
        this.d_cpm=0;
        this.observers = new ArrayList<Observer>();
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

    public ArrayList<Meal> getMeals() {
        return c_meals;
    }

    public double dGetCPM() {
        return d_cpm;
    }

    public double dGetEatenKcal() {
        return d_eaten_kcal;
    }

    public void vAddObserver(Observer obs){
        observers.add(obs);
    }

    public void vRemoveObserver(Observer obs){
        observers.remove(obs);
    }

    public void vNotifyObserver(){
        for(Observer obs : observers) obs.update(d_eaten_kcal,d_cpm,c_meals);
    }


    public void vAddMeal(Meal cMeal)
    {
        d_eaten_kcal+=cMeal.dGetKcal();
        c_meals.add(cMeal);
        vNotifyObserver();
    }

    public boolean bDeleteMeal(String sComment){
        for(int i = 0; i < c_meals.size(); i++){
            if(c_meals.get(i).sGetComment().equals(sComment)){
                d_eaten_kcal-= c_meals.get(i).dGetKcal();
                c_meals.remove(i);
                vNotifyObserver();
                return true;
            }
        }
        return false;
    }

    public abstract void dSetCPM();

    protected double dRoundDouble(double dNumber)
    {
        dNumber *= 100;
        dNumber = round(dNumber);
        dNumber /= 100;
        return dNumber;
    }
}
