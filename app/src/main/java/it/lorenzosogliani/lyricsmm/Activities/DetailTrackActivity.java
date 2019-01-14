package it.lorenzosogliani.lyricsmm.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

import it.lorenzosogliani.lyricsmm.Model.Lyrics;
import it.lorenzosogliani.lyricsmm.Model.Track.Track;
import it.lorenzosogliani.lyricsmm.Networks.ApiRest;
import it.lorenzosogliani.lyricsmm.R;

public class DetailTrackActivity extends AppCompatActivity {
    //VAR
    Track track;
    Bundle bundle;

    //UI
    TextView txt_title_artista,txt_album,txt_lyrics;
    ImageView img_cover;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_track);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getDataFromExtras();
        initUI();
        loadData();
    }
    public void getDataFromExtras(){
        bundle = getIntent().getExtras();

        if(bundle != null){
            track = bundle.getParcelable("track");
            if(track == null){
                Toast.makeText(this,getString(R.string.si_verificato_un_errore),Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }
    public void initUI(){
        txt_title_artista = findViewById(R.id.txt_title_artista);
        txt_album = findViewById(R.id.txt_album);
        img_cover = findViewById(R.id.img_cover);
        txt_lyrics = findViewById(R.id.txt_lyrics);

    }
    public void loadData(){
        getSupportActionBar().setTitle(track.getTrack().getTrackName());

        txt_title_artista.setText(track.getTrack().getTrackName()+ " "+track.getTrack().getArtistName());
        txt_album.setText(track.getTrack().getAlbumName());

        Log.e("URL","URL: "+track.getTrack().getAlbumCoverart800x800());
        Glide.with(DetailTrackActivity.this).load(track.getTrack().getAlbumCoverart500x500()).into(img_cover);
        ApiRest.getTrackList(String.valueOf(track.getTrack().getTrackId()), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("RESPTRACK",response);
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response);
                    JSONObject jsonObject_message = jsonObject.getJSONObject("message");
                    JSONObject jsonObject_body = jsonObject_message.getJSONObject("body");

                    Gson gson = new Gson();
                    Lyrics lyrics = gson.fromJson(jsonObject_body.getJSONObject("lyrics").toString(),Lyrics.class);

                    txt_lyrics.setText(lyrics.getLyricsBody());

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        ApiRest.getAlbumDetail(String.valueOf(track.getTrack().getAlbumId()), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("ALBUMDETAIL",response);
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

                    Glide.with(DetailTrackActivity.this).load(track.getTrack().getAlbumCoverart500x500()).into(img_cover);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
