package com.dexter.Application;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.dexter.Controllers.Task_GetCSRFToken;
import com.dexter.Models.Model_Application;
import com.dexter.Utils.ResourceManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dexter.n on 08/06/2015.
 */
public class LaravelApplication extends Application {

    public static LaravelApplication instance;
    private List<Activity> mCacheTaskActivity;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        mCacheTaskActivity = new ArrayList<Activity>();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, "http://dexter-laravelframe.rhcloud.com/get_csrftoken",
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Gson gson = new GsonBuilder().create();
                        String outputJson = jsonObject.toString();
                        ResourceManager.Application = gson.fromJson(outputJson, Model_Application.class);
                        String token = ResourceManager.Application.csrf_token;
                    }
                },
                new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        volleyError.printStackTrace();
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };

        Volley.newRequestQueue(getBaseContext()).add(jsonObjectRequest);

//        new Task_GetCSRFToken().execute();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();

        Toast.makeText(getApplicationContext(), "Caution! Low Memory", Toast.LENGTH_LONG).show();
    }

    public void addActivity(Activity item)
    {
        mCacheTaskActivity.add(item);

        if (mCacheTaskActivity.size() > 10)
        {
            Activity act_first = mCacheTaskActivity.get(0);
            mCacheTaskActivity.remove(act_first);
        }
    }

    public void removeActivity(Activity item)
    {
        mCacheTaskActivity.remove(item);
    }
}
