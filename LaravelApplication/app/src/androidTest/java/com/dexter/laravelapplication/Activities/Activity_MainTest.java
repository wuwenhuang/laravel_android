package com.dexter.laravelapplication.Activities;

import android.app.Instrumentation;
import android.content.Intent;
import android.content.IntentFilter;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;

import com.dexter.Views.Activities.Activity_Login;
import com.dexter.Views.Activities.Activity_Main;
import com.dexter.Views.Activities.Activity_Register;
import com.dexter.laravelapplication.R;

/**
 * Created by dexter.n on 17/06/2015.
 */
public class Activity_MainTest extends ActivityInstrumentationTestCase2<Activity_Main> {


    private Activity_Main mActivity;
    private Instrumentation mInstrumentation;
    private Button but_register;
    private Button but_login;

    public Activity_MainTest() {
        super(Activity_Main.class);
    }

    public void setUp() throws Exception {
        super.setUp();

        mActivity = getActivity();
        mInstrumentation = getInstrumentation();

        but_register = (Button)mActivity.findViewById(R.id.but_register);
        but_login = (Button)mActivity.findViewById(R.id.but_login);
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    @SmallTest
    public void testPreConditions() throws Exception {
        assertNotNull(mActivity);
        assertNotNull(mInstrumentation);
        assertNotNull(but_register);
        assertNotNull(but_login);

    }

    @SmallTest
    public void testFollowLink() throws Exception {
        Instrumentation.ActivityMonitor monitor = mInstrumentation.addMonitor(Activity_LoginTest.class.getName(), null, false);
        assertEquals(0, monitor.getHits());
        TouchUtils.clickView(this, but_login);
        monitor.waitForActivityWithTimeout(5000);
        assertEquals(1, monitor.getHits());

        monitor = mInstrumentation.addMonitor(Activity_RegisterTest.class.getName(), null, false);
        assertEquals(0, monitor.getHits());
        TouchUtils.clickView(this, but_register);
        monitor.waitForActivityWithTimeout(5000);
        assertEquals(1, monitor.getHits());

        mInstrumentation.removeMonitor(monitor);
    }


}