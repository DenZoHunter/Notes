package ru.geekbrains.notes.domain;

import java.util.ArrayList;
import java.util.List;

public class MockNotesRepository implements NotesRepository {

    private final ArrayList<Notes> data = new ArrayList<>();


    @Override
    public List<Notes> getNotes() {

        ArrayList<Notes> notes = new ArrayList<>();

        notes.add(new Notes("первое имя", "первое описание", "первая дата"));
        notes.add(new Notes("второе имя", "второе описание", "вторая дата"));
        notes.add(new Notes("третье имя", "третье описание", "третья дата"));

        return notes;
    }
}
