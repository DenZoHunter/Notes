package ru.geekbrains.notes.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ru.geekbrains.notes.R;
import ru.geekbrains.notes.domain.MockNotesRepository;
import ru.geekbrains.notes.domain.Notes;
import ru.geekbrains.notes.domain.NotesAdapter;



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



        List<Notes> notesis = new MockNotesRepository().getNotes();

        RecyclerView recyclerList = view.findViewById(R.id.recyclerList);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false);

        recyclerList.setLayoutManager(lm);

        NotesAdapter adapter = new NotesAdapter();

        recyclerList.setAdapter(adapter);

        adapter.setData(notesis);

        adapter.notifyDataSetChanged();

        adapter.setClickListener(new NotesAdapter.OnNoteClicked() {
            @Override
            public void onNoteClicked(Notes note) {
                openNotesDetail(note);
            }
        });


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