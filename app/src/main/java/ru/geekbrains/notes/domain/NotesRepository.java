package ru.geekbrains.notes.domain;

import java.util.ArrayList;
import java.util.List;

import ru.geekbrains.notes.R;

public class NotesRepository {

    public List<Notes> getNotes() {
        ArrayList<Notes> notes = new ArrayList<>();
        notes.add(new Notes(R.string.firstName, R.string.firstDescr, R.string.firstDate));
        notes.add(new Notes(R.string.secondName, R.string.secondDescr, R.string.secondDate));
        notes.add(new Notes(R.string.thirdName, R.string.thirdDescr, R.string.thirdDate));
        return notes;

    }


}
