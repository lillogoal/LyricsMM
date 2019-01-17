package it.lorenzosogliani.lyricsmm.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.signature.StringSignature;

import java.util.ArrayList;
import java.util.Collection;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.lorenzosogliani.lyricsmm.Activities.DetailTrackActivity;
import it.lorenzosogliani.lyricsmm.Model.Track.Track;
import it.lorenzosogliani.lyricsmm.R;

/**
 * Created by Lorenzo on 28/04/2017 22:26
 * Organizazion: LilloNet
 * WebSite: http://www.lillonet.com
 */

public class TracksListAdapter extends RecyclerView.Adapter<TracksListAdapter.ViewHolder>{
    private final Activity mContext;
    ArrayList<Track> trackData;

    public void clear() {
        trackData.clear();
    }

    public void addAll(Collection<Track> dishes) {
        trackData.addAll(dishes);
    }

    public void setTrackData(ArrayList<Track> usersList) {
        this.trackData = usersList;
        this.notifyDataSetChanged();
    }

    public TracksListAdapter(Activity context) {
        this(context, new ArrayList<Track>());
    }

    public TracksListAdapter(Activity context, ArrayList<Track> trackData) {
        mContext = context;
        this.trackData = trackData;
    }

    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Track getItem(int position) {
        return trackData.get(position);
    }

    @Override
    public int getItemCount() {
        return trackData.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {
        int layout = R.layout.adapter_trackslist;
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(layout, viewGroup, false);
        final ViewHolder vh = new ViewHolder(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int position = vh.getAdapterPosition();
                Track track = trackData.get(position);
                Intent intent = new Intent(mContext,DetailTrackActivity.class);
                intent.putExtra("track",track);
                mContext.startActivity(intent);
            }
        });
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        Track track = trackData.get(i);

        viewHolder.txt_title.setText(track.getTrack().getTrackName());
        viewHolder.txt_album.setText(track.getTrack().getAlbumName());
        viewHolder.txt_artist.setText(track.getTrack().getArtistName());
        Glide.with(mContext).load(track.getTrack()
                .getAlbumCoverart500x500())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .crossFade()
                .signature(new StringSignature(String.valueOf(track.getTrack().getAlbumId()+track.getTrack().getArtistName())))
                .into(viewHolder.imgAlbumCover);

    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.txt_title)
        TextView txt_title;

        @BindView(R.id.txt_artist)
        TextView txt_artist;

        @BindView(R.id.txt_album)
        TextView txt_album;

        @BindView(R.id.imgAlbumCover)
        ImageView imgAlbumCover;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}