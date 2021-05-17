
package ru.geekbrains.notes.domain;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class NotesListViewModel extends ViewModel {

    private final MutableLiveData<List<Note>> notesLiveData = new MutableLiveData<>();

    private final MutableLiveData<Note> noteAddedLiveData = new MutableLiveData<>();
    private final MutableLiveData<Integer> noteDeletedLiveData = new MutableLiveData<>();


    private final NotesRepository repository = new FirestoreNotesRepository();

    public LiveData<List<Note>> getNotesLiveData() {
        return notesLiveData;
    }


    public void addClicked() {

        repository.addNote("TITLE", "DESCRIPTION", new Callback<Note>() {
            @Override
            public void onSuccess(Note value) {
                if (notesLiveData.getValue() != null) {
                    ArrayList<Note> notes = new ArrayList<>(notesLiveData.getValue());
                    notes.add(value);

                    notesLiveData.setValue(notes);
                } else {
                    ArrayList<Note> notes = new ArrayList<>();
                    notes.add(value);

                    notesLiveData.setValue(notes);
                }


            }

            @Override
            public void onError(Throwable error) {

            }
        });
/*        Note notes = repository.addNote();
        noteAddedLiveData.setValue(notes);*/

    }

    public LiveData<Note> getNoteAddedLiveData() {
        return noteAddedLiveData;


    }


    public void deleteClicked(int longClickedPosition) {
/*        repository.removeAtPosition(longClickedPosition);

        noteDeletedLiveData.setValue(longClickedPosition);*/
    }

    public void requestNotes() {

        repository.getNotes(new Callback<List<Note>>() {
            @Override
            public void onSuccess(List<Note> value) {
                notesLiveData.setValue(value);
            }

            @Override
            public void onError(Throwable error) {

            }
        });
/*        List<Note> notes = repository.getNotes();

        notesLiveData.setValue(notes);*/
    }

    public LiveData<Integer> getNoteDeletedLiveData() {
        return noteDeletedLiveData;
    }


}