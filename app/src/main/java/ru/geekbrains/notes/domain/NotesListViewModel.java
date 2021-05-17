
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

    }

    public LiveData<Note> getNoteAddedLiveData() {
        return noteAddedLiveData;


    }


    public void deleteClicked(Note note) {

        repository.remove(note, new Callback<Object>() {
            @Override
            public void onSuccess(Object value) {

                if (notesLiveData.getValue() != null) {

                    ArrayList<Note> notes = new ArrayList<>(notesLiveData.getValue());

                    notes.remove(note);

                    notesLiveData.setValue(notes);
                }
            }

            @Override
            public void onError(Throwable error) {

            }
        });


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

    }

    public LiveData<Integer> getNoteDeletedLiveData() {
        return noteDeletedLiveData;
    }


}