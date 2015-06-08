package com.dexter.Application;

import android.app.Application;

import com.dexter.Controllers.Task_GetCSRFToken;

/**
 * Created by dexter.n on 08/06/2015.
 */
public class LaravelApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        new Task_GetCSRFToken().execute();
    }
}
