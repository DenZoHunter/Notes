package ru.geekbrains.notes.domain;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class NotesListViewModel extends ViewModel {

    private final MutableLiveData<List<Notes>> notesLiveData = new MutableLiveData<>();
    private final MutableLiveData<Notes> noteAddedLiveData = new MutableLiveData<>();
    private final MutableLiveData<Integer> noteDeletedLiveData = new MutableLiveData<>();

    private final MockNotesRepository repository = new MockNotesRepository();

    public LiveData<List<Notes>> getNotesLiveData() {
        return notesLiveData;
    }

    public void requestNotes() {
        List<Notes> notes = repository.getNotes();

        notesLiveData.setValue(notes);
    }

    public void addClicked() {
        Notes notes = repository.addNote();
        noteAddedLiveData.setValue(notes);

    }

    public LiveData<Notes> getNoteAddedLiveData() {
        return noteAddedLiveData;


    }


    public void deleteClicked(int longClickedPosition) {
        repository.removeAtPosition(longClickedPosition);

        noteDeletedLiveData.setValue(longClickedPosition);
    }

    public LiveData<Integer> getNoteDeletedLiveData() {
        return noteDeletedLiveData;
    }
}
