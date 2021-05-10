package ru.geekbrains.notes.domain;

import java.util.ArrayList;
import java.util.List;



public class MockNotesRepository implements NotesRepository{

    private final ArrayList<Notes> data = new ArrayList<>();


    @Override
    public List<Notes> getNotes() {
        return new ArrayList<>(data);
    }

    @Override
    public Notes addNote() {


        ArrayList<Notes> notes = new ArrayList<>();

        notes.add(new Notes("первое имя","первое описание","первая дата"));
        notes.add(new Notes("второе имя","вторая описание","вторая дата"));
        notes.add(new Notes("третье имя","третья описание","третья дата"));

        Notes added = notes.get(notes.size());

        return added;
    }
}
