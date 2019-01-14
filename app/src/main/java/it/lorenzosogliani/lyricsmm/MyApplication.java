package it.lorenzosogliani.lyricsmm;

import android.app.Application;
import android.content.Context;


import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.services.concurrency.UnmetDependencyException;



/**
 * Created by Lorenzo on 28/06/2016 12.58.
 * Property of LilloNet
 * Email: l.sogliani@gmail.com or lorenzo@apporta.it
 */

public class MyApplication extends Application {
    private static Context mCtx;
    private static RequestQueue mRequestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        try{
            Fabric.with(this, new Crashlytics());
        }catch (UnmetDependencyException exception){
            exception.printStackTrace();
        }
        mCtx = this;
        mRequestQueue = getRequestQueue();
    }
    public static RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mCtx);
        }
        return mRequestQueue;
    }


    public static <T> void addToRequestQueue(Request<T> req) {
        req.setRetryPolicy(new DefaultRetryPolicy(0,-1,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        getRequestQueue().add(req);
    }
}
