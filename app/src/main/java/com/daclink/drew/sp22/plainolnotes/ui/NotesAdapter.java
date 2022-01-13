package com.daclink.drew.sp22.plainolnotes.ui;

import android.content.Context;
import android.content.Intent;
import android.provider.SyncStateContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.daclink.drew.sp22.plainolnotes.EditorActivity;
import com.daclink.drew.sp22.plainolnotes.databinding.NoteListItemBinding;
import com.daclink.drew.sp22.plainolnotes.database.NoteEntity;
import com.daclink.drew.sp22.plainolnotes.utilities.Constants;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

//import butterknife.BindView;
//import butterknife.ButterKnife;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    private final List<NoteEntity> mNotes;
    private final Context mContext;

    private NoteListItemBinding noteBinding;

    public NotesAdapter(List<NoteEntity> notes, Context context) {
        mNotes = notes;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Called whenever new viewholder is created generates an instance of the custom viewholder class below

        //TODO: Why parent.getContext?
//        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        //TODO:What are the parameters?
//        View view = inflater.inflate(R.layout.note_list_item, parent, false);

        NoteListItemBinding noteBinding = NoteListItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);

        return new ViewHolder(noteBinding);

//        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //called when ever update the display of the list item
        //each time a row is refreshed with a data object

        final NoteEntity note = mNotes.get(position);
        holder.mTextView.setText(note.getText());

        holder.mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, EditorActivity.class);
                intent.putExtra(Constants.NOTE_ID_KEY,note.getId());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        //returns total number of items in the view
        return mNotes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //manages the view itself must contain ref for any components.

        //        @BindView(R.id.note_text)
//        NoteListItemBinding noteBinding;
        TextView mTextView;// = noteBinding.noteText;;

        //getting the FAB next to the note entity
        FloatingActionButton mFab;

        //        public ViewHolder(@NonNull View itemView) {
        public ViewHolder(NoteListItemBinding binding) {
            super(binding.getRoot());
//            ButterKnife.bind(this,itemView);
//            noteBinding = binding;
            mTextView = binding.noteText;
            mFab = binding.noteFab;
        }
    }
}
