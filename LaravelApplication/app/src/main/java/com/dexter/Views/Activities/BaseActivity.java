package com.dexter.Views.Activities;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by dexter.n on 11/06/2015.
 */
public abstract class BaseActivity extends Activity {

    protected static BaseActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        instance = this;
    }

    protected abstract void init();
}
