package ru.geekbrains.notes.domain;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class FirestoreNotesRepository implements NotesRepository {

    private static final String NOTES = "notes";
    private static final String NAME = "name";
    private static final String DESCRIPTION = "descr";
    private static final String DATE = "data";

    private final FirebaseFirestore fireStore = FirebaseFirestore.getInstance();

    @Override
    public void getNotes(Callback<List<Note>> callback) {
        fireStore.collection(NOTES)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            ArrayList<Note> tmp = new ArrayList<>();
                            List<DocumentSnapshot> docs = task.getResult().getDocuments();

                            for (DocumentSnapshot doc : docs) {
                                String id = doc.getId();

                                String name = doc.getString(NAME);
                                String description = doc.getString(DESCRIPTION);
                                Date date = doc.getDate(DATE);

                                tmp.add(new Note(name, description, date));

                            }
                            callback.onSuccess(tmp);

                        } else {
                            callback.onError(task.getException());
                        }
                    }
                });
    }

    @Override
    public void addNote(String name, String description, Callback<Note> callback) {

        HashMap<String, Object> data = new HashMap<>();

        Date date = new Date();

        data.put(NAME, name);
        data.put(DESCRIPTION, description);
        data.put(DATE, date);

        fireStore.collection(NOTES)
                .add(data)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if (task.isSuccessful()) {
                            callback.onSuccess(new Note(task.getResult().getId(), name, date));

                        } else {
                            callback.onError(task.getException());
                        }

                    }
                });
    }

    @Override
    public void remove(Note item, Callback<Object> callback) {

        fireStore.collection(NOTES)
                .document(item.getNameNotes())
                .delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            callback.onSuccess(new Object());
                        } else {
                            callback.onError(task.getException());
                        }
                    }
                });
    }
}
