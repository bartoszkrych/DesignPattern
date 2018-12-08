package com.bartoszkrych.interfaces;

public interface Subject {
    void removeObserver();
    void registerObserver();
    void notifyObserver();
}
