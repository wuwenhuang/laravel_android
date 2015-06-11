package com.dexter.Views.Activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dexter.Controllers.Task_Register;
import com.dexter.Receivers.Receiver_Register;
import com.dexter.Utils.ResourceManager;
import com.dexter.laravelapplication.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by dexter.n on 10/06/2015.
 */
public class Activity_Register extends BaseActivity{
    private Holder_Register holder;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initBroadcast();
        init();
    }

    private void initBroadcast() {
        IntentFilter registerFilter = new IntentFilter(Receiver_Register.ACTION_REGISTER);
        registerFilter.addCategory(Intent.CATEGORY_DEFAULT);
        Activity_Register.this.registerReceiver(mBroadcastRegister, registerFilter);
    }

    @Override
    public void init() {
        holder = new Holder_Register();

        holder.et_username = (EditText)findViewById(R.id.et_username);
        holder.et_password = (EditText)findViewById(R.id.et_password);
        holder.et_email = (EditText)findViewById(R.id.et_email);

        holder.but_register = (Button)findViewById(R.id.but_register);
        holder.but_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject jsonObj = new JSONObject();

                try {
                    jsonObj.put("username", holder.et_username.getText().toString());
                    jsonObj.put("email", holder.et_email.getText().toString());
                    jsonObj.put("password", holder.et_password.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                createDialog();
                new Task_Register(getApplicationContext(), jsonObj).execute();
            }


        });
    }

    private void createDialog()
    {
        dialog = new ProgressDialog(getBaseContext());
        dialog.setMessage("Registering..");
        dialog.show();
    }

    private Receiver_Register mBroadcastRegister = new Receiver_Register()
    {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("ID", ResourceManager.UserProfile.id + "");

            if (dialog != null && dialog.isShowing())
                dialog.dismiss();

            finish();
        }
    };
}

class Holder_Register
{
    EditText et_username;
    EditText et_password;
    EditText et_email;

    Button but_register;
}
