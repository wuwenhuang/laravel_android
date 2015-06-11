package com.dexter.Views.Activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dexter.Application.LaravelApplication;
import com.dexter.Controllers.Task_Register;
import com.dexter.laravelapplication.R;

import org.json.JSONException;
import org.json.JSONObject;


public class Activity_Main extends BaseActivity {

    private Holder_Main holder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    @Override
    public void init() {
        holder = new Holder_Main();

        holder.but_register = (Button) findViewById(R.id.but_register);
        holder.but_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(getBaseContext(), Activity_Register.class);
                startActivity(registerIntent);
                LaravelApplication.instance.addActivity(instance);
            }
        });

        holder.but_login = (Button) findViewById(R.id.but_login);
        holder.but_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(getBaseContext(), Activity_Login.class);
                startActivity(loginIntent);
                LaravelApplication.instance.addActivity(instance);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

class Holder_Main
{
    Button but_register;
    Button but_login;
}
