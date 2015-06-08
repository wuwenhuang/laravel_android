package com.dexter.Views.Activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dexter.Controllers.Task_Register;
import com.dexter.laravelapplication.R;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends ActionBarActivity {

    private Holder_Main holder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init()
    {
        holder = new Holder_Main();

        holder.et_username = (EditText)findViewById(R.id.et_username);
        holder.et_password = (EditText)findViewById(R.id.et_password);

        holder.but_register = (Button)findViewById(R.id.but_register);
        holder.but_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject jsonObj = new JSONObject();

                try {
                    jsonObj.put("username", holder.et_username.getText().toString());
                    jsonObj.put("password", holder.et_password.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                new Task_Register(jsonObj).execute();
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
    EditText et_username;
    EditText et_password;

    Button but_register;
}
