package com.dexter.Controllers;

import android.os.AsyncTask;

import com.dexter.Constants.Constant_URL;
import com.dexter.Models.Model_Application;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by dexter.n on 08/06/2015.
 */
public class Task_GetCSRFToken extends AsyncTask <Void, Void, Boolean> {
    private HttpURLConnection urlHttpConnect;
    private String tokenStr;
    private String url;
    private InputStream is;

    @Override
    protected Boolean doInBackground(Void... params) {
        try {
            URL url = new URL("http://dexter-laravelframe.rhcloud.com/get_csrftoken");

            urlHttpConnect = (HttpURLConnection)url.openConnection();
            urlHttpConnect.setRequestMethod("GET");

            is = urlHttpConnect.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            reader.close();

            tokenStr = builder.toString();

            if (getCsrfToken()) {
                urlHttpConnect.disconnect();
                return true;
            }else
                return false;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }


    public boolean getCsrfToken() {
        try {
            JSONObject tokenJson = (JSONObject) new JSONTokener(tokenStr).nextValue();

            if (tokenJson.has("csrf_token"))
            {
                Model_Application.token_csrf = tokenJson.getString("csrf_token");
            }
        } catch (JSONException e) {
            return false;
        }

        return true;
    }
}
