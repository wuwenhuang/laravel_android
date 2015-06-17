package com.dexter.laravelapplication;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.test.InstrumentationTestCase;

import com.dexter.Application.LaravelApplication;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<LaravelApplication> {
    public ApplicationTest() {
        super(LaravelApplication.class);

    }

    @Override
    public void assertActivityRequiresPermission(String packageName, String className, String permission) {
        super.assertActivityRequiresPermission(packageName, className, permission);
    }
}