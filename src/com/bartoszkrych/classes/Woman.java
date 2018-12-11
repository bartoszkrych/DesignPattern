package com.bartoszkrych.classes;

public class Woman extends Human{
    public Woman(String sName, int iAge, int iHeight, double dWeight) {
        super(sName, iAge, iHeight, dWeight);
        d_cpm = 0;
        dSetCPM();
    }

    @Override
    public void dSetCPM() {
        d_cpm =(655.1 + (9.563 * dGetWeight()) + (1.85 * iGetHeight()) - (4.676 * iGetAge()));
        d_cpm = dRoundDouble(d_cpm);
    }
}
