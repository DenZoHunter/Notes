package ru.geekbrains.notes.domain;


import java.util.List;


public interface NotesRepository {

    List<Notes> getNotes();

    Notes addNote();
}
