package com.dexter.laravelapplication.Activities;

import android.app.Instrumentation;
import android.app.KeyguardManager;
import android.content.Context;
import android.os.SystemClock;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.UiThreadTest;
import android.test.suitebuilder.annotation.SmallTest;
import android.text.TextUtils;
import android.text.method.Touch;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.dexter.Models.Model_User;
import com.dexter.Utils.ResourceManager;
import com.dexter.Views.Activities.Activity_Login;
import com.dexter.laravelapplication.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import junit.framework.TestCase;

import org.json.JSONObject;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dexter.n on 17/06/2015.
 */
public class Activity_LoginTest extends ActivityInstrumentationTestCase2<Activity_Login> {

    private Activity_Login mActivity;
    private Instrumentation mInstrumentation;
    private EditText et_email;
    private EditText et_password;
    private Button but_login;

    public Activity_LoginTest() {
        super(Activity_Login.class);
    }

    public void setUp() throws Exception {
        super.setUp();

        mActivity = getActivity();
        setActivityInitialTouchMode(true);
        mInstrumentation = getInstrumentation();

        et_email = (EditText)mActivity.findViewById(R.id.et_email);
        et_password = (EditText) mActivity.findViewById(R.id.et_password);
        but_login = (Button)mActivity.findViewById(R.id.but_login);
    }

    public void tearDown() throws Exception {

    }

    public void testPreConditions() throws Exception {
        assertNotNull(et_email);
        assertNotNull(et_password);
        assertNotNull(but_login);
    }

    public void textFieldIsEmpty() throws Throwable
    {
        assertEquals("", et_email.getText().toString());
        assertEquals("", et_password.getText().toString());
    }
}