package ru.geekbrains.notes.ui;

import ru.geekbrains.notes.domain.Note;

public interface Observer {

    void updateNote(Note notes);
}
