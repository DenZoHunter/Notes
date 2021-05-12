package ru.geekbrains.notes.domain;

import java.util.ArrayList;
import java.util.List;

public class MockNotesRepository implements NotesRepository {

    private final ArrayList<Notes> data = new ArrayList<>();


    @Override
    public List<Notes> getNotes() {
        addNotes();
        return new ArrayList<>(data);
    }

    @Override
    public Notes addNote() {
        return new Notes("новое имя", "новое описание", "новая дата") ;
    }

    public List<Notes> addNotes() {

        data.add(new Notes("первое имя", "первое описание", "первая дата"));
        data.add(new Notes("второе имя", "второе описание", "вторая дата"));
        data.add(new Notes("третье имя", "третье описание", "третья дата"));

        return data;
    }


}
