package it.lorenzosogliani.lyricsmm.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
    ArrayList<Track> usersList;

    public void clear() {
        usersList.clear();
    }

    public void addAll(Collection<Track> dishes) {
        usersList.addAll(dishes);
    }

    public void setUsersList(ArrayList<Track> usersList) {
        this.usersList = usersList;
        this.notifyDataSetChanged();
    }

    public TracksListAdapter(Activity context) {
        this(context, new ArrayList<Track>());
    }

    public TracksListAdapter(Activity context, ArrayList<Track> usersList) {
        mContext = context;
        this.usersList = usersList;
    }

    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Track getItem(int position) {
        return usersList.get(position);
    }

    @Override
    public int getItemCount() {
        return usersList.size();
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
                Track track = usersList.get(position);
                Intent intent = new Intent(mContext,DetailTrackActivity.class);
                intent.putExtra("track",track);
                mContext.startActivity(intent);
            }
        });
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        Track track = usersList.get(i);

        viewHolder.txt_title.setText(track.getTrack().getTrackName());
        viewHolder.txt_album.setText(track.getTrack().getAlbumName());
        viewHolder.txt_artist.setText(track.getTrack().getArtistName());
        //set data
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