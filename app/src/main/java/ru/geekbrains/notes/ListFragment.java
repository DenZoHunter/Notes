package ru.geekbrains.notes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import android.widget.TextView;

import java.util.List;

import ru.geekbrains.notes.domain.Notes;
import ru.geekbrains.notes.domain.NotesRepository;


public class ListFragment extends Fragment {

    public ListFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<Notes> notes = new NotesRepository().getNotes();

        LinearLayout notesList = (LinearLayout) view;

        for (Notes item : notes) {

            View noteView = LayoutInflater.from(requireContext()).inflate(R.layout.item_notes, notesList, false);

            TextView name = noteView.findViewById(R.id.notes_name);
            name.setText(item.getNameNotes());

            TextView date = noteView.findViewById(R.id.notes_date);
            date.setText(item.getDateNotes());

            TextView description = noteView.findViewById(R.id.notes_description);
            description.setText(item.getDescriptionNotes());

            notesList.addView(noteView);
        }
    }
}