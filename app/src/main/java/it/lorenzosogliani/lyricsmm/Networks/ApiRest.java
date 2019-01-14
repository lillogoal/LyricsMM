package it.lorenzosogliani.lyricsmm.Networks;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import it.lorenzosogliani.lyricsmm.MyApplication;

public class ApiRest {
    public static final String API_VERSION = "1.1/";
    public static final String URL = "https://api.musixmatch.com/ws/"+API_VERSION;
    public static final String API_KEY = "1c9e075968d686e586bc8bee13b82ca0";

    public static boolean getSongChart(Response.Listener<String> response, Response.ErrorListener errorListener){
        final String uri;
        uri = String.format(URL+"chart.tracks.get");


        final Map<String, String> params = new HashMap<String, String>();
        params.put("apikey",API_KEY);
        params.put("chart_name","top");
        params.put("page","1");
        params.put("page_size","25");
        params.put("country","it");
        params.put("f_has_lyrics","1");

        StringRequest sendMessageRequest = new StringRequest(Request.Method.GET, uri, response, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
            @Override
            public String getUrl() {
                StringBuilder stringBuilder = new StringBuilder(uri);
                int i = 1;
                for (Map.Entry<String,String> entry: params.entrySet()) {
                    String key;
                    String value;
                    try {
                        key = URLEncoder.encode(entry.getKey(), "UTF-8");
                        value = URLEncoder.encode(entry.getValue(), "UTF-8");
                        if(i == 1) {
                            stringBuilder.append("?" + key + "=" + value);
                        } else {
                            stringBuilder.append("&" + key + "=" + value);
                        }
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    i++;
                }

                return stringBuilder.toString();
            }
        };

        MyApplication.addToRequestQueue(sendMessageRequest);

        return true;
    }
    public static boolean getTrackList(String id,Response.Listener<String> response, Response.ErrorListener errorListener){
        final String uri;
        uri = String.format(URL+"track.lyrics.get");


        final Map<String, String> params = new HashMap<String, String>();
        params.put("apikey",API_KEY);
        params.put("track_id",id);

        StringRequest sendMessageRequest = new StringRequest(Request.Method.GET, uri, response, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
            @Override
            public String getUrl() {
                StringBuilder stringBuilder = new StringBuilder(uri);
                int i = 1;
                for (Map.Entry<String,String> entry: params.entrySet()) {
                    String key;
                    String value;
                    try {
                        key = URLEncoder.encode(entry.getKey(), "UTF-8");
                        value = URLEncoder.encode(entry.getValue(), "UTF-8");
                        if(i == 1) {
                            stringBuilder.append("?" + key + "=" + value);
                        } else {
                            stringBuilder.append("&" + key + "=" + value);
                        }
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    i++;
                }

                return stringBuilder.toString();
            }
        };

        MyApplication.addToRequestQueue(sendMessageRequest);

        return true;
    }
    public static boolean getAlbumDetail(String id,Response.Listener<String> response, Response.ErrorListener errorListener){
        final String uri;
        uri = String.format(URL+"album.get");


        final Map<String, String> params = new HashMap<String, String>();
        params.put("apikey",API_KEY);
        params.put("album_id",id);

        StringRequest sendMessageRequest = new StringRequest(Request.Method.GET, uri, response, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
            @Override
            public String getUrl() {
                StringBuilder stringBuilder = new StringBuilder(uri);
                int i = 1;
                for (Map.Entry<String,String> entry: params.entrySet()) {
                    String key;
                    String value;
                    try {
                        key = URLEncoder.encode(entry.getKey(), "UTF-8");
                        value = URLEncoder.encode(entry.getValue(), "UTF-8");
                        if(i == 1) {
                            stringBuilder.append("?" + key + "=" + value);
                        } else {
                            stringBuilder.append("&" + key + "=" + value);
                        }
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    i++;
                }

                return stringBuilder.toString();
            }
        };

        MyApplication.addToRequestQueue(sendMessageRequest);

        return true;
    }
}
