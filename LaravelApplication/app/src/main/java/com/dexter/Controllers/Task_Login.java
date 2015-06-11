package com.dexter.Controllers;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.dexter.Constants.Constant_String;
import com.dexter.Receivers.Receiver_Login;
import com.dexter.Utils.ResourceManager;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by dexter.n on 11/06/2015.
 */
public class Task_Login extends AsyncTask <Void, Void, Boolean> {

    private final Context mContext;
    private final JSONObject mJsonObj;
    private HttpURLConnection urlConnect;
    private InputStream is;
    private String outputStr;

    public Task_Login(Context context, JSONObject jsonObject) {
        mContext = context;
        mJsonObj = jsonObject;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        URL url = null;
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
            outWrite.write(mJsonObj.toString());
            outWrite.flush();
            outWrite.close();

            int code = urlConnect.getResponseCode();

            if (code == 200) {
                is = urlConnect.getInputStream();

                BufferedReader reader = new BufferedReader(new InputStreamReader(is));

                StringBuilder builder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
                reader.close();

                outputStr = builder.toString();

                getData();
            }

            urlConnect.disconnect();
            return true;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    private void getData() {
        try {
            JSONObject responseJson = (JSONObject) new JSONTokener(outputStr).nextValue();

            if (responseJson.has("id")) ResourceManager.UserProfile.id = responseJson.getInt("id");
            if (responseJson.has("name"))
                ResourceManager.UserProfile.name = responseJson.getString("name");
            if (responseJson.has("email"))
                ResourceManager.UserProfile.email = responseJson.getString("email");
            if (responseJson.has("remember_token"))
                ResourceManager.UserProfile.remember_token = responseJson.getString("remember_token");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        Intent broadcastLogin = new Intent();
        broadcastLogin.setAction(Receiver_Login.ACTION_LOGIN);
        broadcastLogin.addCategory(Intent.CATEGORY_DEFAULT);
        broadcastLogin.putExtra(Constant_String.IS_LOGIN, aBoolean);

        mContext.sendBroadcast(broadcastLogin);
    }
}
