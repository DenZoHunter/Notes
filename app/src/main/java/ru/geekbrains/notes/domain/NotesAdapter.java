package ru.geekbrains.notes.domain;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.geekbrains.notes.R;


public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    private OnNoteClicked clickListener;
    private final ArrayList<Note> data = new ArrayList<>();

    private final Fragment fragment;

    private int longClickedPosition = -1;

    public NotesAdapter(Fragment fragment) {
        this.fragment = fragment;
    }

    public int getLongClickedPosition() {
        return longClickedPosition;
    }



    public interface OnNoteClicked {
        void onNoteClicked(Note note);
    }

    public OnNoteClicked getClickListener() {
        return clickListener;
    }

    public void setClickListener(OnNoteClicked clickListener) {
        this.clickListener = clickListener;
    }


    public int addData(Note notes) {

        data.add(notes);
        int position = data.size()-1;
        notifyDataSetChanged();
        return position;
    }

    public void setData(List<Note> toAdd) {

        NotesDiffUtilCallback callback = new NotesDiffUtilCallback(data, toAdd);

        DiffUtil.DiffResult result = DiffUtil.calculateDiff(callback);

        data.clear();
        data.addAll(toAdd);

        result.dispatchUpdatesTo(this);
    }

    public void delete(int index) {
        data.remove(index);
        notifyItemRemoved(index);
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notes, parent, false);
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {

        Note notes = data.get(position);
        holder.name.setText(notes.getNameNotes());
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
            fragment.registerForContextMenu(itemView);

            name = itemView.findViewById(R.id.notes_name);
            date = itemView.findViewById(R.id.notes_date);
            descr = itemView.findViewById(R.id.notes_description);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getClickListener().onNoteClicked(data.get(getAdapterPosition()));
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    itemView.showContextMenu();
                    longClickedPosition = getAdapterPosition();
                    return true;
                }
            });
        }
    }


    public static class NotesDiffUtilCallback extends DiffUtil.Callback {

        private final List<Note> oldList;
        private final List<Note> newList;

        public NotesDiffUtilCallback(List<Note> oldList, List<Note> newList) {
            this.oldList = oldList;
            this.newList = newList;
        }

        @Override
        public int getOldListSize() {
            return oldList.size();
        }

        @Override
        public int getNewListSize() {
            return newList.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return oldList.get(oldItemPosition).getNameNotes().equals(newList.get(newItemPosition).getNameNotes());
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
        }
    }
}


