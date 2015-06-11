package com.dexter.Application;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.widget.Toast;

import com.dexter.Controllers.Task_GetCSRFToken;

import java.util.ArrayList;
import java.util.List;

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

        new Task_GetCSRFToken().execute();
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
