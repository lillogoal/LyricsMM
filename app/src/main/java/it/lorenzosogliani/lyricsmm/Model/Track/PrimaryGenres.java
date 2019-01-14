package it.lorenzosogliani.lyricsmm.Model.Track;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PrimaryGenres implements Parcelable {
    @SerializedName("music_genre_list")
    private List<MusicGenreList> musicGenreList = null;

    protected PrimaryGenres(Parcel in) {
        musicGenreList = in.createTypedArrayList(MusicGenreList.CREATOR);
    }

    public static final Creator<PrimaryGenres> CREATOR = new Creator<PrimaryGenres>() {
        @Override
        public PrimaryGenres createFromParcel(Parcel in) {
            return new PrimaryGenres(in);
        }

        @Override
        public PrimaryGenres[] newArray(int size) {
            return new PrimaryGenres[size];
        }
    };

    public List<MusicGenreList> getMusicGenreList() {
        return musicGenreList;
    }

    public void setMusicGenreList(List<MusicGenreList> musicGenreList) {
        this.musicGenreList = musicGenreList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(musicGenreList);
    }
}
