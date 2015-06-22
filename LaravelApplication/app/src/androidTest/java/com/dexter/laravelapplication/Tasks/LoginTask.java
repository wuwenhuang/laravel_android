package com.dexter.laravelapplication.Tasks;

import android.test.ActivityTestCase;
import android.test.suitebuilder.annotation.MediumTest;

import junit.framework.TestCase;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by dexter.n on 22/06/2015.
 */
public class LoginTask extends ActivityTestCase {
    public static final JSONObject loginJsonObj = new JSONObject();

    static {
        try {
            loginJsonObj.put("email", "wuwenhuang88@qq.com");
            loginJsonObj.put("password", "jianyang8");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public LoginTask() {
        super();
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testLogin() {
        URL url = null;
        HttpURLConnection urlConnect;
        try {
            url = new URL("http://dexter-laravelframe.rhcloud.com/login");

            urlConnect = (HttpURLConnection) url.openConnection();
            urlConnect.setRequestMethod("POST");
            urlConnect.setDoOutput(true);
            urlConnect.setDoInput(true);
            urlConnect.setConnectTimeout(10000);
            urlConnect.setRequestProperty("Content-Type", "application/json");
//            urlConnect.setRequestProperty("x-csrf-token", Model_Application.token_csrf);
            urlConnect.connect();

            OutputStreamWriter outWrite = new OutputStreamWriter(urlConnect.getOutputStream());
            outWrite.write(loginJsonObj.toString());
            outWrite.flush();
            outWrite.close();

            int code = urlConnect.getResponseCode();
            assertEquals(code, 200);

            urlConnect.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
