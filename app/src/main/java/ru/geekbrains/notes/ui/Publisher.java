package ru.geekbrains.notes.ui;

import java.util.ArrayList;
import java.util.List;

import ru.geekbrains.notes.domain.Note;

public class Publisher {
    private final List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notify(Note notes) {
        for (Observer observer : observers) {
            observer.updateNote(notes);
        }
    }
}
