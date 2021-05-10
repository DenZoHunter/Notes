package ru.geekbrains.notes.domain;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.geekbrains.notes.R;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    private final ArrayList<Notes> data = new ArrayList<>();

    public void setData(List<Notes> toAdd) {

        data.addAll(toAdd);
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notes, parent, false);
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {

        Notes notes = data.get(position);
        holder.name.setText(notes.getNameNotes());
        holder.date.setText(notes.getDateNotes());
        holder.descr.setText(notes.getDescriptionNotes());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class NotesViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView date;
        TextView descr;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.notes_name);
            date = itemView.findViewById(R.id.notes_date);
            descr = itemView.findViewById(R.id.notes_description);
        }
    }
}


