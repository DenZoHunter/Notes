package ru.geekbrains.notes.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import ru.geekbrains.notes.R;
import ru.geekbrains.notes.domain.MockNotesRepository;
import ru.geekbrains.notes.domain.Notes;
import ru.geekbrains.notes.domain.NotesAdapter;
import ru.geekbrains.notes.domain.NotesListViewModel;
import ru.geekbrains.notes.edit.EditNoteFragment;


public class ListFragment extends Fragment {

    private NotesListViewModel viewModel;
    private NotesAdapter adapter;
    private EditNoteFragment editNoteFragment;


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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(this).get(NotesListViewModel.class);
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
        RecyclerView.LayoutManager lm = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);

        recyclerList.setLayoutManager(lm);

        adapter = new NotesAdapter(this);
        recyclerList.setAdapter(adapter);
        adapter.setData(notesis);
        adapter.notifyDataSetChanged();

        adapter.setClickListener(new NotesAdapter.OnNoteClicked() {
            @Override
            public void onNoteClicked(Notes note) {
                openNotesDetail(note);
            }
        });

        Toolbar toolbar = view.findViewById(R.id.toolbar);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_add) {
                    viewModel.addClicked();
                    return true;
                }
                return false;
            }
        });

        viewModel.getNoteAddedLiveData().observe(getViewLifecycleOwner(), new Observer<Notes>() {
            @Override
            public void onChanged(Notes notes) {
                adapter.addData(notes);
                adapter.notifyDataSetChanged();
            }
        });

        viewModel.getNoteDeletedLiveData().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer position) {
                adapter.delete(position);
            }
        });



        viewModel.getNotesLiveData().observe(getViewLifecycleOwner(), new Observer<List<Notes>>() {
            @Override
            public void onChanged(List<Notes> notes) {
                adapter.setData(notes);
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

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        requireActivity().getMenuInflater().inflate(R.menu.menu_list_context,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_open) {
            Toast.makeText(requireContext(), "Открыли", Toast.LENGTH_SHORT).show();

            return true;
        }

        if (item.getItemId() == R.id.action_update) {

            Toast.makeText(requireContext(), "Обновили", Toast.LENGTH_SHORT).show();

            return true;
        }

        if (item.getItemId() == R.id.action_delete) {
            viewModel.deleteClicked(adapter.getLongClickedPosition());
            Toast.makeText(requireContext(), "Удалили", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onContextItemSelected(item);
    }
}