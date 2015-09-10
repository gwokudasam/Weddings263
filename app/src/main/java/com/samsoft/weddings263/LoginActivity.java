package com.samsoft.weddings263;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class LoginActivity extends AppCompatActivity{

    private ParseUser parseUser;

    private Button btnLogIn;

    private static final String APP_ID = "Zkm7SXTdhtvfAsNuW0gilw8YZ1yHgcIaZ6Fz6Xia";
    private static final String CLIENT_ID = "9C9lUNyxNhAXyHKJCvKxsjiY6Jo3o13ab6wA08Ri";
    //private static final String MASTER_KEY = "rRomBuByx2GlzrIKjl5ciAASXqChTVfa9abIteKf"

    private static final String TAG = LoginActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Parse.initialize(this, APP_ID, CLIENT_ID);
        Log.i(TAG, "Pass Initialized");


        final EditText etUn = (EditText) findViewById(R.id.etUsername);
        final EditText etPass = (EditText) findViewById(R.id.etPassword);
        btnLogIn = (Button) findViewById(R.id.btnLogin);

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //to do logic
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                LoginActivity.this.finish();  //exit login
                startActivity(intent);



            }
        });


    }




}
