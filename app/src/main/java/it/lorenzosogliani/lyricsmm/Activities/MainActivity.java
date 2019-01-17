package it.lorenzosogliani.lyricsmm.Activities;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import it.lorenzosogliani.lyricsmm.Adapters.TracksListAdapter;
import it.lorenzosogliani.lyricsmm.Model.Track.Track;
import it.lorenzosogliani.lyricsmm.Networks.ApiRest;
import it.lorenzosogliani.lyricsmm.R;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    ArrayList<Track> arr_track = new ArrayList<>();

    RecyclerView list_track;
    TracksListAdapter tracksListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        loadData();

    }
    public void initUI(){
        list_track = findViewById(R.id.list_track);
        initPullToRefresh();
    }
    SwipeRefreshLayout mSwipeRefreshLayout;
    public void initPullToRefresh(){
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimary);
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }
    public void loadData(){
        ApiRest.getSongChart(new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                mSwipeRefreshLayout.setRefreshing(false);
                Log.e("RESP",response);
                try {
                    arr_track = new ArrayList<>();

                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject jsonObject_message = jsonObject.getJSONObject("message");
                    JSONObject jsonObject_body = jsonObject_message.getJSONObject("body");
                    JSONArray jsonArray = jsonObject_body.getJSONArray("track_list");
                    Gson gson = new Gson();
                    Type listType = new TypeToken<List<Track>>(){}.getType();

                    arr_track = gson.fromJson(jsonArray.toString(), listType);
                    if(arr_track == null) {
                        arr_track = new ArrayList<>();
                    }else{
                        tracksListAdapter = new TracksListAdapter(MainActivity.this,arr_track);
                        list_track.setAdapter(tracksListAdapter);
                        LinearLayoutManager llm = new LinearLayoutManager(MainActivity.this);
                        list_track.setLayoutManager(llm);

                        for(final Track track: arr_track){
                            ApiRest.getAlbumDetail(String.valueOf(track.getTrack().getAlbumId()), new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    JSONObject jsonObject = null;
                                    try {
                                        jsonObject = new JSONObject(response);
                                        JSONObject jsonObject_message = jsonObject.getJSONObject("message");
                                        JSONObject jsonObject_body = jsonObject_message.getJSONObject("body");
                                        JSONObject jsonObject_album = jsonObject_body.getJSONObject("album");

                                        String album_coverart_100x100 = jsonObject_album.getString("album_coverart_100x100");
                                        String album_coverart_350x350 = jsonObject_album.getString("album_coverart_350x350");
                                        String album_coverart_500x500 = jsonObject_album.getString("album_coverart_500x500");
                                        String album_coverart_800x800 = jsonObject_album.getString("album_coverart_800x800");

                                        track.getTrack().setAlbumCoverart100x100(album_coverart_100x100);
                                        track.getTrack().setAlbumCoverart350x350(album_coverart_350x350);
                                        track.getTrack().setAlbumCoverart500x500(album_coverart_500x500);
                                        track.getTrack().setAlbumCoverart800x800(album_coverart_800x800);

                                        tracksListAdapter.setTrackData(arr_track);
                                        tracksListAdapter.notifyDataSetChanged();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() { @Override public void onErrorResponse(VolleyError error) {}});
                        }
                    }




                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onRefresh() {
        loadData();
    }
}
