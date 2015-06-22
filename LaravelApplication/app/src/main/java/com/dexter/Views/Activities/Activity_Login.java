package com.dexter.Views.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dexter.Constants.Constant_String;
import com.dexter.Controllers.Task_Login;
import com.dexter.Models.Model_User;
import com.dexter.Receivers.Receiver_Login;
import com.dexter.Utils.ResourceManager;
import com.dexter.laravelapplication.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dexter.n on 11/06/2015.
 */
public class Activity_Login extends BaseActivity{

    private Holder_Login holder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        initBroadcast();
        init();
    }

    private void initBroadcast() {
        IntentFilter loginFilter = new IntentFilter(Receiver_Login.ACTION_LOGIN);
        loginFilter.addCategory(Intent.CATEGORY_DEFAULT);

        Activity_Login.this.registerReceiver(mBroadcastReceiverLogin, loginFilter);
    }

    @Override
    protected void init() {
        holder = new Holder_Login();

        holder.et_email = (EditText)findViewById(R.id.et_email);
        holder.et_password = (EditText)findViewById(R.id.et_password);

        holder.but_login = (Button)findViewById(R.id.but_login);
        holder.but_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject jsonObj = new JSONObject();
                try {
                    jsonObj.put("email", holder.et_email.getText().toString());
                    jsonObj.put("password", holder.et_password.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                volleyCall(jsonObj);
//                new Task_Login(getApplicationContext(), jsonObj).execute();
            }
        });

        holder.but_forgetPassword = (Button)findViewById(R.id.but_forgetPassword);
        holder.but_forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void volleyCall(final JSONObject jsonObj) {
        JsonObjectRequest jsonObjRequest = new JsonObjectRequest(
                Request.Method.POST, "http://dexter-laravelframe.rhcloud.com/login", jsonObj,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Gson gson = new GsonBuilder().create();
                        String outputString = jsonObject.toString();
                        ResourceManager.UserProfile = gson.fromJson(outputString, Model_User.class);
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        volleyError.printStackTrace();
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
        Volley.newRequestQueue(getBaseContext()).add(jsonObjRequest);
    }

    private Receiver_Login mBroadcastReceiverLogin = new Receiver_Login()
    {
        @Override
        public void onReceive(Context context, Intent intent) {
            Boolean isLogin = intent.getBooleanExtra(Constant_String.IS_LOGIN, false);


        }
    };
}

class Holder_Login
{
    EditText et_email;
    EditText et_password;

    Button but_login;
    Button but_forgetPassword;
}


