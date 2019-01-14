package it.lorenzosogliani.lyricsmm.Model.Track;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class MusicGenre  implements Parcelable {
    @SerializedName("music_genre_id")

    private Integer musicGenreId;
    @SerializedName("music_genre_parent_id")

    private Integer musicGenreParentId;
    @SerializedName("music_genre_name")

    private String musicGenreName;
    @SerializedName("music_genre_name_extended")

    private String musicGenreNameExtended;
    @SerializedName("music_genre_vanity")

    private String musicGenreVanity;

    protected MusicGenre(Parcel in) {
        if (in.readByte() == 0) {
            musicGenreId = null;
        } else {
            musicGenreId = in.readInt();
        }
        if (in.readByte() == 0) {
            musicGenreParentId = null;
        } else {
            musicGenreParentId = in.readInt();
        }
        musicGenreName = in.readString();
        musicGenreNameExtended = in.readString();
        musicGenreVanity = in.readString();
    }

    public static final Creator<MusicGenre> CREATOR = new Creator<MusicGenre>() {
        @Override
        public MusicGenre createFromParcel(Parcel in) {
            return new MusicGenre(in);
        }

        @Override
        public MusicGenre[] newArray(int size) {
            return new MusicGenre[size];
        }
    };

    public Integer getMusicGenreId() {
        return musicGenreId;
    }

    public void setMusicGenreId(Integer musicGenreId) {
        this.musicGenreId = musicGenreId;
    }

    public Integer getMusicGenreParentId() {
        return musicGenreParentId;
    }

    public void setMusicGenreParentId(Integer musicGenreParentId) {
        this.musicGenreParentId = musicGenreParentId;
    }

    public String getMusicGenreName() {
        return musicGenreName;
    }

    public void setMusicGenreName(String musicGenreName) {
        this.musicGenreName = musicGenreName;
    }

    public String getMusicGenreNameExtended() {
        return musicGenreNameExtended;
    }

    public void setMusicGenreNameExtended(String musicGenreNameExtended) {
        this.musicGenreNameExtended = musicGenreNameExtended;
    }

    public String getMusicGenreVanity() {
        return musicGenreVanity;
    }

    public void setMusicGenreVanity(String musicGenreVanity) {
        this.musicGenreVanity = musicGenreVanity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (musicGenreId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(musicGenreId);
        }
        if (musicGenreParentId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(musicGenreParentId);
        }
        dest.writeString(musicGenreName);
        dest.writeString(musicGenreNameExtended);
        dest.writeString(musicGenreVanity);
    }
}
