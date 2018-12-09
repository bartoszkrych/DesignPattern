package com.bartoszkrych.classes;

public class Man extends Human {

    public Man(String name, int age, int height, double weight)
    {
        super(name, age, height, weight);
        d_cpm= dSetCPM();
    }

    @Override
    public double dSetCPM() {
        return (665 + (13.75 * dGetWeight()) + (5.003 * dGetWeight()) - (6.775 * iGetAge()))*1.6;
    }
}
