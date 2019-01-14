package it.lorenzosogliani.lyricsmm.Model.Track;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * A class to denote the track entity.
 *
 * @author Sachin Handiekar
 * @version 1.0
 */
public class Track implements Parcelable {
    @SerializedName("track")
    private TrackData track;

    protected Track(Parcel in) {
        track = in.readParcelable(TrackData.class.getClassLoader());
    }

    public static final Creator<Track> CREATOR = new Creator<Track>() {
        @Override
        public Track createFromParcel(Parcel in) {
            return new Track(in);
        }

        @Override
        public Track[] newArray(int size) {
            return new Track[size];
        }
    };

    public void setTrack(TrackData track) {
        this.track = track;
    }

    public TrackData getTrack() {
        return track;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(track, flags);
    }
}
