package com.bartoszkrych.interfaces;
import java.io.Serializable;

public interface Subject extends Serializable {
    void vAddObserver(Observer obs);
    void vRemoveObserver(Observer obs);
    void vNotifyObserver();
}
