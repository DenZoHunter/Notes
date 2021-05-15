package ru.geekbrains.notes.domain;


import java.util.List;


public interface NotesRepository {

/*    List<Notes> getNotes();

    Notes addNote();

    void removeAtPosition(int longClickedPosition);*/





    void getNotes(Callback<List<Note>> callback);

    void addNote(String name, String description, Callback<Note> callback);

    void remove(Note item, Callback<Object> callback);

}
