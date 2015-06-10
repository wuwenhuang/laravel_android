package com.dexter.Views.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dexter.Controllers.Task_Register;
import com.dexter.laravelapplication.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by dexter.n on 10/06/2015.
 */
public class Activity_Register extends Activity{
    private Holder_Register holder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();
    }

    private void init()
    {
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

                new Task_Register(jsonObj).execute();
            }
        });
    }
}

class Holder_Register
{
    EditText et_username;
    EditText et_password;
    EditText et_email;

    Button but_register;
}
