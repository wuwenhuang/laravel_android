package com.dexter.laravelapplication.Activities;

import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.InstrumentationTestCase;
import android.test.TouchUtils;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;
import android.widget.EditText;

import com.dexter.Views.Activities.Activity_Register;
import com.dexter.laravelapplication.R;

/**
 * Created by dexter.n on 17/06/2015.
 */
public class Activity_RegisterTest extends ActivityInstrumentationTestCase2<Activity_Register> {

    private Activity_Register mActivity;
    private Instrumentation mInstrumentation;
    private EditText et_username;
    private Button but_register;
    private EditText et_password;
    private EditText et_email;

    public Activity_RegisterTest() {
        super(Activity_Register.class);
    }

    public void setUp() throws Exception {
        super.setUp();

        mActivity = getActivity();
        mInstrumentation = getInstrumentation();

        et_username = (EditText)getActivity().findViewById(R.id.et_username);
        et_password = (EditText)getActivity().findViewById(R.id.et_password);
        et_email = (EditText)getActivity().findViewById(R.id.et_email);

        but_register = (Button)getActivity().findViewById(R.id.but_register);
    }

    @SmallTest
    public void testPreConditions() throws Exception {
        assertNotNull(et_username);
        assertNotNull(et_password);
        assertNotNull(et_email);
        assertNotNull(but_register);
    }

    public void tearDown() throws Exception {

    }
}