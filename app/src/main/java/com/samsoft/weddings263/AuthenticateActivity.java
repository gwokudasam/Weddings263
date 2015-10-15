package com.samsoft.weddings263;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.rahatarmanahmed.cpv.CircularProgressView;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class AuthenticateActivity extends AppCompatActivity {

    protected String mAction;

    protected EditText mEmailField;
    protected EditText mPasswordField;
    protected Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authenticate);

        mEmailField = (EditText) findViewById(R.id.editText1);
        mPasswordField = (EditText) findViewById(R.id.editText2);
        mButton = (Button) findViewById(R.id.button1);
        final CircularProgressView mProgressBar = (CircularProgressView) findViewById(R.id.progressBar1);
        Bundle bundle = getIntent().getExtras();
        mAction = bundle.getString(LoginOrSignupActivity.TYPE);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mProgressBar.setVisibility(View.VISIBLE);
                mProgressBar.startAnimation();
                String username = mEmailField.getText().toString();
                String password = mPasswordField.getText().toString();

                if (mAction.equals(LoginOrSignupActivity.SIGNUP)) {
                    /*
                     * Sign up using ParseUser
					 */
                    ParseUser user = new ParseUser();
                    user.setUsername(username);
                    user.setPassword(password);

                    user.signUpInBackground(new SignUpCallback() {
                        public void done(ParseException e) {
                            mProgressBar.setVisibility(View.INVISIBLE);
                            if (e == null) {
                                startActivity(new Intent(
                                        AuthenticateActivity.this,
                                        MainActivity.class));
                            } else {
                                mPasswordField.setText("");
                                mEmailField.setText("");
                                Toast.makeText(AuthenticateActivity.this,
                                        "Sign up failed! Try again.",
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                } else {
                    /*
                     * Login using ParseUser
					 */
                    ParseUser.logInInBackground(username, password,
                            new LogInCallback() {
                                public void done(ParseUser user,
                                                 ParseException e) {
                                    mProgressBar.setVisibility(View.INVISIBLE);
                                    if (user != null) {
                                        // Hooray! The user is logged in.
                                        startActivity(new Intent(
                                                AuthenticateActivity.this,
                                                MainActivity.class));
                                    } else {
                                        // Login failed. Look at the
                                        // ParseException to see what happened.
                                        Toast.makeText(
                                                AuthenticateActivity.this,
                                                "Wrong username / password! Please try again",
                                                Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }
            }
        });
    }
}
