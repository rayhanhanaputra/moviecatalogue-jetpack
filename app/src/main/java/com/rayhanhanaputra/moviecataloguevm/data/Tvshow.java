package com.rayhanhanaputra.moviecataloguevm.data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "tvshow")
public class Tvshow implements Parcelable {
    public static final Parcelable.Creator<Tvshow> CREATOR = new Parcelable.Creator<Tvshow>() {
        @Override
        public Tvshow createFromParcel(Parcel source) {
            return new Tvshow(source);
        }

        @Override
        public Tvshow[] newArray(int size) {
            return new Tvshow[size];
        }
    };
    @PrimaryKey

    @ColumnInfo(name = "tvshowId")
    @SerializedName("id")
    private int id;
    @ColumnInfo(name = "tvshowName")
    @SerializedName("name")
    private String title;
    @ColumnInfo(name = "tvshowOriginalTitle")
    @SerializedName("original_name")
    private String originalTitle;
    @ColumnInfo(name = "tvshowVoteAverage")
    @SerializedName("vote_average")
    private String rating;
    @ColumnInfo(name = "tvshowReleaseDate")
    @SerializedName("first_air_date")
    private String releaseDate;
    @ColumnInfo(name = "tvshowOverview")
    @SerializedName("overview")
    private String overview;
    @ColumnInfo(name = "tvshowPosterPath")
    @SerializedName("poster_path")
    private String photoLink;
    private String type;

    public Tvshow() {

    }

    @Ignore
    public Tvshow(int id, String title, String originalTitle, String rating, String releaseDate, String overview, String photoLink, String type) {
        this.id = id;
        this.title = title;
        this.originalTitle = originalTitle;
        this.rating = rating;
        this.releaseDate = releaseDate;
        this.overview = overview;
        this.photoLink = photoLink;
        this.type = type;
    }

    protected Tvshow(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.originalTitle = in.readString();
        this.rating = in.readString();
        this.releaseDate = in.readString();
        this.overview = in.readString();
        this.photoLink = in.readString();
        this.type = in.readString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.originalTitle);
        dest.writeString(this.rating);
        dest.writeString(this.releaseDate);
        dest.writeString(this.overview);
        dest.writeString(this.photoLink);
        dest.writeString(this.type);
    }
}
