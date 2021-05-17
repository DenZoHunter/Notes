package ru.geekbrains.notes.domain;


import java.util.List;


public interface NotesRepository {

    void getNotes(Callback<List<Note>> callback);

    void addNote(String name, String description, Callback<Note> callback);

    void remove(Note item, Callback<Object> callback);

}
