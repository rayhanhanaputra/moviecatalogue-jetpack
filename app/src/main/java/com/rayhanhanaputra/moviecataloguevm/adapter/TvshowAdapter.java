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
import com.rayhanhanaputra.moviecataloguevm.data.Tvshow;
import com.rayhanhanaputra.moviecataloguevm.utils.CustomOnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class TvshowAdapter extends RecyclerView.Adapter<TvshowAdapter.TvshowViewHolder> {
    private ArrayList<Tvshow> listTv;
    private Context context;

    public TvshowAdapter(Context context, ArrayList<Tvshow> listTv, List<Tvshow> list) {
        this.context = context;
        if (listTv == null) {
            this.listTv = new ArrayList<Tvshow>();
            this.listTv.addAll(list);
        } else {
            this.listTv = listTv;
        }
    }

    public ArrayList<Tvshow> getListTv() {
        return listTv;
    }

    @NonNull
    @Override
    public TvshowAdapter.TvshowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_cardview, parent, false);
        return new TvshowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvshowAdapter.TvshowViewHolder holder, int position) {
        holder.tvTitle.setText(listTv.get(position).getTitle());
        holder.tvOverview.setText(listTv.get(position).getOverview());
        String year = listTv.get(position).getReleaseDate().substring(0, 4);
        holder.tvRating.setText(year);

        Glide.with(holder.imgPoster.getContext())
                .load("https://image.tmdb.org/t/p/w185" + listTv.get(position).getPhotoLink())
                .into(holder.imgPoster);

        holder.itemView.setOnClickListener(new CustomOnItemClickListener(position, (view, position1) -> {
            Tvshow tvshow = getListTv().get(position1);
            Movie intentMovie = new Movie();
            intentMovie.setId(tvshow.getId());
            intentMovie.setTitle(tvshow.getTitle());
            intentMovie.setOverview(tvshow.getOverview());
            intentMovie.setReleaseDate(tvshow.getReleaseDate());
            intentMovie.setRating(tvshow.getRating());
            intentMovie.setPhotoLink(tvshow.getPhotoLink());
            intentMovie.setType("tv");

            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_DETAIL, intentMovie);
            context.startActivity(intent);
        }));
    }

    @Override
    public int getItemCount() {
        return listTv.size();
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
