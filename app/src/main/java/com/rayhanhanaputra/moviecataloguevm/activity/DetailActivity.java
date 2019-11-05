package com.rayhanhanaputra.moviecataloguevm.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.rayhanhanaputra.moviecataloguevm.R;
import com.rayhanhanaputra.moviecataloguevm.activity.ui.main.PageViewModel;
import com.rayhanhanaputra.moviecataloguevm.activity.ui.main.PageViewModel2;
import com.rayhanhanaputra.moviecataloguevm.data.Movie;
import com.rayhanhanaputra.moviecataloguevm.data.Tvshow;
import com.rayhanhanaputra.moviecataloguevm.utils.EspressoIdlingResource;
import com.rayhanhanaputra.moviecataloguevm.viewmodel.DetailViewModel;
import com.rayhanhanaputra.moviecataloguevm.viewmodel.ViewModelFactoryDetail;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_DETAIL = "DetailIntent";
    private DetailViewModel viewModel;
    private TextView tvTitle, tvDate, tvRating, tvOverview;
    private ImageView imgPoster;
    private String type;
    private MenuItem menuItem;
    private PageViewModel pageViewModel;
    private PageViewModel2 pageViewModel2;
    private Movie movie;
    private Boolean isFavorite = false;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        menuItem = menu.getItem(0);
        menuItem.setVisible(false);

        movie = getIntent().getParcelableExtra(EXTRA_DETAIL);
        ViewModelFactoryDetail factory = ViewModelFactoryDetail.getInstance(getApplication());
        EspressoIdlingResource.increment();
        viewModel = ViewModelProviders.of(this, factory).get(DetailViewModel.class);
        if (movie.getType().equals("movie")) {
            viewModel.checkMovie(movie.getId()).observe(this, movieList -> {
                if (movieList.size() > 0) {
                    isFavorite = true;
                } else {
                    movieList.size();
                    isFavorite = false;
                }
                setFavorite(isFavorite);
                menuItem.setVisible(true);
            });
        } else {
            viewModel.checkTvshow(movie.getId()).observe(this, tvshowList -> {
                if (tvshowList.size() > 0) {
                    isFavorite = true;
                } else {
                    tvshowList.size();
                    isFavorite = false;
                }
                setFavorite(isFavorite);
                menuItem.setVisible(true);
            });
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        } else if (item.getItemId() == R.id.add_to_favorite_button) {
            if (!isFavorite) {
                if (type.equals("movie")) {
                    pageViewModel.insert(movie);
                    Snackbar.make(findViewById(R.id.view_detail), "Added to Favorite", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    setFavorite(true);
                } else {
                    Tvshow tv = new Tvshow();
                    tv.setId(movie.getId());
                    tv.setTitle(movie.getTitle());
                    tv.setOverview(movie.getOverview());
                    tv.setReleaseDate(movie.getReleaseDate());
                    tv.setRating(movie.getRating());
                    tv.setPhotoLink(movie.getPhotoLink());
                    tv.setType("tv");
                    pageViewModel2.insert2(tv);
                    Snackbar.make(findViewById(R.id.view_detail), "Added to Favorite", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    setFavorite(true);
                }
            } else {
                if (type.equals("movie")) {
                    pageViewModel.delete(movie);
                    Snackbar.make(findViewById(R.id.view_detail), "Removed from Favorite", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    setFavorite(false);
                } else {
                    Tvshow tv = new Tvshow();
                    tv.setId(movie.getId());
                    tv.setTitle(movie.getTitle());
                    tv.setOverview(movie.getOverview());
                    tv.setReleaseDate(movie.getReleaseDate());
                    tv.setRating(movie.getRating());
                    tv.setPhotoLink(movie.getPhotoLink());
                    tv.setType("tv");
                    pageViewModel2.delete2(tv);
                    Snackbar.make(findViewById(R.id.view_detail), "Removed from Favorite", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    setFavorite(false);
                }
            }

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        pageViewModel = new PageViewModel(this.getApplication());
        pageViewModel2 = new PageViewModel2(this.getApplication());

        movie = getIntent().getParcelableExtra(EXTRA_DETAIL);
        getSupportActionBar().setTitle(movie.getTitle());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        type = movie.getType();
        String id = String.valueOf(movie.getId());

        tvTitle = findViewById(R.id.tv_title);
        tvDate = findViewById(R.id.tv_date);
        tvRating = findViewById(R.id.tv_rating);
        tvRating.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_star_yellow_24dp, 0, 0, 0);
        tvOverview = findViewById(R.id.tv_overview);
        imgPoster = findViewById(R.id.img_poster_detail);
        ProgressBar progressBar = findViewById(R.id.progressBar_detail);
        setVisibility(false);

        progressBar.setVisibility(View.VISIBLE);

        ViewModelFactoryDetail factory = ViewModelFactoryDetail.getInstance(getApplication());
        viewModel = ViewModelProviders.of(this, factory).get(DetailViewModel.class);

        switch (type) {
            case "tv":
                viewModel.getDetail2(type, id).observe(this, movie1 -> {
                    tvTitle.setText(movie1.getTitle());
                    String year = movie1.getReleaseDate().substring(0, 4);
                    tvDate.setText(year);
                    tvRating.setText(movie1.getRating());
                    tvOverview.setText(movie1.getOverview());
                    Glide.with(getApplicationContext())
                            .load("https://image.tmdb.org/t/p/w185" + movie1.getPhotoLink())
                            .into(imgPoster);
                    progressBar.setVisibility(View.GONE);
                    setVisibility(true);
                    EspressoIdlingResource.decrement();
                });
                break;
            default:
                viewModel.getDetail(type, id).observe(this, movie1 -> {
                    tvTitle.setText(movie1.getTitle());
                    String year = movie1.getReleaseDate().substring(0, 4);
                    tvDate.setText(year);
                    tvRating.setText(movie1.getRating());
                    tvOverview.setText(movie1.getOverview());
                    Glide.with(getApplicationContext())
                            .load("https://image.tmdb.org/t/p/w185" + movie1.getPhotoLink())
                            .into(imgPoster);
                    progressBar.setVisibility(View.GONE);
                    setVisibility(true);
                    EspressoIdlingResource.decrement();
                });
                break;

        }
    }

    private void setFavorite(Boolean b) {
        if (!b) {
            menuItem.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_favorite_border_black_24dp));
            isFavorite = false;
        } else {
            menuItem.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_favorite_black_24dp));
            isFavorite = true;
        }
    }

    private void setVisibility(boolean b) {
        if (b) {
            tvTitle.setVisibility(View.VISIBLE);
            tvDate.setVisibility(View.VISIBLE);
            tvOverview.setVisibility(View.VISIBLE);
            tvRating.setVisibility(View.VISIBLE);
            imgPoster.setVisibility(View.VISIBLE);
        } else {
            tvTitle.setVisibility(View.GONE);
            tvDate.setVisibility(View.GONE);
            tvOverview.setVisibility(View.GONE);
            tvRating.setVisibility(View.GONE);
            imgPoster.setVisibility(View.GONE);
        }
    }
}