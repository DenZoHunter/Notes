package ru.geekbrains.notes;

import ru.geekbrains.notes.domain.Notes;

public interface Observer {

    void updateNote(Notes notes);
}
