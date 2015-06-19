package com.dexter.laravelapplication.Activities;

import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.suitebuilder.annotation.SmallTest;
import android.text.method.Touch;
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
    private EditText et_email;
    private EditText et_password;
    private Button but_login;

    public Activity_LoginTest() {
        super(Activity_Login.class);
    }

    public void setUp() throws Exception {
        super.setUp();

        mActivity = getActivity();
        mInstrumentation = getInstrumentation();

        et_email = (EditText)mActivity.findViewById(R.id.et_email);
        et_password = (EditText) mActivity.findViewById(R.id.et_password);
        but_login = (Button)mActivity.findViewById(R.id.but_login);
    }

    @SmallTest
    public void testPreConditions() throws Exception {
        assertNotNull(et_email);
        assertNotNull(et_password);
        assertNotNull(but_login);
    }

    public void tearDown() throws Exception {

    }

    @SmallTest
    public void testFollowLink() throws Exception {
        et_email.setText("wuwenhuang88@qq.com");
        et_password.setText("jianyang8");

        TouchUtils.clickView(this, but_login);
    }

}