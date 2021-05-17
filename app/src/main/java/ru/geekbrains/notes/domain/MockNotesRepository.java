package ru.geekbrains.notes.domain;

import java.util.List;

public class MockNotesRepository implements NotesRepository {
    @Override
    public void getNotes(Callback<List<Note>> callback) {

    }

    @Override
    public void addNote(String name, String description, Callback<Note> callback) {

    }

    @Override
    public void remove(Note item, Callback<Object> callback) {

    }

 /*  private final ArrayList<Note> data = new ArrayList<>();


    @Override
    public List<Note> getNotes() {
        addNotes();
        return new ArrayList<>(data);
    }

    @Override
    public Note addNote() {
        return new Note("новое имя", "новое описание", "новая дата") ;
    }

    @Override
    public void removeAtPosition(int longClickedPosition) {

    }

    public List<Note> addNotes() {

        data.add(new Note("первое имя", "первое описание", "первая дата"));
        data.add(new Note("второе имя", "второе описание", "вторая дата"));
        data.add(new Note("третье имя", "третье описание", "третья дата"));

        return data;
    }*/


}
