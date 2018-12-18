package com.bartoszkrych.interfaces;

import com.bartoszkrych.classes.Meal;

import java.io.Serializable;
import java.util.ArrayList;

public interface Subject extends Serializable {
    void vAddObserver(Observer obs);
    void vRemoveObserver(Observer obs);
    void vNotifyObserver();
}
