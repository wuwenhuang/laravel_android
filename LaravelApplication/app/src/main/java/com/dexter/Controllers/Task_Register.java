package com.dexter.Controllers;

import android.os.AsyncTask;

import com.dexter.Constants.Constant_URL;
import com.dexter.Models.Model_Application;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by dexter.n on 08/06/2015.
 */
public class Task_Register extends AsyncTask<Void, Void, Boolean> {

    private final JSONObject sendJsonObj;
    private HttpURLConnection urlConnect;
    private String str_result;
    private StringBuilder strBuilder;

    public Task_Register(JSONObject jsonObj)
    {
        sendJsonObj = jsonObj;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        try {
            URL url = new URL(Constant_URL.URL + Constant_URL.Register);

            urlConnect = (HttpURLConnection) url.openConnection();

            urlConnect.setDoOutput(true);
            urlConnect.setDoInput(true);
            urlConnect.setInstanceFollowRedirects(true);
            urlConnect.setRequestMethod("POST");
            urlConnect.setRequestProperty("Content-Type", "application/json");
            urlConnect.setRequestProperty("x-csrf-token", Model_Application.token_csrf);
            urlConnect.setUseCaches(false);
            urlConnect.connect();

            OutputStreamWriter outWrite = new OutputStreamWriter(urlConnect.getOutputStream());
            outWrite.write(sendJsonObj.toString());
            outWrite.flush();
            outWrite.close();

            int result = urlConnect.getResponseCode();
            if(result == HttpURLConnection.HTTP_OK)
            {
                buffRead();
                urlConnect.disconnect();
                return true;
            }
            else
            {
                urlConnect.disconnect();
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void buffRead() {
        str_result = null;

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    urlConnect.getInputStream(),"utf-8"));
            while ((str_result = br.readLine()) != null) {
                strBuilder.append(str_result + "\n");
            }
            br.close();

            str_result = strBuilder.toString();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}
