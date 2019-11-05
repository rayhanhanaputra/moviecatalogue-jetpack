package com.rayhanhanaputra.moviecataloguevm.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rayhanhanaputra.moviecataloguevm.R;
import com.rayhanhanaputra.moviecataloguevm.activity.DetailActivity;
import com.rayhanhanaputra.moviecataloguevm.data.Movie;
import com.rayhanhanaputra.moviecataloguevm.utils.CustomOnItemClickListener;

public class MoviePagedAdapter extends PagedListAdapter<Movie, MoviePagedAdapter.MovieViewHolder> {

    private static DiffUtil.ItemCallback<Movie> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Movie>() {
                @Override
                public boolean areItemsTheSame(Movie oldMovie, Movie newMovie) {
                    return oldMovie.getTitle().equals(newMovie.getTitle());
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(Movie oldMovie, @NonNull Movie newMovie) {
                    return oldMovie.equals(newMovie);
                }
            };
    private final Activity activity;

    public MoviePagedAdapter(Activity activity) {
        super(DIFF_CALLBACK);
        this.activity = activity;
    }

    @NonNull
    @Override
    public MoviePagedAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviePagedAdapter.MovieViewHolder holder, int position) {
        final Movie movie = getItem(position);
        holder.tvTitle.setText(movie.getTitle());
        holder.tvOverview.setText(movie.getOverview());
        String year = movie.getReleaseDate().substring(0, 4);
        holder.tvRating.setText(year);

        Glide.with(holder.imgPoster.getContext())
                .load("https://image.tmdb.org/t/p/w185" + movie.getPhotoLink())
                .into(holder.imgPoster);

        holder.itemView.setOnClickListener(new CustomOnItemClickListener(position, (view, position1) -> {
            Movie intentMovie = new Movie();
            intentMovie.setId(movie.getId());
            intentMovie.setTitle(movie.getTitle());
            intentMovie.setOverview(movie.getOverview());
            intentMovie.setReleaseDate(movie.getReleaseDate());
            intentMovie.setRating(movie.getRating());
            intentMovie.setPhotoLink(movie.getPhotoLink());
            intentMovie.setType("movie");

            Intent intent = new Intent(this.activity, DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_DETAIL, intentMovie);
            this.activity.startActivity(intent);
        }));
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        TextView tvTitle;
        TextView tvOverview;
        TextView tvRating;
        ImageView imgPoster;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            tvTitle = mView.findViewById(R.id.tv_item_title);
            tvOverview = mView.findViewById(R.id.tv_item_description);
            tvRating = mView.findViewById(R.id.tv_item_date);
            imgPoster = mView.findViewById(R.id.img_poster);
        }
    }
}
