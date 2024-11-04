package com.example.poster;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * PosterAdapter is responsible for providing views that represent
 * the poster items in a RecyclerView.
 */
public class PosterAdapter extends RecyclerView.Adapter<PosterAdapter.PosterViewHolder>{

    private List<Poster> posterList;
    private PostersListener postersListener;

    /**
     * Constructs a PosterAdapter with the given list of posters and listener.
     *
     * @param posterList       The list of posters to display.
     * @param postersListener  The listener for poster actions.
     */
    public PosterAdapter(List<Poster> posterList, PostersListener postersListener) {
        this.posterList = posterList;
        this.postersListener = postersListener;
    }

    @NonNull
    @Override
    public PosterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for a poster item
        return new PosterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_poster, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PosterViewHolder holder, int position) {
        // Bind the poster data to the ViewHolder
        holder.bindPoster(posterList.get(position));
    }

    @Override
    public int getItemCount() {
        return posterList.size();
    }

    /**
     * Returns a list of selected posters.
     *
     * @return List of selected posters.
     */
    public List<Poster> getSelectedPosters() {
        List<Poster> selectedPosters = new ArrayList<>();
        for (Poster poster : this.posterList) {
            if (poster.isSelected) {
                selectedPosters.add(poster);
            }
        }
        return selectedPosters;
    }


    /**
     * Returns a list of selected posters.
     *
     * @return List of selected posters.
     */
    class PosterViewHolder extends RecyclerView.ViewHolder{

        ConstraintLayout layoutPosters;
        View viewBackground;
        RoundedImageView imagePoster;
        TextView textName, textCreatedBy, textStory;
        RatingBar ratingBar;
        ImageView imageSelected;
        public PosterViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutPosters = itemView.findViewById(R.id.layoutPoster);
            viewBackground = itemView.findViewById(R.id.viewBackground);
            imagePoster = itemView.findViewById(R.id.imagePosters);
            textName = itemView.findViewById(R.id.textName);
            textCreatedBy = itemView.findViewById(R.id.textCreateBy);
            textStory = itemView.findViewById(R.id.textStory);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            imageSelected = itemView.findViewById(R.id.imageSelected);

        }

        /**
         * Returns a list of selected posters.
         *
         * @return List of selected posters.
         */
        void bindPoster(final Poster poster){
            imagePoster.setImageResource(poster.image);
            textName.setText(poster.name);
            textCreatedBy.setText(poster.createdBy);
            textStory.setText(poster.story);
            ratingBar.setRating(poster.rating);
            if(poster.isSelected){
                viewBackground.setBackgroundResource(R.drawable.poster_selected_background);
                imageSelected.setVisibility(View.VISIBLE);
            } else {
                viewBackground.setBackgroundResource(R.drawable.poster_background);
                imageSelected.setVisibility(View.GONE);
            }
            // Set click listener to select or deselect the poster
            layoutPosters.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (poster.isSelected) {
                        viewBackground.setBackgroundResource(R.drawable.poster_background);
                        imageSelected.setVisibility(View.GONE);
                        poster.isSelected = false;
                        if (getSelectedPosters().isEmpty()) {
                            postersListener.onPosterAction(false);
                        }
                    } else {
                        viewBackground.setBackgroundResource(R.drawable.poster_selected_background);
                        imageSelected.setVisibility(View.VISIBLE);
                        poster.isSelected = true;
                        postersListener.onPosterAction(true);
                    }
                }

            });
        }
    }
}
