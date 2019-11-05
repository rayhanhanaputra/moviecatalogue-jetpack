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
import com.rayhanhanaputra.moviecataloguevm.data.Tvshow;
import com.rayhanhanaputra.moviecataloguevm.utils.CustomOnItemClickListener;

public class TvshowPagedAdapter extends PagedListAdapter<Tvshow, TvshowPagedAdapter.TvshowViewHolder> {

    private static DiffUtil.ItemCallback<Tvshow> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Tvshow>() {
                @Override
                public boolean areItemsTheSame(Tvshow oldMovie, Tvshow newMovie) {
                    return oldMovie.getTitle().equals(newMovie.getTitle());
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(Tvshow oldMovie, @NonNull Tvshow newMovie) {
                    return oldMovie.equals(newMovie);
                }
            };
    private final Activity activity;

    public TvshowPagedAdapter(Activity activity) {
        super(DIFF_CALLBACK);
        this.activity = activity;
    }

    @NonNull
    @Override
    public TvshowPagedAdapter.TvshowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview, parent, false);
        return new TvshowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvshowPagedAdapter.TvshowViewHolder holder, int position) {
        final Tvshow tvshow = getItem(position);
        holder.tvTitle.setText(tvshow.getTitle());
        holder.tvOverview.setText(tvshow.getOverview());
        String year = tvshow.getReleaseDate().substring(0, 4);
        holder.tvRating.setText(year);

        Glide.with(holder.imgPoster.getContext())
                .load("https://image.tmdb.org/t/p/w185" + tvshow.getPhotoLink())
                .into(holder.imgPoster);

        holder.itemView.setOnClickListener(new CustomOnItemClickListener(position, (view, position1) -> {
            Movie intentMovie = new Movie();
            intentMovie.setId(tvshow.getId());
            intentMovie.setTitle(tvshow.getTitle());
            intentMovie.setOverview(tvshow.getOverview());
            intentMovie.setReleaseDate(tvshow.getReleaseDate());
            intentMovie.setRating(tvshow.getRating());
            intentMovie.setPhotoLink(tvshow.getPhotoLink());
            intentMovie.setType("tv");

            Intent intent = new Intent(this.activity, DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_DETAIL, intentMovie);
            this.activity.startActivity(intent);
        }));
    }

    public class TvshowViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        TextView tvTitle;
        TextView tvOverview;
        TextView tvRating;
        ImageView imgPoster;

        public TvshowViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            tvTitle = mView.findViewById(R.id.tv_item_title);
            tvOverview = mView.findViewById(R.id.tv_item_description);
            tvRating = mView.findViewById(R.id.tv_item_date);
            imgPoster = mView.findViewById(R.id.img_poster);
        }
    }
}

