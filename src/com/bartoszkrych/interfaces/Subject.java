package com.bartoszkrych.interfaces;

import com.bartoszkrych.classes.Meal;

import java.util.ArrayList;

public interface Subject {
    void vAddObserver(Observer obs);
    void vRemoveObserver(Observer obs);
    void vNotifyObserver();
}
