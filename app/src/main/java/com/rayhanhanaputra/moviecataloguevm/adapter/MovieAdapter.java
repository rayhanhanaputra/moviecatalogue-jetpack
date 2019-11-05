package com.rayhanhanaputra.moviecataloguevm.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rayhanhanaputra.moviecataloguevm.R;
import com.rayhanhanaputra.moviecataloguevm.activity.DetailActivity;
import com.rayhanhanaputra.moviecataloguevm.data.Movie;
import com.rayhanhanaputra.moviecataloguevm.utils.CustomOnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private ArrayList<Movie> listMovie;
    private Context context;

    public MovieAdapter(Context context, ArrayList<Movie> listMovie, List<Movie> movieList) {
        this.context = context;
        if (listMovie == null) {
            this.listMovie = new ArrayList<Movie>();
            this.listMovie.addAll(movieList);
        } else {
            this.listMovie = listMovie;
        }

    }

    public ArrayList<Movie> getListMovie() {
        return listMovie;
    }

    @NonNull
    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_cardview, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieViewHolder holder, int position) {
        holder.tvTitle.setText(listMovie.get(position).getTitle());
        holder.tvOverview.setText(listMovie.get(position).getOverview());
        String year = listMovie.get(position).getReleaseDate().substring(0, 4);
        holder.tvRating.setText(year);

        Glide.with(holder.imgPoster.getContext())
                .load("https://image.tmdb.org/t/p/w185" + listMovie.get(position).getPhotoLink())
                .into(holder.imgPoster);

        holder.itemView.setOnClickListener(new CustomOnItemClickListener(position, (view, position1) -> {
            Movie movie = getListMovie().get(position1);
            Movie intentMovie = new Movie();
            intentMovie.setId(movie.getId());
            intentMovie.setTitle(movie.getTitle());
            intentMovie.setOverview(movie.getOverview());
            intentMovie.setReleaseDate(movie.getReleaseDate());
            intentMovie.setRating(movie.getRating());
            intentMovie.setPhotoLink(movie.getPhotoLink());
            intentMovie.setType("movie");

            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_DETAIL, intentMovie);
            context.startActivity(intent);
        }));
    }

    @Override
    public int getItemCount() {
        return listMovie.size();
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
