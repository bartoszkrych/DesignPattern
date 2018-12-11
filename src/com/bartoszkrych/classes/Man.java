package com.bartoszkrych.classes;

public class Man extends Human {

    public Man(String name, int age, int height, double weight)
    {
        super(name, age, height, weight);
        d_cpm= 0;
        dSetCPM();
    }

    @Override
    public void dSetCPM() {
        d_cpm =(665 + (13.75 * dGetWeight()) + (5.003 * iGetHeight()) - (6.775 * iGetAge()));
        d_cpm = dRoundDouble(d_cpm);
    }
}
