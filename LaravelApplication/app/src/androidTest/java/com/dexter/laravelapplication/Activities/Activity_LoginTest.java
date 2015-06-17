package com.dexter.laravelapplication.Activities;

import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;

import com.dexter.Views.Activities.Activity_Login;
import com.dexter.laravelapplication.R;

import junit.framework.TestCase;

/**
 * Created by dexter.n on 17/06/2015.
 */
public class Activity_LoginTest extends ActivityInstrumentationTestCase2<Activity_Login> {

    private Activity_Login mActivity;
    private Instrumentation mInstrumentation;
    private EditText et_username;
    private EditText et_password;
    private Button but_register;

    public Activity_LoginTest() {
        super(Activity_Login.class);
    }

    public void setUp() throws Exception {
        super.setUp();

        mActivity = getActivity();
        mInstrumentation = getInstrumentation();

        et_username = (EditText)mActivity.findViewById(R.id.et_username);
        et_password = (EditText) mActivity.findViewById(R.id.et_password);
        but_register = (Button)mActivity.findViewById(R.id.but_register);
    }

    public void tearDown() throws Exception {

    }

    public void testOnCreate() throws Exception {

    }

    public void testInit() throws Exception {

    }
}