package ru.geekbrains.notes;

import android.content.Context;
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

    public interface OnNoteClicked {
        void onNoteClicked(Notes notes);
    }

    private OnNoteClicked onNoteClicked;

    public ListFragment() {

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof OnNoteClicked) {
            onNoteClicked = (OnNoteClicked) context;
        }
    }

    @Override
    public void onDetach() {
        onNoteClicked = null;
        super.onDetach();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<Notes> notesis = new NotesRepository().getNotes();

        LinearLayout notesList = view.findViewById(R.id.notes_list);

        for (Notes notes : notesis) {

            View notesView = LayoutInflater.from(requireContext()).inflate(R.layout.item_notes, notesList, false);

            notesView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openNotesDetail(notes);
                }
            });

            TextView name = notesView.findViewById(R.id.notes_name);
            name.setText(notes.getNameNotes());

            TextView date = notesView.findViewById(R.id.notes_date);
            date.setText(notes.getDateNotes());

            TextView description = notesView.findViewById(R.id.notes_description);
            description.setText(notes.getDescriptionNotes());

            notesList.addView(notesView);
        }
    }

    private void openNotesDetail(Notes notes) {
        if (getActivity() instanceof PublisherHolder) {
            PublisherHolder holder = (PublisherHolder) getActivity();

            holder.getPublisher().notify(notes);
        }

        if (onNoteClicked != null) {
            onNoteClicked.onNoteClicked(notes);
        }
    }
}