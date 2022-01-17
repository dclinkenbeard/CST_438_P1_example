package com.daclink.drew.sp22.plainolnotes.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.daclink.drew.sp22.plainolnotes.R;
import com.daclink.drew.sp22.plainolnotes.databinding.BookItemBinding;
import com.daclink.drew.sp22.plainolnotes.model.Volume;
import com.daclink.drew.sp22.plainolnotes.utilities.Util;

import java.util.ArrayList;
import java.util.List;

public class BookSearchResultsAdapter extends RecyclerView.Adapter<BookSearchResultsHolder> {
private List<Volume> results = new ArrayList<>();


    private BookItemBinding binding;

    @NonNull
    @Override
    public BookSearchResultsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_item,parent,false);
        return new BookSearchResultsHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BookSearchResultsHolder holder, int position) {
        Volume volume = results.get(position);

        holder.titleTextview.setText(volume.getVolumeInfo().getTitle());
        holder.publishedDateTextView.setText(volume.getVolumeInfo().getPublishedDate());

        if(volume.getVolumeInfo().getImageLinks() != null){
            String imgUrl = volume.getVolumeInfo().getImageLinks().getSmallThumbnail()
                    .replace("http://","https://");
            Glide.with(holder.itemView)
                    .load(imgUrl)
                    .into(holder.smallThumbnailImageView);
        }

        if (volume.getVolumeInfo().getAuthors() != null){
            String authors = Util.StringJoin(volume.getVolumeInfo().getAuthors(), ", ");
            holder.authorsTextView.setText(authors);
        }

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public void setResults(List<Volume> results){
        this.results = results;
        notifyDataSetChanged();
    }
}

class BookSearchResultsHolder extends RecyclerView.ViewHolder{
    final TextView titleTextview;
    final TextView authorsTextView;
    final TextView publishedDateTextView;
    final ImageView smallThumbnailImageView;

    public BookSearchResultsHolder(@NonNull View itemView) {
        //TODO: translate this to use viewBindings later...
//        public BookSearchResultsHolder(@NonNull BookItemBinding bookBinding) {
//        super(bookBinding.getRoot());
        super(itemView);

        titleTextview = itemView.findViewById(R.id.book_item_title);
        authorsTextView = itemView.findViewById(R.id.book_item_author);
        publishedDateTextView = itemView.findViewById(R.id.book_item_publish_date);
        smallThumbnailImageView = itemView.findViewById(R.id.book_item_smallThumbnail);
    }
}