package com.bartoszkrych.classes;

public class Woman extends Human{
    public Woman(String sName, int iAge, int iHeight, double dWeight) {
        super(sName, iAge, iHeight, dWeight);
        d_cpm = dSetCPM();
    }

    @Override
    public double dSetCPM() {
        return (655.1 + (9.563 * dGetWeight()) + (1.85 * iGetHeight()) - (4.676 * iGetAge()))*1.6;
    }
}
