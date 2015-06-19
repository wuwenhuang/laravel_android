package com.dexter.laravelapplication;

import android.os.Handler;
import android.os.Message;
import android.test.ApplicationTestCase;

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