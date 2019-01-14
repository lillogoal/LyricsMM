package it.lorenzosogliani.lyricsmm.Model.Track;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SecondaryGenres implements Parcelable {
    @SerializedName("music_genre_list")
    private List<MusicGenreList> musicGenreList = null;

    protected SecondaryGenres(Parcel in) {
        musicGenreList = in.createTypedArrayList(MusicGenreList.CREATOR);
    }

    public static final Creator<SecondaryGenres> CREATOR = new Creator<SecondaryGenres>() {
        @Override
        public SecondaryGenres createFromParcel(Parcel in) {
            return new SecondaryGenres(in);
        }

        @Override
        public SecondaryGenres[] newArray(int size) {
            return new SecondaryGenres[size];
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
