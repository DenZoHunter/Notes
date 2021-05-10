package ru.geekbrains.notes.ui;

import ru.geekbrains.notes.domain.Notes;

public interface Observer {

    void updateNote(Notes notes);
}
