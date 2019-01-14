package it.lorenzosogliani.lyricsmm.Model.Track;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class MusicGenreList implements Parcelable {
    @SerializedName("music_genre")
    private MusicGenre musicGenre;

    protected MusicGenreList(Parcel in) {
        musicGenre = in.readParcelable(MusicGenre.class.getClassLoader());
    }

    public static final Creator<MusicGenreList> CREATOR = new Creator<MusicGenreList>() {
        @Override
        public MusicGenreList createFromParcel(Parcel in) {
            return new MusicGenreList(in);
        }

        @Override
        public MusicGenreList[] newArray(int size) {
            return new MusicGenreList[size];
        }
    };

    public MusicGenre getMusicGenre() {
        return musicGenre;
    }

    public void setMusicGenre(MusicGenre musicGenre) {
        this.musicGenre = musicGenre;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(musicGenre, flags);
    }
}
